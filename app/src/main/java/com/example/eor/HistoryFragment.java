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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryFragment extends Fragment implements ItemListener{
    private RecyclerView rv;
    ArrayList<HistoryLayout> historyLayoutList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inboxView = inflater.inflate(R.layout.fragment_hisory,container,false);
        rv=inboxView.findViewById(R.id.__recyclerview_itemhistory);

        historyAdapter = new HistoryAdapter(historyLayoutList,this);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(historyAdapter);

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String todayDate= simpleDateFormat.format(new Date());


        historyLayoutList.add(new HistoryLayout("Mobile", "Sharvil Patel", "Toronto", "P0030", todayDate , todayDate));
        historyLayoutList.add(new HistoryLayout("TV", "Karan Patel", "Mississauga", "P0020", todayDate , todayDate));
        historyLayoutList.add(new HistoryLayout("Game", "Henil Parikh", "York", "P0043",todayDate , todayDate));
        return inboxView;
    }

    @Override
    public void onItemClick(int index) {
        Toast.makeText(getContext(),"Clicked Item: "+index,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReviewClick(int position) {
        Intent reviewBookingIntent = new Intent(getContext(),ReviewFragment.class);
        reviewBookingIntent.putExtra("Title",historyLayoutList.get(position).getTitle());
        reviewBookingIntent.putExtra("Username",historyLayoutList.get(position).getUsername());
        reviewBookingIntent.putExtra("Location",historyLayoutList.get(position).getLocation());
        reviewBookingIntent.putExtra("toDate",historyLayoutList.get(position).getToDate());
        reviewBookingIntent.putExtra("fromDate",historyLayoutList.get(position).getFromDate());
        startActivity(reviewBookingIntent);
    }
}
