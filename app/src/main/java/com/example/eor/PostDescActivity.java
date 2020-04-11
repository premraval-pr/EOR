package com.example.eor;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostDescActivity extends AppCompatActivity {

    Button bookbid, reply;
    ViewPager viewPager;
    TextView textViewPostDesc, textViewPostTitle, textViewPostPrice, textViewPostAvailableFrom, textViewPostAvailableTo, textViewPostCreatedOn, textViewUserName, textViewLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdesc);


        bookbid = findViewById(R.id.__button_bookbid);
        textViewPostDesc = findViewById(R.id.__textview_itemdescription);
        textViewPostDesc.setMovementMethod(new ScrollingMovementMethod());
        textViewPostTitle = findViewById(R.id.__textview_itemtitle);
        textViewPostPrice = findViewById(R.id.__textview_pricedisplay);
        textViewPostAvailableFrom = findViewById(R.id.__textview_postavailablefromview);
        textViewPostAvailableTo = findViewById(R.id.__textview_postavailabletoview);
        textViewPostCreatedOn = findViewById(R.id.__textview_postcreatedview);
        textViewUserName = findViewById(R.id.__textview_username);
        textViewLocation = findViewById(R.id.__textview_location);

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

        setPostDescription();
    }

    public void setPostDescription() {

        PostDescription_DAO postDescription_dao = new PostDescription_DAO();
        PostDescription_Model postDescription_model = postDescription_dao.getPost(PostAdapter_ExplorePostsActivity.post_id);
        textViewPostTitle.setText(postDescription_model.getPostTitle());
        textViewPostPrice.setText("$ " + postDescription_model.getPostPrice());
        textViewPostDesc.setText(postDescription_model.getPostDescription());
        textViewPostAvailableFrom.setText(postDescription_model.getPostFrom().toString().substring(4,10)+postDescription_model.getPostFrom().toString().substring(23,28));
        textViewPostAvailableTo.setText(postDescription_model.getPostTo().toString().substring(4,10)+postDescription_model.getPostTo().toString().substring(23,28));
        textViewPostCreatedOn.setText(postDescription_model.getPostCreated().toString().substring(4,10)+postDescription_model.getPostCreated().toString().substring(23,28));
//        viewPager.setAdapter(postDescription_model.getImagePath());
        viewPager = findViewById(R.id.__imageview_postimage);
        ImageViewPageAdapter imageViewPageAdapter = new ImageViewPageAdapter(this);
        viewPager.setAdapter(imageViewPageAdapter);
        textViewUserName.setText(postDescription_model.getUser_fname());
        textViewLocation.setText(postDescription_model.getUser_city());
    }


}
