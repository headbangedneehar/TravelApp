package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends Activity implements View.OnClickListener{

    private Button welcome;
    public static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome = (Button) this.findViewById(R.id.Welcome_Page_Btn);
        welcome.setOnClickListener(this);
        PACKAGE_NAME = getApplicationContext().getPackageName();
    }

    @Override
    public void onClick(View v)
    {
        int id=v.getId();
        if (id== R.id.Welcome_Page_Btn){
            Intent intent = new Intent(this, HomeActivity.class);
            this.startActivity(intent);
        }
    }
}