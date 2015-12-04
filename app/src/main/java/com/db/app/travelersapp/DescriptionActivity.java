package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.db.app.travelersapp.constant.SQLCommand;
import com.db.app.travelersapp.util.DBOperator;
import com.db.app.travelersapp.util.Pair;
import com.db.app.travelersapp.view.ChartGenerator;
import java.util.LinkedList;
import java.util.List;



public class DescriptionActivity extends Activity implements View.OnClickListener {
    //768 is display width
    private String uni_id;
    ScrollView scrollView;
    TextView textView1, textView2;
    Button travelInfoButton, ratingButton;
    LinearLayout leftLinearLayout, rightLinearLayout;
    static TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        travelInfoButton = (Button) this.findViewById(R.id.travelinfo_button);
        travelInfoButton.setOnClickListener(this);
        ratingButton = (Button) this.findViewById(R.id.rating_btn);
        ratingButton.setOnClickListener(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int displayWidth = size.x;


        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");
        uni_id = intent.getStringExtra("uni_id");
        Cursor cursor = DBOperator.getInstance().execQuery(sql);
        //scrollView=(ScrollView)this.findViewById(R.id.temp_scrollview);
        //scrollView.addView(new TableView(this.getBaseContext(),cursor));
        //textView1=(TextView)this.findViewById(R.id.temp_textview);
        //textView2=(TextView)this.findViewById(R.id.temp_textview2);
        //String one=cursor.getString(cursor.getColumnIndex(""));
        //leftLinearLayout=(LinearLayout)this.findViewById(R.id.left_result_layout);
        //rightLinearLayout=(LinearLayout)this.findViewById(R.id.right_result_layout);
        tl = (TableLayout) this.findViewById(R.id.temptable);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        String col_names;
        //col_names=cursor.getString(0);
        int no_of_cols = cursor.getColumnCount();//cursor.getcolname(0) is uni_name

        //String col_names=cursor.getString(cursor.getColumnIndex("uni_type"));

        //String col_names = cursor.getString(cursor.getColumnIndex("name"));

        //textView1.setText(cursor.getString(cursor.getColumnIndex(col_names+" "+Integer.toString(no_of_cols))));
        //textView1.setText(col_names+" "+Integer.toString(no_of_cols));
        //textView2.setText(cursor.getString(cursor.getColumnIndex(col_names[1])));

        //final TextView[] myLeftTextViews = new TextView[no_of_cols];
        //final TextView[] myRightTextViews = new TextView[no_of_cols];
        for (int i = 0; i < no_of_cols; i++) {
            // create a new textview
            //TextView leftRowTextView = new TextView(this);//previously final
            //TextView rightRowTextView = new TextView(this);
            TableRow tr = new TableRow(this);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            tr.setLayoutParams(lp);
            TextView leftRowTextView = new TextView(getApplicationContext());//previously final
            TextView rightRowTextView = new TextView(this);
            LinearLayout leftLL = new LinearLayout(this);
            LinearLayout rightLL = new LinearLayout(this);
            //leftRowTextView.setLayoutParams(lp);
            // set some properties of rowTextView or something
            leftRowTextView.setText(cursor.getColumnName(i));//(cursor.getColumnName(i)+": "+cursor.getString(i));//(cursor.getString(cursor.getColumnIndex(col_names[i])));
            //rightRowTextView.setLayoutParams(lp);
            rightRowTextView.setText(cursor.getString(i));
            leftRowTextView.setWidth(Math.round(displayWidth * .2f));
            rightRowTextView.setMaxWidth(Math.round(displayWidth * 0.8f));

            leftRowTextView.setSingleLine(false);
            rightRowTextView.setSingleLine(false);
            rightRowTextView.setPadding(0, 0, 50, 0);

            leftLL.addView(leftRowTextView);
            rightLL.addView(rightRowTextView);

            //leftRowTextView.setWidth(Math.round(displayWidth*.2f));
            //rightRowTextView.setMaxWidth(Math.round(displayWidth*0.8f));

            //rightRowTextView.setMaxWidth(Math.round(displayWidth*0.8f));
            tr.addView(leftLL);
            tr.addView(rightLL);
            // add the textview to the linearlayout
            //leftLinearLayout.addView(leftRowTextView);
            //rightLinearLayout.addView(rightRowTextView);
            // save a reference to the textview for later

            tl.addView(tr, i);
            //myLeftTextViews[i] = leftRowTextView;
            //myRightTextViews[i] = rightRowTextView;
        }
        cursor.close();

    }

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_description, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }*/
    public void onClick(View v) {
        int id = v.getId();
        //cursor.getString(1);

        if (id == R.id.travelinfo_button) {
            Toast.makeText(getBaseContext(), uni_id,
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), demoActivity.class);
            //String sql=SQLCommand.transinfo+uni_id+"');";
            String sql = SQLCommand.demo + "'" + uni_id + "';";
            intent.putExtra("sql", sql);
            intent.putExtra("uni_id", uni_id);
            startActivity(intent);
        }
        else if (id == R.id.rating_btn) {
            // show summary chart
            String sql=SQLCommand.rating+uni_id+"' group by rating order by rating;";
            Toast.makeText(getBaseContext(), sql,
                    Toast.LENGTH_SHORT).show();
            Cursor cursor = DBOperator.getInstance().execQuery(sql);
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
    }
}