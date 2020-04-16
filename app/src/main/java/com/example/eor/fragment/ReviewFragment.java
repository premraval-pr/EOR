package com.example.eor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eor.R;

import java.util.Date;

public class ReviewFragment extends AppCompatActivity {

    String title, location, username;
    String toDate, fromDate;

    TextView __textview_title,__textview_username,__textview_toDate,__textview_fromDate,__textview_location;
    RatingBar __ratingBar_review;
    EditText __edittext_writeAreview;
    Button __button_submitReview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Intent intentFromHistory = getIntent();
        title = intentFromHistory.getStringExtra("Title");
        location = intentFromHistory.getStringExtra("Location");
        username = intentFromHistory.getStringExtra("Username");
        toDate = intentFromHistory.getStringExtra("toDate");
        fromDate = intentFromHistory.getStringExtra("fromDate");


        setContentView(R.layout.fragment_review);




        __textview_title = findViewById(R.id.__textview_itemtitle_reviewPage);
        __textview_username = findViewById(R.id.__textview_username_reviewPage);
        __textview_fromDate = findViewById(R.id.__textview_calenderfrom_reviewPage);
        __textview_toDate = findViewById(R.id.__textview_calenderto_reviewPage);
        __textview_location = findViewById(R.id.__textview_location_reviewPage);
        __ratingBar_review = findViewById(R.id.__ratingbar_review);
        __edittext_writeAreview = findViewById(R.id.__edittext_writeareview);
        __button_submitReview = findViewById(R.id.__button_submitreview);



        __button_submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Your Review has been recorded. Thank you.",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });


        __textview_title.setText(title);
        __textview_username.setText(location);
        __textview_username.setText(username);
        __textview_toDate.setText(toDate);
        __textview_fromDate.setText(fromDate);

    }
}
