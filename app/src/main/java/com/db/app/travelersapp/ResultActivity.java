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
import com.db.app.travelersapp.util.DBOperator;

public class ResultActivity extends Activity implements View.OnClickListener {

    ListView listView;
    Button returnFromResult;
    static String dest, sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        returnFromResult = (Button) this.findViewById(R.id.returnFromResult);
        returnFromResult.setOnClickListener(this);

        Intent intent = this.getIntent();
        sql = intent.getStringExtra("sql");
        dest = intent.getStringExtra("Destination");
        Cursor cursor = DBOperator.getInstance().execQuery(sql,getArgs(dest));
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
            intent.putExtra("Destination", dest);
            startActivity(intent);
        }
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String uni_id = cursor.getString(cursor.getColumnIndex("_id"));
            Intent intent=new Intent(getApplicationContext(),DescriptionActivity.class);
            intent.putExtra("Destination",dest);
            intent.putExtra("sql",sql);
            intent.putExtra("uni_id",uni_id);
            startActivity(intent);
        }
    }

    private String[] getArgs(String dest) {
        String args[] = new String[1];
        args[0] = dest;
        return args;
    }
}
