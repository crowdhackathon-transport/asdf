package nkparadox.bussightseeing;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class StopListActivity extends ListActivity {
    private CommentsDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);
        String bus ="";


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bus = extras.getString("BUS");
        }


        datasource = new CommentsDataSource(this);
        datasource.open();


        List<stopItem> results = datasource.runBusQuery(bus);


        // use the SimpleCursorAdapter to show the
        // elements in a ListView

        ArrayAdapter<stopItem> adapter = new ArrayAdapter<stopItem>(this,
                android.R.layout.simple_list_item_1, results);
        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                stopItem value =  (stopItem) adapter.getItemAtPosition(position);
                String theId = String.valueOf(value.getId());
                String theName = String.valueOf(value.getStop());
                String theLat = String.valueOf(value.getLat());
                String theLon = String.valueOf(value.getLon());
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, theLon, duration);
                //toast.show();

                Intent intent = new Intent(getApplicationContext(), PlacePickerActivity.class);
                Bundle bund = new Bundle();
                bund.putDouble("lat",value.getLat());
                bund.putDouble("lon",value.getLon());
                intent.putExtra("ID", theId);
                intent.putExtra("STOP", theName);
                //intent.putExtra( "LAT", theLat );
                //intent.putExtra("LON", theLon);
                intent.putExtras(bund);
                startActivity(intent);


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stop_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
