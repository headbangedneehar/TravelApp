package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        //set it back to false

        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");
        String dest = intent.getStringExtra("Destination");
        Cursor cursor = DBOperator.getInstance().execQuery(sql, this.getArgs(dest));
        listView=(ListView)this.findViewById(R.id.emer_listView);
        //listView.setOnItemClickListener(new ItemClickListener());

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.listitem_emer, cursor,
                new String[]{"emer_name", "emer_addr", "emer_contact"}, new int[]{R.id.emer_name,R.id.emer_addr, R.id.emer_contact}, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.returnButton){
            Intent intent = new Intent(this, SelectActivity.class);
            this.startActivity(intent);
        }
    }

    private String[] getArgs(String dest) {
        String[] args = new String[1];
        args[0] = dest;
        return args;
    }
}
