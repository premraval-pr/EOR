package com.example.eor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment implements ItemListener_ExplorePostsActivity
{
    RecyclerView __recylerView_mainRecyler;
    ItemListener_ExplorePostsActivity __itemListener;
    List<ExplorePost_Model> list;
    ExplorePost_UserPost_DAO explorePost_dao;

    List<ExplorePost_Model> filteredList = new ArrayList<ExplorePost_Model>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
         list=new ArrayList<>();
         View inboxView = inflater.inflate(R.layout.fragment_posts,container,false);
          __itemListener=this;
          explorePost_dao=new ExplorePost_UserPost_DAO();
          explorePost_dao.collectData();
          list=ExplorePost_UserPost_DAO.list;
          __recylerView_mainRecyler=inboxView.findViewById(R.id.recyler_view_for_userpost);
          __recylerView_mainRecyler.setLayoutManager(new LinearLayoutManager(getContext()));
          __recylerView_mainRecyler.setAdapter(new PostAdapter_ExplorePostActivity_UserPosts(__itemListener, list));
          return inboxView;
    }

    @Override
    public void onCLickPost(int position) {

    }
}
