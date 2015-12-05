package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;

public class ResultActivity extends Activity implements View.OnClickListener {

    ListView listView;
    boolean isResultHotel=false,isResultEntertainment=false,isResultRestaurant=false;
    Button returnFromResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //set all booleans of this page equal to previous one
        isResultHotel=SelectActivity.isHotel;
        isResultRestaurant=SelectActivity.isRestaurant;
        isResultEntertainment=SelectActivity.isEntertainment;

        returnFromResult = (Button) this.findViewById(R.id.returnFromResult);
        returnFromResult.setOnClickListener(this);

        //reset all booleans on reaching this page
        SelectActivity.isHotel=false;
        SelectActivity.isRestaurant=false;
        SelectActivity.isEntertainment=false;


        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");
        Cursor cursor = DBOperator.getInstance().execQuery(sql);
        listView=(ListView)this.findViewById(R.id.result_listView);
        listView.setOnItemClickListener(new ItemClickListener());

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.listitem_result, cursor,
                new String[]{"uni_name","rating"}, new int[]{R.id.uni_name, R.id.rating}, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        listView.setAdapter(adapter);

    }

    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.returnFromResult) {
            Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
            startActivity(intent);
        }
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String uni_id = cursor.getString(cursor.getColumnIndex("_id"));
            Toast.makeText(getApplicationContext(), uni_id, Toast.LENGTH_SHORT).show();
            String sql="";
            if(isResultHotel)
            {
                sql= SQLCommand.resultHotel +"'"+ uni_id+"';";
            }
            else if(isResultEntertainment)
            {
                sql = SQLCommand.resultEntertainment +"'"+ uni_id+"';";
            }
            else if(isResultRestaurant)
            {
                sql =SQLCommand.resultRestaurant +"'"+ uni_id+"';";
            }

            Intent intent=new Intent(getApplicationContext(),DescriptionActivity.class);
            intent.putExtra("sql",sql);
            intent.putExtra("uni_id",uni_id);
            startActivity(intent);
        }
    }
}
