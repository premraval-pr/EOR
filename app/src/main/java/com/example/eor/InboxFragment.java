package com.example.eor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InboxFragment extends Fragment implements MessageListener{

    RecyclerView messagerecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inboxView = inflater.inflate(R.layout.fragment_inbox,container,false);

        final MyMessagesDataAdapter myMessagesDataAdapter = new MyMessagesDataAdapter();

        messagerecyclerView = inboxView.findViewById(R.id.__recyclerview_messageView);
        messagerecyclerView.setHasFixedSize(true);
        messagerecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        messagerecyclerView.setAdapter(myMessagesDataAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                myMessagesDataAdapter.notifyDataSetChanged();

            }
        }).attachToRecyclerView(messagerecyclerView);
        return inboxView;
    }

    @Override
    public void ViewonClick(int pos) {
        Toast t= Toast.makeText(getContext(),"Message: "+pos,Toast.LENGTH_SHORT);
        t.show();
    }
}
