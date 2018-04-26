package rebeccapurple.android.contact;

import android.os.Build;
import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    public static class DB {
        public static class COLUMN {
            public static final String ID = ContactsContract.Data._ID;
            public static final String COUNT = ContactsContract.Data._COUNT;
            public static final String PACKAGE;
            public static final String MIMETYPE = ContactsContract.Data.MIMETYPE;
            public static final String RAW = ContactsContract.Data.RAW_CONTACT_ID;
            public static final String PRIMARY = ContactsContract.Data.IS_PRIMARY;
            public static final String SUPER = ContactsContract.Data.IS_SUPER_PRIMARY;
            public static final String READONLY = ContactsContract.Data.IS_READ_ONLY;
            public static final String VERSION = ContactsContract.Data.DATA_VERSION;
            public static final String DATA1 = ContactsContract.Data.DATA1;
            public static final String DATA2 = ContactsContract.Data.DATA2;
            public static final String DATA3 = ContactsContract.Data.DATA3;
            public static final String DATA4 = ContactsContract.Data.DATA4;
            public static final String DATA5 = ContactsContract.Data.DATA5;
            public static final String DATA6 = ContactsContract.Data.DATA6;
            public static final String DATA7 = ContactsContract.Data.DATA7;
            public static final String DATA8 = ContactsContract.Data.DATA8;
            public static final String DATA9 = ContactsContract.Data.DATA9;
            public static final String DATA10 = ContactsContract.Data.DATA10;
            public static final String DATA11 = ContactsContract.Data.DATA11;
            public static final String DATA12 = ContactsContract.Data.DATA12;
            public static final String DATA13 = ContactsContract.Data.DATA13;
            public static final String DATA14 = ContactsContract.Data.DATA14;
            public static final String DATA15 = ContactsContract.Data.DATA15;
            public static final String SYNC1 = ContactsContract.Data.SYNC1;
            public static final String SYNC2 = ContactsContract.Data.SYNC2;
            public static final String SYNC3 = ContactsContract.Data.SYNC3;
            public static final String SYNC4 = ContactsContract.Data.SYNC4;
            public static final String CARRIER;

            static {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    CARRIER = ContactsContract.Data.CARRIER_PRESENCE;
                } else {
                    CARRIER = "carrier_presence";
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    PACKAGE = ContactsContract.Data.RES_PACKAGE;
                } else {
                    PACKAGE = "res_package";
                }
            }
        }
    }

    @SerializedName("id") @Expose private Long __id;
    @SerializedName("package") @Expose private String __package;
    @SerializedName("mimetype") @Expose private String __mimetype;
    @SerializedName("primary") @Expose private Long __primary;
    @SerializedName("super") @Expose private Long __super;
    @SerializedName("readonly") @Expose private Long __readonly;
    @SerializedName("data") @Expose public List<String> data;
    private byte[] __blob;
    @SerializedName("carrier") @Expose private Long __carrier;

    public void id(Long v){ __id = v; }
    public void _package(String v){ __package = v; }
    public void mimetype(String v){ __mimetype = v; }
    public void primary(Long v){ __primary = v; }
    public void _super(Long v){ __super = v; }
    public void readonly(Long v){ __readonly = v; }
    public void blob(byte[] v){ __blob = v; }

    public Long id(){ return __id; }
    public String _package(){ return __package; }
    public String mimetype(){ return __mimetype; }
    public Long primary(){ return __primary; }
    public Long _super(){ return __super; }
    public Long readonly(){ return __readonly; }
    public byte[] blob(){ return __blob; }
}
