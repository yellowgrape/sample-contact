package functional.android;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.LinkedList;
import java.util.List;

import rebeccapurple.Condition;
import rebeccapurple.Listener;
import rebeccapurple.android.contact.Content;

public class contact {

    public static Content.Photo photo(Cursor cursor, Content.Photo photo) {
        if(photo == null){
            photo = new Content.Photo();
        }
        photo.thumbnail(functional.android.sql.get.string(cursor, Content.DB.COLUMN.BASE.PHOTO_THUMBNAIL_URI));
        photo.file(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.BASE.PHOTO_FILE_ID));
        photo.id(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.BASE.PHOTO_ID));
        photo.uri(functional.android.sql.get.string(cursor, Content.DB.COLUMN.BASE.PHOTO_URI));
        return photo;
    }

    public static Content.Base base(Cursor cursor, Content.Base base){
        if(base == null){
            base = new Content.Base();
        }
        base.id(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.BASE.ID));
        base.raw(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.BASE.NAME_RAW_CONTACT_ID));
        base.name(functional.android.sql.get.string(cursor, Content.DB.COLUMN.BASE.DISPLAY_NAME));
        base.lookup(functional.android.sql.get.string(cursor, Content.DB.COLUMN.BASE.LOOKUP));
        base.photo = photo(cursor, base.photo);
        return base;
    }

    public static Content.Status status(Cursor cursor, Content.Status status) {
        if(status == null){
            status = new Content.Status();
        }
        status._package(functional.android.sql.get.string(cursor, Content.DB.COLUMN.STATUS.CONTACT_STATUS_RES_PACKAGE));
        status.chattable(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.STATUS.CONTACT_CHAT_CAPABILITY));
        status.icon(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.STATUS.CONTACT_STATUS_ICON));
        status.label(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.STATUS.CONTACT_STATUS_LABEL));
        status.timestamp(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.STATUS.CONTACT_STATUS_TIMESTAMP));
        status.presence(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.STATUS.CONTACT_PRESENCE));
        status.value(functional.android.sql.get.string(cursor, Content.DB.COLUMN.STATUS.CONTACT_STATUS));
        return status;
    }

    public static Content.Option option(Cursor cursor, Content.Option option){
        if(option == null){
            option = new Content.Option();
        }
        option.contacted(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.OPTIONS.LAST_TIME_CONTACTED));
        option.hit(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.OPTIONS.TIMES_CONTACTED));
        option.pinned(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.OPTIONS.PINNED));
        option.starred(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.OPTIONS.STARRED));
        option.voicemail(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.OPTIONS.VOICEMAIL));
        option.rington(functional.android.sql.get.string(cursor, Content.DB.COLUMN.OPTIONS.RINGTON));
        return option;
    }

    public static Content.Name name(Cursor cursor, Content.Name name){
        if(name == null){
            name = new Content.Name();
        }
        name.alternative(functional.android.sql.get.string(cursor, Content.DB.COLUMN.NAME.DISPLAY_NAME_ALTERNATIVE));
        name.primary(functional.android.sql.get.string(cursor, Content.DB.COLUMN.NAME.DISPLAY_NAME_PRIMARY));
        name.source(functional.android.sql.get.string(cursor, Content.DB.COLUMN.NAME.DISPLAY_NAME_SOURCE));
        name.phonetic = contact.phonetic(cursor, name.phonetic);
        return name;
    }

    public static Content.Phonetic phonetic(Cursor cursor, Content.Phonetic phonetic) {
        if(phonetic == null){
            phonetic = new Content.Phonetic();
        }
        phonetic.style(functional.android.sql.get.string(cursor, Content.DB.COLUMN.NAME.PHONETIC_NAME_STYLE));
        phonetic.name(functional.android.sql.get.string(cursor, Content.DB.COLUMN.NAME.PHONETIC_NAME));
        return phonetic;
    }

    public static List<Content> all(Context context, Uri uri){ return all(context, uri, null, null, null); }
    public static List<Content> all(Context context, Uri uri, Listener<Content> callback){ return  all(context, uri, null, null, callback); }
    public static List<Content> all(Context context, Uri uri, String sort){ return  all(context, uri, sort, null, null); }
    public static List<Content> all(Context context, Uri uri, String sort, Listener<Content> callback){ return  all(context, uri, sort, null, callback); }
    public static List<Content> all(Context context, Uri uri, String sort, Condition<Cursor> condition){ return all(context, uri, sort, condition, null); }
    public static List<Content> all(Context context, Uri uri, String sort, Condition<Cursor> condition, Listener<Content> callback){
        ContentResolver resolver = context.getContentResolver();
        LinkedList<Content> contents = null;
        Cursor cursor = null;
        try {
            cursor = resolver.query(uri, null, null, null, sort);
            functional.log.e(functional.android.sql.columns(cursor));
            if(cursor != null){
                contents = new LinkedList<>();
                int total = cursor.getCount();
                cursor.moveToFirst();
                for(int i  = 0; i < total; i++){
                    if(condition == null || condition.check(cursor)){
                        Content o = new Content();
                        o.base = contact.base(cursor, o.base);
                        o.status = contact.status(cursor, o.status);
                        o.option = contact.option(cursor, o.option);
                        o.name = contact.name(cursor, o.name);
                        if(callback != null){
                            callback.on(o);
                        }
                        contents.add(o);
                        cursor.moveToNext();
                        continue;
                    }
                    break;
                }
            }
        } catch(Throwable e){
            functional.log.e("resolver.query(...)", e);
        } finally {
            if(cursor != null){
                cursor.close();
            }
        }
        return contents;
    }
}
