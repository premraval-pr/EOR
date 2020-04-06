package com.example.eor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatActivity extends AppCompatActivity {


    RecyclerView ChatRecyclerView;
    ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ChatRecyclerView = findViewById(R.id.__recycleview_chatlayout_chat);
        chatAdapter = new ChatAdapter(this,ChatProvider.chats);
        ChatRecyclerView.setAdapter(chatAdapter);
        ChatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
