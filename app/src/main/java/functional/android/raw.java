package functional.android;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

import rebeccapurple.android.contact.Content;
import rebeccapurple.android.contact.Raw;

public class raw {
    public static Raw.Sync sync(Cursor cursor, Raw.Sync sync){
        if(sync == null){
            sync = new Raw.Sync();
        }
        if(sync.data == null){
            sync.data = new ArrayList<>();
        } else {
            sync.data.clear();
        }
        sync.data.add(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.SYNC.FIRST));
        sync.data.add(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.SYNC.SECOND));
        sync.data.add(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.SYNC.THIRD));
        sync.data.add(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.SYNC.FOURTH));
        sync.dirty(functional.android.sql.get.int64(cursor, Raw.DB.COLUMN.SYNC.DIRTY));
        sync.name(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.SYNC.NAME));
        sync.source(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.SYNC.SOURCE));
        sync.type(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.SYNC.TYPE));
        sync.version(functional.android.sql.get.int64(cursor, Raw.DB.COLUMN.SYNC.VERSION));
        return sync;
    }

    public static Raw get(Context context, Content content){
        if(content.raw != null && content.raw.id() != null){
            ContentResolver resolver = context.getContentResolver();
            Cursor cursor = null;
            try {
                cursor = resolver.query(ContactsContract.RawContacts.CONTENT_URI, null, Raw.DB.COLUMN.ID + "=?", new String[]{functional.string.from(content.raw.id())}, null);
                if (cursor != null) {
                    if(cursor.getCount()==1){
                        cursor.moveToFirst();
                        content.raw.account(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.ACCOUNT));
                        content.raw.aggregation(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.AGGREGATION));
                        content.raw.contact(functional.android.sql.get.int64(cursor, Raw.DB.COLUMN.CONTACT));
                        content.raw.dataset(functional.android.sql.get.string(cursor, Raw.DB.COLUMN.DATASET));
                        content.raw.deleted(functional.android.sql.get.int64(cursor, Raw.DB.COLUMN.DELETED));
                        content.raw.dirty(functional.android.sql.get.int64(cursor, Raw.DB.COLUMN.DIRTY));
                        content.raw.profile(functional.android.sql.get.int64(cursor, Raw.DB.COLUMN.PROFILE));
                        content.raw.readonly(functional.android.sql.get.int64(cursor, Raw.DB.COLUMN.READONLY));
                        content.raw.unique(functional.android.sql.get.int64(cursor, Raw.DB.COLUMN.UNIQUE));
                        content.raw.name = contact.name(cursor, content.raw.name);
                        content.raw.option = contact.option(cursor, content.raw.option);
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
        return content.raw;
    }
}
