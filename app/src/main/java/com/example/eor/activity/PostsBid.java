package com.example.eor.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eor.R;


public class PostsBid extends AppCompatActivity {



    TextView  __textviewUserBidedPrice;
    Chronometer __chronometerStopWatchBid;
    EditText alertbox;
    double bid_price;
    double bidvalueRounded;
    AlertDialog.Builder alertDialog;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_bid);

        __textviewUserBidedPrice=findViewById(R.id.__textview_bid_price);
        __chronometerStopWatchBid=findViewById(R.id.__timepicker_bid_bidtime);
        __chronometerStopWatchBid.setCountDown(true);
          long  timeInMilSeconds = 500000;
        __chronometerStopWatchBid.setBase(SystemClock.elapsedRealtime() + timeInMilSeconds);
        __chronometerStopWatchBid.start();





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


            }
        });
        alertDialog.show();



    }
}
