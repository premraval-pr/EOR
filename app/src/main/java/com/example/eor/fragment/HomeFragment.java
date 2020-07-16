package com.example.eor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

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
import com.example.eor.listener.ItemListener_ExplorePostsActivity;
import com.example.eor.model.ExplorePost_Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements ItemListener_ExplorePostsActivity {

    RecyclerView __recylerView_mainRecyler;
    Button __button_map;
    FloatingActionButton __button_Post;
    List<ExplorePost_Model> list;
    FrameLayout __frame_map;
    List<ExplorePost_Model> filteredlist = new ArrayList<ExplorePost_Model>();
    ExplorePost_DAO explorePost_dao;
    public static PostAdapter_ExplorePostsActivity postAdapter_explorePostsActivity;
    MapsFragment mapsFragment;


    Button __button_filter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inboxView = inflater.inflate(R.layout.fragment_home,container,false);
        list = ExplorePost_DAO.list;
        explorePost_dao=new ExplorePost_DAO();
        explorePost_dao.collectData();
        list=ExplorePost_DAO.list;
        mapsFragment = new MapsFragment();

        __recylerView_mainRecyler=inboxView.findViewById(R.id.__recylerview_mainContent);
        __recylerView_mainRecyler.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter_explorePostsActivity = new PostAdapter_ExplorePostsActivity(this,list);
        __recylerView_mainRecyler.setAdapter(postAdapter_explorePostsActivity);
        __button_Post = inboxView.findViewById(R.id.__button_postad);
        __button_map = inboxView.findViewById(R.id.__button_mapview);
        __frame_map = inboxView.findViewById(R.id.__frame_map);

        __button_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UploadPostActivity.class);
                startActivity(intent);
            }
        });

        __button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (__frame_map.getVisibility()) {
                    case View.GONE:
                        __frame_map.setVisibility(View.VISIBLE);
                        break;
                    case View.VISIBLE:
                        __frame_map.setVisibility(View.GONE);
                        break;
                    case View.INVISIBLE:
                        break;
                }
            }
        });

        __recylerView_mainRecyler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mapsFragment.animate(((LinearLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager())).findFirstVisibleItemPosition());
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
                __button_map.setVisibility(View.GONE);
                __button_filter.setVisibility(View.GONE);
                __button_Post.hide();
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.__frame_map,mapsFragment)
                .commit();
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