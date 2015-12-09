package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.db.app.travelersapp.util.DBOperator;

/**
 * Created by Nupurb on 20-11-2015.
 */
public class EmergencyActivity extends Activity implements View.OnClickListener{
    Button returnBtn;
    ListView listView;
    static String dest, sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        //set it back to false
        returnBtn = (Button) this.findViewById(R.id.returnFromEmer);
        returnBtn.setOnClickListener(this);
        Intent intent = this.getIntent();
        sql = intent.getStringExtra("sql");
        dest = intent.getStringExtra("Destination");
        Cursor cursor = DBOperator.getInstance().execQuery(sql, this.getArgs(dest));
        listView=(ListView)this.findViewById(R.id.emer_listView);
        listView.setOnItemClickListener(new ItemClickListener());

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.listitem_emer, cursor,
                new String[]{"emer_name", "emer_addr", "emer_contact"}, new int[]{R.id.emer_name,R.id.emer_addr, R.id.emer_contact}, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        listView.setAdapter(adapter);
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            //String uni_id = cursor.getString(cursor.getColumnIndex("_id"));

            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cursor.getString(3)));
            startActivity(intent);

        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.returnFromEmer){
            Intent intent = new Intent(this, SelectActivity.class);
            intent.putExtra("Destination", dest);
            this.startActivity(intent);
        }
    }

    private String[] getArgs(String dest) {
        String[] args = new String[1];
        args[0] = dest;
        return args;
    }
}
