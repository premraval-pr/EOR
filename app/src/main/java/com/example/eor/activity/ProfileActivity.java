package com.example.eor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eor.R;
import com.example.eor.adapter.ProfileAdapter;
import com.example.eor.dao.Profile_DAO;
import com.example.eor.listener.IProfileListener;
import com.example.eor.model.ExplorePost_Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileActivity extends AppCompatActivity implements IProfileListener {

    RecyclerView recyclerView;
    ProfileAdapter profileAdapter;
    Profile_DAO profile_dao;
    String user_id, fullName, username, location;
    List<ExplorePost_Model> items;
    Intent intent_from_PostDec;
    TextView full_name, u_name, location_user, numberOfPost;
    int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile_dao = new Profile_DAO(this);
        full_name = findViewById(R.id.__textviewPost_firstname);
        u_name = findViewById(R.id.__textviewPost_username);
        location_user = findViewById(R.id.__textviewPost_location);
        numberOfPost = findViewById(R.id.__textviewPost_numberOfPostsinNumber);

        recyclerView = findViewById(R.id.__recyclerview_userprofile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        //intent from post description
        intent_from_PostDec = getIntent();
        user_id = intent_from_PostDec.getStringExtra("user_id");
        fullName = intent_from_PostDec.getStringExtra("full_name");
        username = intent_from_PostDec.getStringExtra("full_name");
        location = intent_from_PostDec.getStringExtra("location");

        full_name.setText(fullName);
        u_name.setText(username);
        location_user.setText(location);



        profile_dao.collectData(user_id);
        numberOfPost.setText(size+"");
        profileAdapter = new ProfileAdapter(this,items);
        recyclerView.setAdapter(profileAdapter);

    }

    @Override
    public void getUserProfilePosts(List<ExplorePost_Model> explorePost_models) {
        this.size = explorePost_models.size();
        this.items = explorePost_models;
    }
}