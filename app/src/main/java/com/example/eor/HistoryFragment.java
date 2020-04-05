package com.example.eor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryFragment extends Fragment implements ItemListener{
    private RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inboxView = inflater.inflate(R.layout.fragment_hisory,container,false);
        rv=inboxView.findViewById(R.id.__recyclerview_itemhistory);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new HistoryAdapter(this));
        return inboxView;
    }

    @Override
    public void onItemClick(int index) {
        Toast.makeText(getContext(),"Clicked Item: "+index,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReviewClick(int position) {
        Intent reviewBookingIntent = new Intent(getContext(),ReviewFragment.class);
        startActivity(reviewBookingIntent);
    }
}
