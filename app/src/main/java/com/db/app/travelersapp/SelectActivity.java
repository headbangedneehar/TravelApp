package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;
import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;


public class SelectActivity extends Activity implements View.OnClickListener{
    Button goHotel_btn,goEntertainment_btn,goRestauant_btn, emergencyButton;
    ScrollView scrollView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

        goHotel_btn=(Button)this.findViewById(R.id.goHotel_btn);
        goHotel_btn.setOnClickListener(this);
        goEntertainment_btn=(Button)this.findViewById(R.id.goEntertainment_btn);
        goEntertainment_btn.setOnClickListener(this);
        goRestauant_btn=(Button)this.findViewById(R.id.goRestauant_btn);
        goRestauant_btn.setOnClickListener(this);
        emergencyButton=(Button)this.findViewById(R.id.goEmergency_btn);
        emergencyButton.setOnClickListener(this);
        //scrollView=(ScrollView)this.findViewById(R.id.tempscrollview);
    }

    public void onClick(View v) {
        String sql="";
        int id=v.getId();
        if(id==R.id.goHotel_btn){
            //scrollView.removeAllViews();
            sql= SQLCommand.getHotel;
            getSQL(sql, ResultActivity.class);
            //Cursor cursor=DBOperator.getInstance().execQuery(sql);
            //scrollView.addView(new TableView(this.getBaseContext(),cursor));
        } else if(id==R.id.goEntertainment_btn) {
            getSQL(SQLCommand.getEntertainment, ResultActivity.class);  //Change this to entertainment query

        } else if(id == R.id.goRestauant_btn) {
            getSQL(SQLCommand.getRestaurant, ResultActivity.class);  //Change this to entertainment query
        } else if(id == R.id.goEmergency_btn) {
            Toast.makeText(getBaseContext(), "emergency clicked",
                    Toast.LENGTH_SHORT).show();
            getSQL(SQLCommand.getEmergency, EmergencyActivity.class);
        }

    }

    private void getSQL(String sql, Class intentClass) {
        Intent intent=this.getIntent();
        String destination = intent.getStringExtra("Destination");
        Intent intent2= new Intent(getApplicationContext(),intentClass);//(this,hreresult...)

                sql=sql+"'"+destination+"' GROUP BY u.uni_name,u.uni_id ORDER BY c.rating desc;";


        intent2.putExtra("sql", sql);
        startActivity(intent2);
        Toast.makeText(getBaseContext(), sql,
                Toast.LENGTH_SHORT).show();

    }
}
