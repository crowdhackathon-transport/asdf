package nkparadox.bussightseeing;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;

    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ROUTESHORTNAME,
            MySQLiteHelper.COLUMN_ROUTELONGNAME };

    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }



    public List<routeItem> runQuery(String lat, String lon) {

        List<routeItem> comments = new ArrayList<routeItem>();

        Cursor cursor1 = database.rawQuery("select distinct routes.routeLongName , routes.routeShortName from routes, stops, stopTimes" +
                " where  stops.id = stopTimes.id     and routes.routeShortName = stopTimes.routeShortName  " +
                " and ((lat - ? ) * (lat - ?) + (lon - ?) * (lon - ?)) < 0.0006;", new String[] {lat,lat,lon, lon});

        //Cursor cursor = database.query(MySQLiteHelper.TABLE_ROUTES,
        //        allColumns, null, null, null, null, null);

        cursor1.moveToFirst();
        while (!cursor1.isAfterLast()) {
            routeItem comment = cursorToRoute(cursor1);
            comments.add(comment);
            cursor1.moveToNext();
        }
        // make sure to close the cursor
        cursor1.close();
        return comments;

    };

    public List<stopItem> runBusQuery(String bus) {

        List<stopItem> comments = new ArrayList<stopItem>();

        Cursor cursor = database.rawQuery(
                " select stops.id , stops.stop , stops.lat , stops.lon from stops,stopTimes\n" +
                "  where stops.id = stopTimes.id\n" +
                " and stopTimes.routeShortName = ? ", new String[] {bus});

        //Cursor cursor = database.query(MySQLiteHelper.TABLE_ROUTES,
        //        allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stopItem comment = cursorToStop(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }




        cursor.close();
        return comments;

    };

    public List<routeItem> testQ() {

        List<routeItem> routeI = new ArrayList<routeItem>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_ROUTES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            routeItem temp = cursorToRoute(cursor);
            routeI.add(temp);
            cursor.moveToNext();
        }

        cursor.close();
        return routeI;

    };



    private routeItem cursorToRoute(Cursor cursor) {
        routeItem route = new routeItem();
        route.setRouteLongName(cursor.getString(0));
        route.setRouteShortName(cursor.getString(1));
        return route;
    }

    private stopItem cursorToStop(Cursor cursor) {
        stopItem stop = new stopItem();
        stop.setId(Integer.parseInt(cursor.getString(0)));
        stop.setStop(cursor.getString(1));
        stop.setLat(Double.parseDouble(cursor.getString(2)));
        stop.setLon(Double.parseDouble(cursor.getString(3)));
        return stop;
    }


}

