package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;


public class SelectActivity extends Activity implements View.OnClickListener{
    Button goHotel_btn,goEntertainment_btn,goRestauant_btn, emergencyButton, returnButton;

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

        returnButton = (Button) this.findViewById(R.id.goReturn_btn);
        returnButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        String sql="";
        int id=v.getId();
        if(id==R.id.goHotel_btn){
            //scrollView.removeAllViews();
            sql= SQLCommand.getHotel;
            getSQL(sql, ResultActivity.class);

        } else if(id==R.id.goEntertainment_btn) {
            getSQL(SQLCommand.getEntertainment, ResultActivity.class);

        } else if(id == R.id.goRestauant_btn) {
            getSQL(SQLCommand.getRestaurant, ResultActivity.class);

        } else if(id == R.id.goEmergency_btn) {
            getSQL(SQLCommand.getEmergency, EmergencyActivity.class);

        } else if(id == R.id.goReturn_btn){
            Intent intent= new Intent(this,HomeActivity.class);
            startActivity(intent);
        }

    }

    private void getSQL(String sql, Class intentClass) {
        Intent intent=this.getIntent();

        Intent intent2= new Intent(getApplicationContext(),intentClass);
        intent2.putExtra("sql", sql);
        intent2.putExtra("Destination",intent.getStringExtra("Destination"));
        startActivity(intent2);

    }
}
