package com.example.eor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements ItemListener_ExplorePostsActivity{

    RecyclerView __recylerView_mainRecyler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inboxView = inflater.inflate(R.layout.fragment_home,container,false);
        __recylerView_mainRecyler=inboxView.findViewById(R.id.__recylerview_mainContent);
        __recylerView_mainRecyler.setLayoutManager(new LinearLayoutManager(getContext()));
        __recylerView_mainRecyler.setAdapter(new PostAdapter_ExplorePostsActivity(this));
        return inboxView;
    }

    @Override
    public void onCLickPost(int position) {
        Intent intent = new Intent(getContext(),PostDescActivity.class);
        startActivity(intent);
    }
}