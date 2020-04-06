package com.example.eor;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookActivity extends AppCompatActivity {

    TextView calFrom, calTo;
    ImageView image_calFrom, image_calTo;
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

        //Calendar To

        mycalendarTo.set(Calendar.YEAR,2020);
        mycalendarTo.set(Calendar.MONTH,Calendar.APRIL);
        mycalendarTo.set(Calendar.DAY_OF_MONTH,9);

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

        mycalendarFrom.set(Calendar.YEAR,2020);
        mycalendarFrom.set(Calendar.MONTH,Calendar.APRIL);
        mycalendarFrom.set(Calendar.DAY_OF_MONTH,4);

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
                datetoDailog.getDatePicker().setMinDate(mintodate.getTime().getTime());
            }
        },mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DAY_OF_MONTH));

        dateFrom.getDatePicker().setMinDate(mycalendarFrom.getTime().getTime());
        dateFrom.getDatePicker().setMaxDate(mycalendarTo.getTime().getTime());

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

        datetoDailog.getDatePicker().setMaxDate(mycalendarTo.getTime().getTime());

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
