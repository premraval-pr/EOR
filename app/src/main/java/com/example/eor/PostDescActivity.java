package com.example.eor;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class PostDescActivity extends AppCompatActivity {

    Button bookbid, reply;
    ViewPager viewPager;
    TextView textViewPostDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdesc);

        viewPager = findViewById(R.id.__imageview_postimage);
        ImageViewPageAdapter imageViewPageAdapter = new ImageViewPageAdapter(this);
        viewPager.setAdapter(imageViewPageAdapter);

        bookbid = findViewById(R.id.__button_bookbid);

        textViewPostDesc = findViewById(R.id.__textview_itemdescription);

        textViewPostDesc.setMovementMethod(new ScrollingMovementMethod());

        bookbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BookActivity.class));
            }
        });

        reply = findViewById(R.id.__button_reply);
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReplyPop.class));
            }
        });
    }
}
