package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;
import com.db.app.travelersapp.view.TableView;

public class DestinationlistActivity extends Activity implements View.OnClickListener {

    ListView listView;
    Button returnFromDest;
    TextView textView;
    static String regionName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_destinationlist);

        returnFromDest = (Button) this.findViewById(R.id.returnFromDest);
        returnFromDest.setOnClickListener(this);

        Intent intent = this.getIntent();
        regionName = intent.getStringExtra("Region");
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.getDestination, this.getArgs(regionName.toUpperCase()));
        listView=(ListView)this.findViewById(R.id.dest_listView);
        listView.setOnItemClickListener(new ItemClickListener());

        textView = (TextView) this.findViewById(R.id.videoLink);
        if(regionName.toUpperCase().equalsIgnoreCase("CALIFORNIA")) {
            textView.setText("http://www.visitcalifornia.com/");
            textView.setTextColor(Color.BLACK);
        } else if(regionName.toUpperCase().equalsIgnoreCase("MASSACHUSETTS")) {
            textView.setText("http://www.massvacation.com/");
            textView.setTextColor(Color.BLACK);
        }

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.listitem_dest, cursor,
                new String[]{"dest_name"}, new int[]{R.id.dest_name}, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        listView.setAdapter(adapter);
    }
    @Override
    public void onClick(View v){
        int id=v.getId();
        if (id == R.id.returnFromDest) {

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }


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

    private String[] getArgs(String region) {
        String args[] = new String[1];
        args[0] = region;
        return args;
    }
}

