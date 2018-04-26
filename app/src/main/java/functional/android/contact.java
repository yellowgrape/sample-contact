package functional.android;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import rebeccapurple.Condition;
import rebeccapurple.Listener;
import rebeccapurple.android.contact.Content;
import rebeccapurple.android.contact.Data;
import rebeccapurple.android.contact.Raw;

public class contact {

    public static Content.Photo photo(Cursor cursor, Content.Photo photo) {
        if(photo == null){
            photo = new Content.Photo();
        }
        photo.thumbnail(functional.android.sql.get.string(cursor, Content.DB.COLUMN.PHOTO_THUMBNAIL_URI));
        photo.file(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.PHOTO_FILE_ID));
        photo.id(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.PHOTO_ID));
        photo.uri(functional.android.sql.get.string(cursor, Content.DB.COLUMN.PHOTO_URI));
        return photo;
    }

    public static Content base(Cursor cursor, Content base){
        if(base == null){
            base = new Content();
        }
        base.id(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.ID));
        // base.raw(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.NAME_RAW_CONTACT_ID));
        base.name(functional.android.sql.get.string(cursor, Content.DB.COLUMN.DISPLAY_NAME));
        base.lookup(functional.android.sql.get.string(cursor, Content.DB.COLUMN.LOOKUP));
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

    public static Raw raw(Cursor cursor, Raw raw){
        if(raw == null){
            raw = new Raw();
        }
        raw.id(functional.android.sql.get.int64(cursor, Content.DB.COLUMN.NAME_RAW_CONTACT_ID));
        return raw;
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
                        Content o = contact.base(cursor, new Content());
                        o.raw = contact.raw(cursor, o.raw);
                        o.status = contact.status(cursor, o.status);
                        o.option = contact.option(cursor, o.option);
                        o.name = contact.name(cursor, o.name);
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
        if(contents != null){
            for(Content content : contents){
                content.raw = raw.get(context, content);
                content.data = data.get(context, content);
                if(callback != null){
                    callback.on(content);
                }
            }
        }
        return contents;
    }

    public static class data {
        public static List<Data> get(Context context, Content content){
            if(content != null && content.id() != null){
                if(content.data == null){
                    content.data = new ArrayList<>();
                } else {
                    content.data.clear();
                }
                ContentResolver resolver = context.getContentResolver();
                Cursor cursor = null;
                try {
                    cursor = resolver.query(ContactsContract.Data.CONTENT_URI, null, Data.DB.COLUMN.ID + "=?", new String[]{functional.string.from(content.id())}, null);
                    if(cursor != null){
                        int total = cursor.getCount();
                        cursor.moveToFirst();
                        for(int i = 0; i < total; i++){
                            Data o = new Data();
                            o.id(sql.get.int64(cursor, Data.DB.COLUMN.ID));
                            o._package(sql.get.string(cursor, Data.DB.COLUMN.PACKAGE));
                            o._super(sql.get.int64(cursor, Data.DB.COLUMN.SUPER));
                            o.mimetype(sql.get.string(cursor, Data.DB.COLUMN.MIMETYPE));
                            o.primary(sql.get.int64(cursor, Data.DB.COLUMN.PRIMARY));
                            o.readonly(sql.get.int64(cursor, Data.DB.COLUMN.READONLY));
                            o.data = new ArrayList<>();
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA1));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA2));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA3));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA3));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA4));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA5));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA6));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA7));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA8));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA9));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA10));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA11));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA12));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA13));
                            o.data.add(sql.get.string(cursor, Data.DB.COLUMN.DATA14));
                            o.blob(sql.get.blob(cursor, Data.DB.COLUMN.DATA15));
                            content.data.add(o);
                            cursor.moveToNext();
                        }
                    } else {
                        functional.log.e("cursor == null");
                    }
                } catch(Throwable e){
                    functional.log.e("resolver.query(...)", e);
                } finally {
                    if(cursor != null){
                        cursor.close();
                    }
                }


            } else {
                functional.log.e("content != null && content.id() != null");
            }
            return content != null ? content.data : null;
        }
    }

    public static class raw {
        public static Raw.Sync sync(Cursor cursor, Raw.Sync sync){
            if(sync == null){
                sync = new Raw.Sync();
            }
            if(sync.data == null){
                sync.data = new ArrayList<>();
            } else {
                sync.data.clear();
            }
            sync.data.add(sql.get.string(cursor, Raw.DB.COLUMN.SYNC.FIRST));
            sync.data.add(sql.get.string(cursor, Raw.DB.COLUMN.SYNC.SECOND));
            sync.data.add(sql.get.string(cursor, Raw.DB.COLUMN.SYNC.THIRD));
            sync.data.add(sql.get.string(cursor, Raw.DB.COLUMN.SYNC.FOURTH));
            sync.dirty(sql.get.int64(cursor, Raw.DB.COLUMN.SYNC.DIRTY));
            sync.name(sql.get.string(cursor, Raw.DB.COLUMN.SYNC.NAME));
            sync.source(sql.get.string(cursor, Raw.DB.COLUMN.SYNC.SOURCE));
            sync.type(sql.get.string(cursor, Raw.DB.COLUMN.SYNC.TYPE));
            sync.version(sql.get.int64(cursor, Raw.DB.COLUMN.SYNC.VERSION));
            return sync;
        }

        public static Raw get(Context context, Content content){
            if(content != null && content.raw != null && content.raw.id() != null){
                ContentResolver resolver = context.getContentResolver();
                Cursor cursor = null;
                try {
                    cursor = resolver.query(ContactsContract.RawContacts.CONTENT_URI, null, Raw.DB.COLUMN.ID + "=?", new String[]{functional.string.from(content.raw.id())}, null);
                    if (cursor != null) {
                        if(cursor.getCount()==1){
                            cursor.moveToFirst();
                            content.raw.account(sql.get.string(cursor, Raw.DB.COLUMN.ACCOUNT));
                            content.raw.aggregation(sql.get.string(cursor, Raw.DB.COLUMN.AGGREGATION));
                            content.raw.contact(sql.get.int64(cursor, Raw.DB.COLUMN.CONTACT));
                            content.raw.dataset(sql.get.string(cursor, Raw.DB.COLUMN.DATASET));
                            content.raw.deleted(sql.get.int64(cursor, Raw.DB.COLUMN.DELETED));
                            content.raw.dirty(sql.get.int64(cursor, Raw.DB.COLUMN.DIRTY));
                            content.raw.profile(sql.get.int64(cursor, Raw.DB.COLUMN.PROFILE));
                            content.raw.readonly(sql.get.int64(cursor, Raw.DB.COLUMN.READONLY));
                            content.raw.unique(sql.get.int64(cursor, Raw.DB.COLUMN.UNIQUE));
                            content.raw.name = name(cursor, content.raw.name);
                            content.raw.option = option(cursor, content.raw.option);
                            content.raw.sync = raw.sync(cursor, content.raw.sync);
                        } else {
                            functional.log.e("cursor.getCount() = " + cursor.getCount());
                        }
                    } else {
                        functional.log.e("cursor == null");
                    }
                } catch(Throwable e){
                    functional.log.e("resolver.query(...)", e);
                } finally {
                    if(cursor != null){
                        cursor.close();
                    }
                }
            } else {
                functional.log.e("content.raw == null || content.raw.id() == null");
            }
            return content !=null ? content.raw : null;
        }
    }
}
