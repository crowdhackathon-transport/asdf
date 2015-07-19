package nkparadox.bussightseeing;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class RouteActivity extends ListActivity {
    private CommentsDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        String latitude = "";
        String longtitude = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            latitude = extras.getString("LATIT");
            longtitude = extras.getString("LONG");
        }

        AssetManager mgr = getAssets();

        InputStream is = null;
        try {
            is = mgr.open(MySQLiteHelper.DATABASE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String outFileName = MySQLiteHelper.DB_PATH + MySQLiteHelper.DATABASE_NAME;
        OutputStream myOutput = null;

        try {
            myOutput = new FileOutputStream(outFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {


        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        datasource = new CommentsDataSource(this);
        datasource.open();


        List<routeItem> results = datasource.runQuery(latitude, longtitude);

      // List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView

        ArrayAdapter<routeItem> adapter = new ArrayAdapter<routeItem>(this,
                android.R.layout.simple_list_item_1, results);
        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                routeItem value =  (routeItem) adapter.getItemAtPosition(position);
                String toGo = value.getRouteShortName();

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, toGo, duration);
                //toast.show();

                Intent intent = new Intent(getApplicationContext(), StopListActivity.class);
                intent.putExtra("BUS", toGo);
                startActivity(intent);

            }
        });

    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml


    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}
