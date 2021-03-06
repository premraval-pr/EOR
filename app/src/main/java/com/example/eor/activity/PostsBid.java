package com.example.eor.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eor.R;
import com.example.eor.dao.User_DAO;
import com.squareup.picasso.Picasso;


public class PostsBid extends AppCompatActivity {



    TextView  __textviewUserBidedPrice,__textViewUserbidder,__textview_post_title,__textview_post_location,__textview_post_creatorusername,__textview_post_creatorprice;
    Chronometer __chronometerStopWatchBid;
    ImageView image_post;
    EditText alertbox;
    double bid_price;
    double bidvalueRounded;
    AlertDialog.Builder alertDialog;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_bid);

        __textview_post_title=findViewById(R.id.__textview_bid_itemtitle);
        __textview_post_location=findViewById(R.id.__textview_bid_location);
        __textview_post_creatorusername=findViewById(R.id.__textview_bid_username);
        __textview_post_creatorprice=findViewById(R.id.__textview_bid_actualprice);
        __textviewUserBidedPrice=findViewById(R.id.__textview_bid_price);
        image_post=findViewById(R.id.__imageview_bid_defaultimage);
        __textViewUserbidder=findViewById(R.id.__textviewusername_bid);
        __chronometerStopWatchBid=findViewById(R.id.__timepicker_bid_bidtime);
        __chronometerStopWatchBid.setCountDown(true);
          long  timeInMilSeconds = 500000;
        __chronometerStopWatchBid.setBase(SystemClock.elapsedRealtime() + timeInMilSeconds);
        __chronometerStopWatchBid.start();
        Intent postdetails=getIntent();

        __textview_post_title.setText(postdetails.getStringExtra("ItemTitle"));
        __textview_post_location.setText(postdetails.getStringExtra("Location"));
        __textview_post_creatorusername.setText(postdetails.getStringExtra("UserName"));
        __textview_post_creatorprice.setText(String.valueOf(postdetails.getDoubleExtra("PostAmount",0)));
        Picasso.with(this).load(postdetails.getStringExtra("ImageString")).resize(200,200).centerCrop().into(image_post);







    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openDialogBox(View view)
    {
        alertbox=new EditText(this);
        alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle("Please Enter the Amount");
        alertDialog.setView(alertbox);
        alertbox.setKeyListener(DigitsKeyListener.getInstance(null,false,true));
        alertbox.setHint("Enter Price");
        alertbox.setPadding(20,20,20,20);
        alertbox.setMaxLines(1);
        alertbox.setWidth(10);
        alertbox.setHeight(120);
        alertDialog.setNegativeButton("Cancel",null);
        alertDialog.create();
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bid_price=Double.parseDouble(alertbox.getText().toString());
                bidvalueRounded= Math.round(bid_price * 100D) / 100D;
                __textviewUserBidedPrice.setText(String.valueOf(bidvalueRounded));
                User_DAO user_dao=new User_DAO();
                __textViewUserbidder.setText(user_dao.getUser(SlidingDrawerActivity.USER_ID).getUser_displayname());
            }
        });
        alertDialog.show();



    }
}
