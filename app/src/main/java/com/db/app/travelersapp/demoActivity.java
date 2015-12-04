package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;

public class demoActivity extends Activity {

    TableLayout tl;
    String uni_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");
        uni_id=intent.getStringExtra("uni_id");
        Cursor cursor = DBOperator.getInstance().execQuery(sql);

        tl=(TableLayout)this.findViewById(R.id.demotable);


        Display display = getWindowManager().getDefaultDisplay();Point size=new Point();
        display.getSize(size);int displayWidth=size.x;

        if(cursor!=null && cursor.moveToFirst())
            do {
                int no_of_cols = cursor.getColumnCount();
                for (int i = 0; i < no_of_cols; i++) {
                    TableRow tr = new TableRow(this);
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    tr.setLayoutParams(lp);
                    TextView leftRowTextView = new TextView(getApplicationContext());//previously final
                    TextView rightRowTextView = new TextView(this);
                    LinearLayout leftLL = new LinearLayout(this);
                    LinearLayout rightLL = new LinearLayout(this);
                    leftRowTextView.setText(cursor.getColumnName(i));
                    rightRowTextView.setText(cursor.getString(i));
                    leftRowTextView.setWidth(Math.round(displayWidth * .2f));
                    rightRowTextView.setMaxWidth(Math.round(displayWidth * 0.8f));

                    leftRowTextView.setSingleLine(false);
                    rightRowTextView.setSingleLine(false);
                    rightRowTextView.setPadding(0, 0, 50, 0);

                    leftLL.addView(leftRowTextView);
                    rightLL.addView(rightRowTextView);
                    tr.addView(leftLL);
                    tr.addView(rightLL);
                    if((i+1)%5==0)
                    {
                        tr.setPadding(0,0,0,30);
                    }
                    tl.addView(tr, i);
                }


            }
            while(cursor.moveToNext());
        cursor.close();
    }

}
