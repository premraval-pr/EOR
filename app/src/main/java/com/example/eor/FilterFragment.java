package com.example.eor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.eor.PostAdapter_ExplorePostsActivity.*;

public class FilterFragment extends Fragment {

    HomeFragment homeFragment = new HomeFragment();


    DatePickerDialog picker;
    public View __view_filter;
    public TextView __textview_filter;
    SeekBar seekBarPrice_filter;
    SeekBar seekBarRadius_filter;


    EditText postalcode;
    EditText datePickerDialogTo;
    EditText datePickerDialogFrom;
    int productPrice;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        __view_filter = inflater.inflate(R.layout.layout_filterposts,container,false);
        __textview_filter = __view_filter.findViewById(R.id.__textview_FilterBtn);

        seekBarPrice_filter = __view_filter.findViewById(R.id.__seekbar_pricerange);



        final EditText __edittextFromDate = __view_filter.findViewById(R.id.__edittext_dateFrom_filter);
        __edittextFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        __edittextFromDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                picker.show();
            }
        });



        final EditText __edittextToDate = __view_filter.findViewById(R.id.__edittext_dateTo_filter);
        __edittextToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        __edittextToDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                picker.show();
            }
        });






        seekBarRadius_filter = __view_filter.findViewById(R.id.__seekbar_radius);

        seekBarRadius_filter.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView tv = __view_filter.findViewById(R.id.__textview_RadiusValue);
                tv.setText((progress+" Miles"));


                int width = seekBar.getWidth() - seekBar.getPaddingLeft() - seekBar.getPaddingRight();
                int thumbPos = seekBar.getPaddingLeft() + width * seekBar.getProgress() / seekBar.getMax();

                tv.measure(0, 0);
                int txtW = tv.getMeasuredWidth();
                int delta = txtW / 2;
                tv.setX(seekBar.getX() + thumbPos - delta);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





        seekBarPrice_filter.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView tv = __view_filter.findViewById(R.id.__textview_PriceRangeValue);
                tv.setText(String.valueOf("$"+progress));


                int width = seekBar.getWidth() - seekBar.getPaddingLeft() - seekBar.getPaddingRight();
                int thumbPos = seekBar.getPaddingLeft() + width * seekBar.getProgress() / seekBar.getMax();

                tv.measure(0, 0);
                float txtW = tv.getMeasuredWidth();
                float delta = txtW / 2;
                tv.setX(seekBar.getX() + thumbPos - delta);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        __textview_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postalcode = __view_filter.findViewById(R.id.__edittext_location_filter);
                String s_postalcode = postalcode.getText().toString();

                SeekBar radiusValue = __view_filter.findViewById(R.id.__seekbar_radius);
                int seekbar_radiusValue  = radiusValue.getProgress();


                datePickerDialogFrom = __view_filter.findViewById(R.id.__edittext_dateFrom_filter);
                String s_datePickerDialogFrom = datePickerDialogFrom.getText().toString();


                datePickerDialogTo = __view_filter.findViewById(R.id.__edittext_dateTo_filter);
                String s_datePickerDialogTo = datePickerDialogTo.getText().toString();



                productPrice = seekBarPrice_filter.getProgress();
                ApplyFilterToPosts(productPrice);

            }

            private void ApplyFilterToPosts(int productPrice) {
                startActivity(new Intent(getContext(),SlidingDrawerActivity.class));
            }
        });





        return  __view_filter;



    }


}
