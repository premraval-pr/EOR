package com.example.eor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookActivity extends AppCompatActivity {

    TextView calFrom, calTo, userName, itemTitle, userLocation, postDesc, postAmount, postTotalAmount, taxAmount, dateFrom, dateTo;
    ImageView image_calFrom, image_calTo, image_post;
    Calendar mycalendarFrom = Calendar.getInstance();
    Calendar mycalendarTo = Calendar.getInstance();

    final Calendar mycalendar = Calendar.getInstance();

    final Calendar mintodate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        calFrom = findViewById(R.id.__textview_calenderfrom);
        calTo = findViewById(R.id.__textview_calenderto);
        image_calFrom = findViewById(R.id.__imageview_calenderfrom);
        image_calTo = findViewById(R.id.__imageview_calenderto);
        userName = findViewById(R.id.__textview_username);
        itemTitle = findViewById(R.id.__textview_itemtitle);
        userLocation = findViewById(R.id.__textview_location);
        postDesc = findViewById(R.id.__textview_postDescription);
        postAmount = findViewById(R.id.__textview_amounts);
        postTotalAmount = findViewById(R.id.__textview_totalamount);
        taxAmount = findViewById(R.id.__textview_taxxamount);
        image_post=findViewById(R.id.__imageview_itemimage);
        dateFrom = findViewById(R.id.__textview_calenderfrom);
        dateTo = findViewById(R.id.__textview_calenderto);

        Intent intentFromPostDesc = getIntent();
        itemTitle.setText(intentFromPostDesc.getStringExtra("ItemTitle"));
        userName.setText(intentFromPostDesc.getStringExtra("UserName"));
        userLocation.setText(intentFromPostDesc.getStringExtra("Location"));
        postDesc.setText(intentFromPostDesc.getStringExtra("ItemTitle"));
        double price = intentFromPostDesc.getDoubleExtra("PostAmount",0);
        double tax = price*0.13;
        double total = price+tax;
        postAmount.setText("$"+String.valueOf(price));
        taxAmount.setText("$"+String.valueOf(tax));
        postTotalAmount.setText("$"+String.valueOf(total));
        Picasso.with(this).load(intentFromPostDesc.getStringExtra("ImageString")).resize(200,200).centerCrop().into(image_post);
        //from
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateFromCal = null;
        try {
            dateFromCal = sdf.parse(intentFromPostDesc.getStringExtra("DateFrom").substring(0,10));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newsdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(newsdf.format(dateFromCal));
        //dateFrom.setText(newsdf.format(dateFromCal));
        //to
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateToCal = null;
        try {
            dateToCal = sdf1.parse(intentFromPostDesc.getStringExtra("DateTo").substring(0,10));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newsdf1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(newsdf1.format(dateToCal));
        //dateTo.setText(newsdf1.format(dateToCal));


        //Calendar To

        mycalendarTo.set(Calendar.YEAR,dateToCal.getYear());
        mycalendarTo.set(Calendar.MONTH,dateToCal.getMonth());
        mycalendarTo.set(Calendar.DAY_OF_MONTH,dateToCal.getDay());

        final DatePickerDialog datetoDailog = new DatePickerDialog(BookActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int tempmonth = month+1;
                String dateFormat = tempmonth+"/"+dayOfMonth+"/"+year;
                calTo.setText(dateFormat);
            }
        },mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DAY_OF_MONTH));



//*********************************************************************************************************************************//
        //Calendar From

        mycalendarFrom.set(Calendar.YEAR,dateFromCal.getYear());
        mycalendarFrom.set(Calendar.MONTH,dateFromCal.getMonth());
        mycalendarFrom.set(Calendar.DAY_OF_MONTH,dateFromCal.getDay());

        final DatePickerDialog dateFrom = new DatePickerDialog(BookActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int tempmonth = month+1;
                String format = tempmonth+"/"+dayOfMonth+"/"+year;
                calFrom.setText(format);
//                calTo.setEnabled(true);
//                image_calTo.setEnabled(true);
                mintodate.set(Calendar.YEAR,year);
                mintodate.set(Calendar.MONTH,month);
                mintodate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                datetoDailog.getDatePicker().setMinDate(mintodate.getTimeInMillis());
            }
        },mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DAY_OF_MONTH));

        dateFrom.getDatePicker().setMinDate(mycalendarFrom.getTimeInMillis());
        dateFrom.getDatePicker().setMaxDate(mycalendarTo.getTimeInMillis());

        //On Click Listner FROM
        calFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFrom.show();
            }
        });

        //Image Calendar From
        image_calFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFrom.show();
            }
        });

        datetoDailog.getDatePicker().setMaxDate(mycalendarTo.getTimeInMillis());

        //On Click Listner TO
        //calTo.setEnabled(false);
        calTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datetoDailog.show();
            }
        });

        //Image Calendar To
//        image_calTo.setEnabled(false);
        image_calTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datetoDailog.show();
            }
        });

        //Button and checkbox

        final Button paymentButton=findViewById( R.id.__button_choosepaymentmethod);
        final CheckBox termsandContionCheckBox= ( CheckBox ) findViewById( R.id.__checkbox_termsandcondition);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(termsandContionCheckBox.isChecked())
                {
                    paymentButton.setEnabled(true);
                }
                else
                {
                    paymentButton.setEnabled(false);
                    Toast.makeText(BookActivity.this, "Please agree to the terms and condition above!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
