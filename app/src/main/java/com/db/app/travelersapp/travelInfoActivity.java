package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;
import com.db.app.travelersapp.view.TableView;

public class travelInfoActivity extends Activity {

    ListView listView;
    TableLayout tl;
    ScrollView sv;
    private String uni_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);

        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");
        uni_id=intent.getStringExtra("uni_id");
        Cursor cursor = DBOperator.getInstance().execQuery(sql);

        listView = (ListView) this.findViewById(R.id.transportInfo_listview);
        listView.setOnItemClickListener(new ItemClickListener());
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.listitem_transportinfo, cursor,
                new String[]{"trans_type","start_point"}, new int[]{R.id.trans_type, R.id.start_point}, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        listView.setAdapter(adapter);
        tl=(TableLayout)this.findViewById(R.id.travelInfo_table);
        //sv=(ScrollView)this.findViewById(R.id.temp_sv);

    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String trans_id = cursor.getString(cursor.getColumnIndex("_id"));


            Display display = getWindowManager().getDefaultDisplay();Point size=new Point();
            display.getSize(size);int displayWidth=size.x;

            String sql=SQLCommand.travelinfo+"'"+uni_id+"' and t.trans_id="+"'"+trans_id+"';";
            Cursor cursor2 = DBOperator.getInstance().execQuery(sql);

            if(cursor2.getCount()>0)
            asd(cursor2,displayWidth);

            Toast.makeText(getApplicationContext(), sql+" ||"+cursor2.getCount(), Toast.LENGTH_SHORT).show();
        }

    }

    private void asd(Cursor cursor2,int displayWidth) {
        if (cursor2 != null) {

            tl.removeAllViewsInLayout();
            tl=(TableLayout)this.findViewById(R.id.travelInfo_table);
            cursor2.moveToFirst();
            int no_of_cols = cursor2.getColumnCount();

            for (int i = 0; i < no_of_cols; i++) {
                TableRow tr = new TableRow(getApplicationContext());
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tr.setLayoutParams(lp);
                TextView leftRowTextView = new TextView(getApplicationContext());
                TextView rightRowTextView = new TextView(getApplicationContext());
                LinearLayout leftLL = new LinearLayout(getApplicationContext());
                LinearLayout rightLL = new LinearLayout(getApplicationContext());
                leftRowTextView.setText(cursor2.getColumnName(i));
                rightRowTextView.setText(cursor2.getString(i));
                leftRowTextView.setWidth(Math.round(displayWidth * .2f));
                rightRowTextView.setMaxWidth(Math.round(displayWidth * 0.8f));

                leftRowTextView.setSingleLine(false);
                rightRowTextView.setSingleLine(false);
                rightRowTextView.setPadding(0, 0, 50, 0);

                leftLL.addView(leftRowTextView);
                rightLL.addView(rightRowTextView);
                tr.addView(leftLL);
                tr.addView(rightLL);
                tl.addView(tr, i);
            }
            cursor2.close();
        }
    }

}



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_travel_info, menu);
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
*/