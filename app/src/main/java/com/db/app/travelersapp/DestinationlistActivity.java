package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;
import com.db.app.travelersapp.view.TableView;

public class DestinationlistActivity extends Activity implements View.OnClickListener {

    ListView listView;
    Button continue_btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_destinationlist);

        Intent intent = this.getIntent();
        String regionName = intent.getStringExtra("Region");
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.getDestination+"'"+regionName.toUpperCase()+"';");
        listView=(ListView)this.findViewById(R.id.dest_listView);
        listView.setOnItemClickListener(new ItemClickListener());

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.listitem_dest, cursor,
                new String[]{"dest_name"}, new int[]{R.id.dest_name}, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        listView.setAdapter(adapter);
    }
    @Override
    public void onClick(View v){
        int id=v.getId();


    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String destination = cursor.getString(1);
            Toast.makeText(getApplicationContext(), "Clickedd "+destination, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
            intent.putExtra("Destination", destination);
            startActivity(intent);

        }
    }
}

