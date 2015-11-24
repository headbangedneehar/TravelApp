package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

//import project.db.travelapp.R;
//import com.db.travel.app.v1.view.TableView;
import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;
/*import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.db.app.travelersapp.util.DBOperator;*/

public class SelectActivity extends Activity implements View.OnClickListener{
    Button goHotel_btn,goEntertainment_btn,goRestauant_btn;
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
        //scrollView=(ScrollView)this.findViewById(R.id.tempscrollview);
    }

    public void onClick(View v) {
        String sql="";
        int id=v.getId();
        if(id==R.id.goHotel_btn){
            //scrollView.removeAllViews();
            sql= SQLCommand.get_hotel;
            getSQL(sql);
            //Cursor cursor=DBOperator.getInstance().execQuery(sql);
            //scrollView.addView(new TableView(this.getBaseContext(),cursor));
        } else if(id==R.id.goEntertainment_btn) {
            getSQL(SQLCommand.dummy);  //Change this to entertainment query

        }else if(id == R.id.goEmergency_btn) {

        }

    }

    private void getSQL(String sql) {
        Intent intent=this.getIntent();
        String RegionDestText = intent.getStringExtra("RegionDestText");
        Intent intent2= new Intent(getApplicationContext(),ResultActivity.class);//(this,hreresult...)
        if(!RegionDestText.contains(","))
        {
            sql=sql+" and region.reg_name= '"+RegionDestText+"';";
        }
        else
        {
            int commaAt=RegionDestText.indexOf(",");
            String reg= RegionDestText.substring(0, commaAt);
            String dest= RegionDestText.substring(commaAt+1);
            if(!reg.isEmpty())
            {
                sql=sql+" and region.reg_name='"+reg+"' and destination.dest_name='"+dest+"';";
            }
            else
            {
                sql=sql+"'"+dest+"';";
            }
        }
        intent2.putExtra("sql", sql);
        startActivity(intent2);
        Toast.makeText(getBaseContext(), sql,
                Toast.LENGTH_SHORT).show();

    }
}
