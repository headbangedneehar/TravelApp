package com.db.app.travelersapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.db.app.travelersapp.constant.SQLCommand;
/*
use SQLCommand.addReview
insert into cust_review values('00030',4,"THE REVIEW",uni_id)
*/



public class AddReviewActivity extends Activity implements View.OnClickListener{

    EditText ratinginput,reviewinput;
    Button postReviewButton;
    private String rat_id;
    private String sql;
    private int rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        Intent intent=this.getIntent();
        String rat_id=intent.getStringExtra("rat_id");
        String sql= SQLCommand.addReview+rat_id+"' ";

        postReviewButton = (Button) this.findViewById(R.id.post_review_button);
        postReviewButton.setOnClickListener(this);
        ratinginput = (EditText) this.findViewById(R.id.rating);
        reviewinput = (EditText) this.findViewById(R.id.review);
    }


    public void onClick(View v)
    {
        int id=v.getId();

        if (id==R.id.post_review_button)
        {
            if(ratinginput.getText().toString().isEmpty() || reviewinput.getText().toString().isEmpty())
            {
                Toast.makeText(getBaseContext(), "Please enter both, Rating and Review",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {

            }
        }
    }
}
