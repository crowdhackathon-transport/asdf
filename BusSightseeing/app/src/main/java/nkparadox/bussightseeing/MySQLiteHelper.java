package nkparadox.bussightseeing;

/**
 * Created by Amsoir on 18.7.15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_ROUTES = "routes";
    public static final String TABLE_STOPS = "stops";
    public static final String TABLE_STOP_TIMES = "stopTimes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STOP = "stop";

    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LON = "lon";

    public static final String COLUMN_ROUTESHORTNAME = "routeShortName";
    public static final String COLUMN_ROUTELONGNAME = "routeLongName";

    public static String DB_PATH = "/data/data/nkparadox.bussightseeing/databases/";



    public static final String DATABASE_NAME = "asdf.db";
    private static final int DATABASE_VERSION = 1;




    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
