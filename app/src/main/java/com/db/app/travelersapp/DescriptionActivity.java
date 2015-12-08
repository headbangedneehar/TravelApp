package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;
import com.db.app.travelersapp.util.Pair;
import com.db.app.travelersapp.view.ChartGenerator;
import java.util.LinkedList;
import java.util.List;



public class DescriptionActivity extends Activity implements View.OnClickListener {
    //768 is display width
    private static String uni_id, dest, sql;
    Button travelInfoButton, ratingButton, returnButton;
    static TableLayout tl,desc_table;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        travelInfoButton = (Button) this.findViewById(R.id.travelinfo_button);
        travelInfoButton.setOnClickListener(this);
        ratingButton = (Button) this.findViewById(R.id.rating_btn);
        ratingButton.setOnClickListener(this);
        returnButton = (Button) this.findViewById(R.id.returnFromDesc);
        returnButton.setOnClickListener(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int displayWidth = size.x;


        Intent intent = this.getIntent();
        uni_id = intent.getStringExtra("uni_id");
        dest = intent.getStringExtra("Destination");
        sql = intent.getStringExtra("sql");

        if(uni_id.contains("h")) {
            cursor = DBOperator.getInstance().execQuery(SQLCommand.resultHotel, this.getHotel(uni_id));
        } else if(uni_id.contains("r")) {
            cursor = DBOperator.getInstance().execQuery(SQLCommand.resultRestaurant, this.getHotel(uni_id));
        } else if(uni_id.contains("e")) {
            cursor = DBOperator.getInstance().execQuery(SQLCommand.resultEntertainment, this.getHotel(uni_id));
        }

        desc_table = (TableLayout) this.findViewById(R.id.desc_table);
        tl = (TableLayout) this.findViewById(R.id.temptable);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        String col_names;
        //col_names=cursor.getString(0);
        int no_of_cols = cursor.getColumnCount();//cursor.getcolname(0) is uni_name

        for (int i = 0; i < no_of_cols; i++) {
            // create a new textview
            TableRow tr = new TableRow(this);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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
            rightRowTextView.setPadding(20, 0, 70, 0);

            leftLL.addView(leftRowTextView);
            rightLL.addView(rightRowTextView);

            leftRowTextView.setTextColor(Color.parseColor("#FFFFFF"));
            rightRowTextView.setTextColor(Color.parseColor("#FFFFFF"));

            tr.addView(leftLL);
            tr.addView(rightLL);
            tl.addView(tr, i);
        }
        cursor.close();

        //String descsql=SQLCommand.getDescription+uni_id+"';";
        Cursor cursor2 = DBOperator.getInstance().execQuery(SQLCommand.getDescription,this.getHotel(uni_id));
        if(cursor2!=null && cursor2.moveToFirst())
            do {
                //int no_of_cols_desc = cursor2.getColumnCount();
                //for (int i = 0; i < no_of_cols_desc; i++) {
                    //int a=1;
                    TableRow tr = new TableRow(this);
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    tr.setLayoutParams(lp);
                    TextView RowTextView = new TextView(this);//previously final
                    //TextView rightRowTextView = new TextView(this);
                    LinearLayout LL = new LinearLayout(this);
                    //LinearLayout rightLL = new LinearLayout(this);
                    //leftRowTextView.setText(a+". ");
                    if(!cursor2.getString(0).isEmpty())
                    RowTextView.setText("> "+cursor2.getString(0));
                    //leftRowTextView.setWidth(Math.round(displayWidth * .1f));
                    RowTextView.setMaxWidth(Math.round(displayWidth * 1f));

                    RowTextView.setSingleLine(false);
                    //rightRowTextView.setSingleLine(false);
                    RowTextView.setPadding(0, 0, 70, 0);

                    LL.addView(RowTextView);
                    //rightLL.addView(rightRowTextView);
                    tr.addView(LL);
                    //tr.addView(rightLL);
                    desc_table.addView(tr);
                    tr.setPadding(0, 0, 0, 30);
                    //a=a+1;
                //}


            }
            while(cursor2.moveToNext());
        cursor2.close();

    }



    public void onClick(View v) {
        int id = v.getId();
        //cursor.getString(1);

        if (id == R.id.travelinfo_button) {
            Intent intent = new Intent(getApplicationContext(), TravelInfoActivity.class);
            intent.putExtra("uni_id", uni_id);
            intent.putExtra("Destination", dest);
            intent.putExtra("sql", sql);
            startActivity(intent);
        }
        else if (id == R.id.rating_btn) {
            // show summary chart
            //String sql=SQLCommand.rating+uni_id+"';";

            Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.rating, this.getHotel(uni_id));
            List<Pair> pairList = new LinkedList<Pair>();
            for (int i = 1; i <= 5; i++) {
                Pair pair = new Pair(i, 0);
                pairList.add(pair);
            }
            while (cursor.moveToNext()) {
                int location = Integer.parseInt(cursor.getString(0));
                pairList.get(location - 1).setNumber(
                        Double.parseDouble(cursor.getString(1)));
            }
            Intent intent = ChartGenerator.getBarChart(getBaseContext(),
                    "Ratings",pairList);
            this.startActivity(intent);
        }
        else if(id==R.id.returnFromDesc)
        {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("Destination", this.getIntent().getStringExtra("Destination"));
            intent.putExtra("sql", this.getIntent().getStringExtra("sql"));
            startActivity(intent);
        }
    }


    private String[] getHotel(String uni_id) {
        String[] args = new String[1];
        args[0] = uni_id;
        return args;
    }
}
