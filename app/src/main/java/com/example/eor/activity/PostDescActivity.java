package com.example.eor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.eor.R;
import com.example.eor.adapter.ImageViewPageAdapter;
import com.example.eor.adapter.PostAdapter_ExplorePostsActivity;
import com.example.eor.dao.PostDescription_DAO;
import com.example.eor.dao.Saved_Posts_DAO;
import com.example.eor.listener.SavedPostListener;
import com.example.eor.model.PostDescription_Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostDescActivity extends AppCompatActivity implements SavedPostListener {

    ViewPager viewPager;
    TextView textViewPostDesc, textViewPostTitle, textViewPostPrice, textViewPostAvailableFrom, textViewPostAvailableTo, textViewPostCreatedOn, textViewUserName, textViewLocation;
    PostDescription_Model postDescription_model;
    FloatingActionButton fab_options,fab_book,fab_bid,fab_reply,fab_save;
    Animation fab_open,fab_close,fab_rotate_clock,fab_rotate_anti;
    TextView textView_book,textView_bid,textView_reply,textView_save;
    Saved_Posts_DAO saved_posts_dao;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdesc);
        Intent intent_from_saved_post = getIntent();




        textViewPostDesc = findViewById(R.id.__textview_itemdescription);
        textViewPostDesc.setMovementMethod(new ScrollingMovementMethod());
        textViewPostTitle = findViewById(R.id.__textview_itemtitle);
        textViewPostPrice = findViewById(R.id.__textview_pricedisplay);
        textViewPostAvailableFrom = findViewById(R.id.__textview_postavailablefromview);
        textViewPostAvailableTo = findViewById(R.id.__textview_postavailabletoview);
        textViewPostCreatedOn = findViewById(R.id.__textview_postcreatedview);
        textViewUserName = findViewById(R.id.__textview_username);
        textViewLocation = findViewById(R.id.__textview_location);
        fab_options = findViewById(R.id.__fab_options);
        fab_book = findViewById(R.id.__fab_book);
        fab_bid = findViewById(R.id.__fab_bid);
        fab_reply = findViewById(R.id.__fab_reply);
        fab_save = findViewById(R.id.__fab_save);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fab_rotate_clock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rotate_clocl);
        fab_rotate_anti = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rotate_anti);
        textView_book = findViewById(R.id.__textview_book);
        textView_bid = findViewById(R.id.__textview_bidd);
        textView_reply = findViewById(R.id.__textview_reply);
        textView_save = findViewById(R.id.__textview_save);
        saved_posts_dao = new Saved_Posts_DAO(this);



        fab_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    fab_book.startAnimation(fab_close);
                    textView_book.startAnimation(fab_close);
                    fab_bid.startAnimation(fab_close);
                    textView_bid.startAnimation(fab_close);
                    fab_reply.startAnimation(fab_close);
                    textView_reply.startAnimation(fab_close);
                    fab_save.startAnimation(fab_close);
                    textView_save.startAnimation(fab_close);
                    fab_options.startAnimation(fab_rotate_anti);
                    isOpen = false;
                }
                else{
                    fab_book.startAnimation(fab_open);
                    textView_book.startAnimation(fab_open);
                    fab_bid.startAnimation(fab_open);
                    textView_bid.startAnimation(fab_open);
                    fab_reply.startAnimation(fab_open);
                    textView_reply.startAnimation(fab_open);
                    fab_save.startAnimation(fab_open);
                    textView_save.startAnimation(fab_open);
                    fab_options.startAnimation(fab_rotate_clock);
                    isOpen = true;
                }
            }
        });


        fab_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentBooking = new Intent(getApplicationContext(), BookActivity.class);
                intentBooking.putExtra("ItemTitle",textViewPostTitle.getText().toString());
                intentBooking.putExtra("UserName",textViewUserName.getText().toString());
                intentBooking.putExtra("Location",textViewLocation.getText().toString());
                intentBooking.putExtra("PostDescription",textViewPostDesc.getText().toString());
                intentBooking.putExtra("PostAmount",Double.parseDouble(postDescription_model.getPostPrice()));
                intentBooking.putExtra("ImageString", PostDescription_DAO.image_post.get(0));

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateFrom = postDescription_model.getPostFrom();
                String dateFromString = df.format(dateFrom);
                System.out.println(dateFromString);
                intentBooking.putExtra("DateFrom",dateFromString);

                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                Date dateTo = postDescription_model.getPostTo();
                String dateToString = df1.format(dateTo);
                System.out.println(dateToString);
                intentBooking.putExtra("DateTo",dateToString);

                startActivity(intentBooking);
            }
        });

        fab_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentbid=new Intent(getApplicationContext(), PostsBid.class);
                intentbid.putExtra("ItemTitle",textViewPostTitle.getText().toString());
                intentbid.putExtra("UserName",textViewUserName.getText().toString());
                intentbid.putExtra("Location",textViewLocation.getText().toString());
                intentbid.putExtra("PostAmount",Double.parseDouble(postDescription_model.getPostPrice()));
                intentbid.putExtra("ImageString", PostDescription_DAO.image_post.get(0));
                startActivity(intentbid);
            }
        });

        fab_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChatActivity.class)
                .putExtra("Itemname",postDescription_model.getPostId()+"_"+postDescription_model.getUser_id()+"_"+SlidingDrawerActivity.USER_ID)
                .putExtra("new","new"));
            }
        });


        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saved_posts_dao.CreateSavedPost(postDescription_model.getPostId());




            }
        });
        String post_id = intent_from_saved_post.getStringExtra("postid");
        if(post_id!=null)
        {
            System.out.println("Intent"+post_id);
            setPostDescription(post_id);
        }
        else {
            setPostDescription();
        }


    }

    public void setPostDescription() {
        PostDescription_DAO postDescription_dao = new PostDescription_DAO();
        postDescription_model = postDescription_dao.getPost(PostAdapter_ExplorePostsActivity.post_id);
        textViewPostTitle.setText(postDescription_model.getPostTitle());
        textViewPostPrice.setText("$ " + postDescription_model.getPostPrice());
        textViewPostDesc.setText(postDescription_model.getPostDescription());
        textViewPostAvailableFrom.setText(postDescription_model.getPostFrom().toString().substring(4,10)+postDescription_model.getPostFrom().toString().substring(23,28));
        textViewPostAvailableTo.setText(postDescription_model.getPostTo().toString().substring(4,10)+postDescription_model.getPostTo().toString().substring(23,28));
        textViewPostCreatedOn.setText(postDescription_model.getPostCreated().toString().substring(4,10)+postDescription_model.getPostCreated().toString().substring(23,28));
        viewPager = findViewById(R.id.__scrollview_postimage);
        ImageViewPageAdapter imageViewPageAdapter = new ImageViewPageAdapter(this,postDescription_model.getImagePath());
        viewPager.setAdapter(imageViewPageAdapter);
        textViewUserName.setText(postDescription_model.getUser_fname());
        textViewLocation.setText(postDescription_model.getUser_city());
    }

    public void setPostDescription(String id) {
        PostDescription_DAO postDescription_dao = new PostDescription_DAO();
        postDescription_model = postDescription_dao.getPost(id);
        textViewPostTitle.setText(postDescription_model.getPostTitle());
        textViewPostPrice.setText("$ " + postDescription_model.getPostPrice());
        textViewPostDesc.setText(postDescription_model.getPostDescription());
        textViewPostAvailableFrom.setText(postDescription_model.getPostFrom().toString().substring(4,10)+postDescription_model.getPostFrom().toString().substring(23,28));
        textViewPostAvailableTo.setText(postDescription_model.getPostTo().toString().substring(4,10)+postDescription_model.getPostTo().toString().substring(23,28));
        textViewPostCreatedOn.setText(postDescription_model.getPostCreated().toString().substring(4,10)+postDescription_model.getPostCreated().toString().substring(23,28));
        viewPager = findViewById(R.id.__scrollview_postimage);
        ImageViewPageAdapter imageViewPageAdapter = new ImageViewPageAdapter(this,postDescription_model.getImagePath());
        viewPager.setAdapter(imageViewPageAdapter);
        textViewUserName.setText(postDescription_model.getUser_fname());
        textViewLocation.setText(postDescription_model.getUser_city());
    }

    @Override
    public void apiResult(String result) {

        if(result.equals("1"))
        {
            Snackbar.make(fab_options,postDescription_model.getPostTitle()+" saved to Saved Posts",Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            Snackbar.make(fab_options,postDescription_model.getPostTitle()+" already in Saved Posts",Snackbar.LENGTH_SHORT).show();
        }

    }
}
