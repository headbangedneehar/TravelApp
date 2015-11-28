package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;

//import com.db.app.travelersapp.R;


public class HomeActivity extends Activity implements View.OnClickListener {

    Button searchBtn;
    EditText region, destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        region = (EditText) this.findViewById(R.id.region_editText);
        destination = (EditText) this.findViewById(R.id.dest_editText);
        searchBtn = (Button) this.findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(this);

        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v)
    {

        /*Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_2,
                this.getArgs(true));

        while(cursor.moveToNext()) {
            int regId = Integer.parseInt(cursor.getString(0));
            System.out.println("Reg Id = "+regId);
        }*/
        int id=v.getId();

        if (id==R.id.search_btn){

            if(!region.getText().toString().isEmpty() && destination.getText().toString().isEmpty()) //destination is empty
            {
                Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.checkRegion+"'"+region.getText().toString().toUpperCase()+"';");
                Toast.makeText(getBaseContext(), "After region query " + cursor.getCount(),
                        Toast.LENGTH_SHORT).show();
                if(cursor.getCount() > 0)
                {
                    String inputRegion=region.getText().toString();
                    Intent intent = new Intent(this, DestinationlistActivity.class); //Need to put the DestList activity class of the next page
                    intent.putExtra("Region",inputRegion);
                    this.startActivity(intent);
                    Toast.makeText(getBaseContext(), String.valueOf(cursor.getCount()),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Region Not Found",
                            Toast.LENGTH_SHORT).show();
                }
            }
            else if(region.getText().toString().isEmpty() && destination.getText().toString().isEmpty()) //both are empty
            {
                Toast.makeText(getBaseContext(), "Blank Region and Destination! Not Valid",
                        Toast.LENGTH_SHORT).show();
            } else if(!destination.getText().toString().isEmpty()) {
                Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.checkDest + "'" + destination.getText().toString() + "'");
                if (cursor.getCount() > 0)//to see if there exists at least 1 row with the result, that is region and destination must be valid to be true
                {
                    String inputDest = destination.getText().toString();
                    Intent intent = new Intent(this, SelectActivity.class); //Need to put the activity class of the next page
                    intent.putExtra("Destination", inputDest);
                    this.startActivity(intent);
                    Toast.makeText(getBaseContext(), String.valueOf(cursor.getCount()),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Region/Destination Not Found",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String[] getArgs(boolean isSearch) {
        String[] args = null;
        if (isSearch) {
            args = new String[1];
            // get region
            args[0] = region.getText().toString();
            // get destination
            //args[1] = destText.getText().toString();

        }
        return args;
    }
}
