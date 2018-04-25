package rebeccapurple.android.contact;

import android.os.Build;
import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {
    public static class DB {
        public static class COLUMN {
            public static class BASE {

                public static final String ID = ContactsContract.Contacts._ID;
                public static final String COUNT = ContactsContract.Contacts._COUNT;
                public static final String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
                public static final String NAME_RAW_CONTACT_ID;
                public static final String PHOTO_ID = ContactsContract.Contacts.PHOTO_ID;
                public static final String PHOTO_FILE_ID = ContactsContract.Contacts.PHOTO_FILE_ID;
                public static final String PHOTO_URI = ContactsContract.Contacts.PHOTO_URI;
                public static final String PHOTO_THUMBNAIL_URI = ContactsContract.Contacts.PHOTO_THUMBNAIL_URI;
                public static final String LOOKUP = ContactsContract.Contacts.LOOKUP_KEY;

                static {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        NAME_RAW_CONTACT_ID = ContactsContract.Contacts.NAME_RAW_CONTACT_ID;
                    } else {
                        NAME_RAW_CONTACT_ID = "name_raw_contact_id";
                    }
                }
            }

            public static class STATUS {
                public static final String CONTACT_PRESENCE = ContactsContract.Contacts.CONTACT_PRESENCE;
                public static final String CONTACT_CHAT_CAPABILITY = ContactsContract.Contacts.CONTACT_CHAT_CAPABILITY;
                public static final String CONTACT_STATUS = ContactsContract.Contacts.CONTACT_STATUS;
                public static final String CONTACT_STATUS_TIMESTAMP = ContactsContract.Contacts.CONTACT_STATUS_TIMESTAMP;
                public static final String CONTACT_STATUS_RES_PACKAGE = ContactsContract.Contacts.CONTACT_STATUS_RES_PACKAGE;
                public static final String CONTACT_STATUS_LABEL = ContactsContract.Contacts.CONTACT_STATUS_LABEL;
                public static final String CONTACT_STATUS_ICON = ContactsContract.Contacts.CONTACT_STATUS_ICON;
            }

            public static class OPTIONS {
                public static final String TIMES_CONTACTED = ContactsContract.Contacts.TIMES_CONTACTED;
                public static final String LAST_TIME_CONTACTED = ContactsContract.Contacts.LAST_TIME_CONTACTED;
                public static final String STARRED = ContactsContract.Contacts.STARRED;
                public static final String RINGTON = ContactsContract.Contacts.CUSTOM_RINGTONE;
                public static final String VOICEMAIL = ContactsContract.Contacts.SEND_TO_VOICEMAIL;
                public static final String PINNED;

                static {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        PINNED = ContactsContract.Contacts.PINNED;
                    } else {
                        PINNED = "pinned";
                    }
                }
            }

            public static class NAME {
                public static final String DISPLAY_NAME_SOURCE = ContactsContract.Contacts.DISPLAY_NAME_SOURCE;
                public static final String DISPLAY_NAME_PRIMARY = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
                public static final String DISPLAY_NAME_ALTERNATIVE = ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE;
                public static final String PHONETIC_NAME_STYLE = ContactsContract.Contacts.PHONETIC_NAME_STYLE;
                public static final String PHONETIC_NAME = ContactsContract.Contacts.PHONETIC_NAME;
            }
        }
    }

    public static class Photo {
        @SerializedName("id") @Expose private Long __id;                        /** {@link DB.COLUMN.BASE#PHOTO_ID} */
        @SerializedName("file") @Expose private Long __file;                    /** {@link DB.COLUMN.BASE#PHOTO_FILE_ID} */
        @SerializedName("uri") @Expose private String __uri;                    /** {@link DB.COLUMN.BASE#PHOTO_URI} */
        @SerializedName("thumbnail") @Expose private String __thumbnail;        /** {@link DB.COLUMN.BASE#PHOTO_THUMBNAIL_URI} */

        public Long id(){ return __id; }
        public Long file(){ return __file; }
        public String uri(){ return __uri; }
        public String thumbnail(){ return __thumbnail; }

        public void id(Long v){ __id = v; }
        public void file(Long v){ __file = v; }
        public void uri(String v){ __uri = v; }
        public void thumbnail(String v){ __thumbnail = v; }
    }

    public static class Base {
        @SerializedName("id") @Expose private Long __id;                        /** {@link DB.COLUMN.BASE#ID} */
        @SerializedName("name") @Expose private String __name;                  /** {@link DB.COLUMN.BASE#DISPLAY_NAME} */
        @SerializedName("raw") @Expose private Long __raw;                      /** {@link DB.COLUMN.BASE#NAME_RAW_CONTACT_ID} */
        @SerializedName("lookup") @Expose private String __lookup;              /** {@link DB.COLUMN.BASE#LOOKUP} */
        @SerializedName("photo") @Expose public Photo photo;

        public void id(Long v){ __id = v; }
        public void name(String v){ __name = v; }
        public void raw(Long v){ __raw = v; }
        public void lookup(String v){ __lookup = v; }

        public Long id(){ return __id; }
        public String name(){ return __name; }
        public Long raw(){ return __raw; }
        public String lookup(){ return __lookup; }

    }

    public static class Status {
        @SerializedName("presence") @Expose private Long __presence;            /** {@link DB.COLUMN.STATUS#CONTACT_PRESENCE} */
        @SerializedName("chattable") @Expose private Long __chattable;          /** {@link DB.COLUMN.STATUS#CONTACT_CHAT_CAPABILITY} */
        @SerializedName("value") @Expose private String __value;                /** {@link DB.COLUMN.STATUS#CONTACT_STATUS} */
        @SerializedName("timestamp") @Expose private Long __timestamp;          /** {@link DB.COLUMN.STATUS#CONTACT_STATUS_TIMESTAMP} */
        @SerializedName("package") @Expose private String __package;            /** {@link DB.COLUMN.STATUS#CONTACT_STATUS_RES_PACKAGE} */
        @SerializedName("label") @Expose private Long __label;                  /** {@link DB.COLUMN.STATUS#CONTACT_STATUS_LABEL} */
        @SerializedName("icon") @Expose private Long __icon;                    /** {@link DB.COLUMN.STATUS#CONTACT_STATUS_ICON} */

        public Long presence(){ return __presence; }
        public Long chattable(){ return __chattable; }
        public String value(){ return __value; }
        public Long timestamp(){ return __timestamp; }
        public String _package(){ return __package; }
        public Long label(){ return __label; }
        public Long icon(){ return __icon; }

        public void presence(Long v){ __presence = v; }
        public void chattable(Long v){ __chattable = v; }
        public void value(String v){ __value = v; }
        public void timestamp(Long v){ __timestamp = v; }
        public void _package(String v){ __package = v; }
        public void label(Long v){ __label = v; }
        public void icon(Long v){ __icon = v; }
    }

    public static class Option {
        @SerializedName("hit") @Expose private Long __hit;                      /** {@link DB.COLUMN.OPTIONS#TIMES_CONTACTED} */
        @SerializedName("contacted") @Expose private Long __contacted;          /** {@link DB.COLUMN.OPTIONS#LAST_TIME_CONTACTED} */
        @SerializedName("starred") @Expose private Long __starred;              /** {@link DB.COLUMN.OPTIONS#STARRED} */
        @SerializedName("pinned") @Expose private Long __pinned;                /** {@link DB.COLUMN.OPTIONS#PINNED} */
        @SerializedName("rington") @Expose private String __rington;            /** {@link DB.COLUMN.OPTIONS#RINGTON} */
        @SerializedName("voicemail") @Expose private Long __voicemail;          /** {@link DB.COLUMN.OPTIONS#VOICEMAIL} */

        public Long hit(){ return __hit; }
        public Long contacted(){ return __contacted; }
        public Long starred(){ return __starred; }
        public Long pinned(){ return __pinned; }
        public String rington(){ return __rington; }
        public Long voicemail(){ return __voicemail; }

        public void hit(Long v){ __hit = v; }
        public void contacted(Long v){ __contacted = v; }
        public void starred(Long v){ __starred = v; }
        public void pinned(Long v){ __pinned = v; }
        public void rington(String v){ __rington = v; }
        public void voicemail(Long v){ __voicemail = v; }
    }

    public static class Phonetic {
        @SerializedName("style") @Expose private String __style;                /** {@link DB.COLUMN.NAME#PHONETIC_NAME_STYLE} */
        @SerializedName("name") @Expose private String __name;                  /** {@link DB.COLUMN.NAME#PHONETIC_NAME} */

        public void style(String v){ __style = v; }
        public void name(String v){ __name = v; }

        public String style(){ return __style; }
        public String name(){ return __name; }
    }

    public static class Name {
        @SerializedName("source") @Expose private String __source;              /** {@link DB.COLUMN.NAME#DISPLAY_NAME_SOURCE} */
        @SerializedName("primary") @Expose private String __primary;            /** {@link DB.COLUMN.NAME#DISPLAY_NAME_PRIMARY} */
        @SerializedName("alternative") @Expose private String __alternative;    /** {@link DB.COLUMN.NAME#DISPLAY_NAME_ALTERNATIVE} */
        @SerializedName("phonetic") @Expose public Phonetic phonetic;

        public void source(String v){ __source = v; }
        public void primary(String v){ __primary = v; }
        public void alternative(String v){ __alternative = v; }

        public String source(){ return __source; }
        public String primary(){ return __primary; }
        public String alternative(){ return __alternative; }
    }

    @SerializedName("base")   @Expose public Base base;
    @SerializedName("status") @Expose public Status status;
    @SerializedName("option") @Expose public Option option;
    @SerializedName("name")   @Expose public Name name;
}
