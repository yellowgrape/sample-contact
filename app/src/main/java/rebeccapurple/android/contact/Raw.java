package rebeccapurple.android.contact;

import android.os.Build;
import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Raw {
    public static class DB {
        public static class COLUMN {
            public static final String ID = ContactsContract.RawContacts._ID;
            public static final String COUNT = ContactsContract.RawContacts._COUNT;
            public static final String CONTACT = ContactsContract.RawContacts.CONTACT_ID;
            public static final String UNIQUE;
            public static final String DATASET = ContactsContract.RawContacts.DATA_SET;
            public static final String ACCOUNT;
            public static final String AGGREGATION = ContactsContract.RawContacts.AGGREGATION_MODE;
            public static final String DELETED = ContactsContract.RawContacts.DELETED;
            public static final String READONLY = ContactsContract.RawContacts.RAW_CONTACT_IS_READ_ONLY;
            public static final String PROFILE = ContactsContract.RawContacts.RAW_CONTACT_IS_USER_PROFILE;
            public static final String DIRTY;

            static {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ACCOUNT = ContactsContract.RawContacts.ACCOUNT_TYPE_AND_DATA_SET;
                } else {
                    ACCOUNT = "account_type_and_data_set";
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    UNIQUE = ContactsContract.RawContacts.BACKUP_ID;
                    DIRTY = ContactsContract.RawContacts.METADATA_DIRTY;
                } else {
                    UNIQUE = "backup_id";
                    DIRTY = "metadata_dirty";
                }
            }
            public static class SYNC {
                public static final String FIRST = ContactsContract.RawContacts.SYNC1;
                public static final String SECOND = ContactsContract.RawContacts.SYNC2;
                public static final String THIRD = ContactsContract.RawContacts.SYNC3;
                public static final String FOURTH = ContactsContract.RawContacts.SYNC4;
                public static final String NAME = ContactsContract.RawContacts.ACCOUNT_NAME;
                public static final String TYPE = ContactsContract.RawContacts.ACCOUNT_TYPE;
                public static final String SOURCE = ContactsContract.RawContacts.SOURCE_ID;
                public static final String VERSION = ContactsContract.RawContacts.VERSION;
                public static final String DIRTY = ContactsContract.RawContacts.DIRTY;
            }
        }
    }

    public static class Sync {
        @SerializedName("data") @Expose public List<String> data;
        @SerializedName("name") @Expose private String __name;
        @SerializedName("type") @Expose private String __type;
        @SerializedName("source") @Expose private String __source;
        @SerializedName("version") @Expose private Long __version;
        @SerializedName("dirty") @Expose private Long __dirty;

        public void name(String v){ __name = v; }
        public void type(String v){ __type = v; }
        public void source(String v){ __source = v; }
        public void version(Long v){ __version = v; }
        public void dirty(Long v){ __dirty = v; }

        public String name(){ return __name; }
        public String type(){ return __type; }
        public String source(){ return __source; }
        public Long version(){ return __version; }
        public Long dirty(){ return __dirty; }
    }

    @SerializedName("id") @Expose private Long __id;
    @SerializedName("contact") @Expose private Long __contact;
    @SerializedName("unique") @Expose private Long __unique;
    @SerializedName("dataset") @Expose private String __dataset;
    @SerializedName("account") @Expose private String __account;
    @SerializedName("aggregation") @Expose private String __aggregation;
    @SerializedName("deleted") @Expose private Long __deleted;
    @SerializedName("readonly") @Expose private Long __readonly;
    @SerializedName("profile") @Expose private Long __profile;
    @SerializedName("dirty") @Expose private Long __dirty;

    @SerializedName("sync") @Expose public Sync sync;
    @SerializedName("option") @Expose public Content.Option option;
    @SerializedName("name") @Expose public Content.Name name;

    public void id(Long v){ __id = v; }
    public void contact(Long v){ __contact = v; }
    public void unique(Long v){ __unique = v; }
    public void dataset(String v){ __dataset = v; }
    public void account(String v){ __account = v; }
    public void aggregation(String v){ __aggregation = v; }
    public void deleted(Long v){ __deleted = v; }
    public void readonly(Long v){ __readonly = v; }
    public void profile(Long v){ __profile = v; }
    public void dirty(Long v){ __dirty = v; }

    public Long id(){ return __id; }
    public Long contact(){ return __contact; }
    public Long unique(){ return __unique; }
    public String dataset(){ return __dataset; }
    public String account(){ return __account; }
    public String aggregation(){ return __aggregation; }
    public Long deleted(){ return __deleted; }
    public Long readonly(){ return __readonly; }
    public Long profile(){ return __profile; }
    public Long dirty(){ return __dirty; }
}
