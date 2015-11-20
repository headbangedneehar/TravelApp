package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Nupurb on 20-11-2015.
 */
public class EmergencyActivity extends Activity implements View.OnClickListener{
    Button returnBtn;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        list = (ListView) this.findViewById(R.id.emer_listView);
        returnBtn = (Button) this.findViewById(R.id.returnButton);
        returnBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.returnButton){
            Intent intent = new Intent(this, SelectActivity.class);
            this.startActivity(intent);
        }
    }
}
