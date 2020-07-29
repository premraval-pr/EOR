package com.example.eor.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eor.R;
import com.example.eor.adapter.MySavedPostsAdapter;
import com.example.eor.dao.Saved_Posts_DAO;
import com.example.eor.model.MySavedPostsModel;

import java.util.ArrayList;

public class SavedPostsFragment extends Fragment {

    Saved_Posts_DAO saved_posts_dao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_posts, container, false);
        ArrayList<MySavedPostsModel> myListData = new ArrayList<MySavedPostsModel>();
        saved_posts_dao = new Saved_Posts_DAO();


        myListData = saved_posts_dao.retrieveSavedPosts();
        RecyclerView recyclerView =  view.findViewById(R.id.__recyclerView_savedposts);
        MySavedPostsAdapter adapter = new MySavedPostsAdapter(myListData,view.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        return view;
    }
}