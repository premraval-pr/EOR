package com.example.eor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eor.R;
import com.example.eor.activity.PostDescActivity;
import com.example.eor.activity.UploadPostActivity;
import com.example.eor.adapter.PostAdapter_ExplorePostsActivity;
import com.example.eor.dao.ExplorePost_DAO;
import com.example.eor.fragment.FilterFragment;
import com.example.eor.listener.ItemListener_ExplorePostsActivity;
import com.example.eor.model.ExplorePost_Model;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemListener_ExplorePostsActivity {

    RecyclerView __recylerView_mainRecyler;
    Button __button_Post;
    List<ExplorePost_Model> list;
    List<ExplorePost_Model> filteredlist = new ArrayList<ExplorePost_Model>();
    ExplorePost_DAO explorePost_dao;
    public static PostAdapter_ExplorePostsActivity postAdapter_explorePostsActivity;



    Button __button_filter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inboxView = inflater.inflate(R.layout.fragment_home,container,false);
        list = ExplorePost_DAO.list;
        explorePost_dao=new ExplorePost_DAO();
        explorePost_dao.collectData();
        list=ExplorePost_DAO.list;

        __recylerView_mainRecyler=inboxView.findViewById(R.id.__recylerview_mainContent);
        __recylerView_mainRecyler.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter_explorePostsActivity = new PostAdapter_ExplorePostsActivity(this,list);
        __recylerView_mainRecyler.setAdapter(postAdapter_explorePostsActivity);
        __button_Post = inboxView.findViewById(R.id.__button_postad);

        __button_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UploadPostActivity.class);
                startActivity(intent);
            }
        });

        __button_filter = inboxView.findViewById(R.id.__button_filter);

        __button_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterFragment filterFragment = new FilterFragment();
                filterFragment.setEnterTransition(new Slide());
                filterFragment.setReturnTransition(new Slide());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.__constraintlayout_mainscreen_filter,filterFragment);
                __button_Post.setVisibility(View.GONE);
                __button_filter.setVisibility(View.GONE);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return inboxView;
    }

   public static void update()
   {
       postAdapter_explorePostsActivity.notifyDataSetChanged();
   }

    @Override
    public void onResume() {
        super.onResume();
        FilterFragment.productPrice=0;
        update();
    }

    @Override
    public void onCLickPost(int position) {
        Intent intent = new Intent(getContext(), PostDescActivity.class);
        startActivity(intent);
    }

}