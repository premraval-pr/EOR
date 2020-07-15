package com.example.eor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eor.R;
import com.example.eor.adapter.ChatAdapter;
import com.example.eor.model.Chat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {


    RecyclerView ChatRecyclerView;
    ChatAdapter chatAdapter;
    String demoChat = "demo";
    EditText etMessage;
    ImageView send;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Chat> chatArrayList;
    String name="";
    TextView itemtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        itemtitle=findViewById(R.id.__textview_itemtitle_chat);
        chatArrayList = new ArrayList<>();
        ChatRecyclerView = findViewById(R.id.__recycleview_chatlayout_chat);
        chatAdapter = new ChatAdapter(this,chatArrayList);
        Intent i=getIntent();
        name=i.getStringExtra("Itemname");
        itemtitle.setText(name);
        ChatRecyclerView.setAdapter(chatAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ChatRecyclerView.setLayoutManager(linearLayoutManager);
        etMessage = findViewById(R.id.__edittext_messagetype_chat);
        send = findViewById(R.id.__imageview_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(etMessage.getText().toString());
            }
        });
        loadChat();
    }

    private void sendMessage(final String message) {

        final String loggedUSer = SlidingDrawerActivity.USER_ID;
        db.collection("chats")
                .document(demoChat)
                .collection("messages")
                .add(new Chat(message,loggedUSer, Timestamp.now()))
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Message Not Sent",Toast.LENGTH_SHORT).show();
                    }
                });
        etMessage.setText("");
        hideKeyBoard();
    }

    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if(view!= null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void loadChat(){
        db.collection("chats")
                .document(demoChat)
                .collection("messages")
                .orderBy("timecreated")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            e.printStackTrace();
                            return;
                        }

                        if(queryDocumentSnapshots!=null){
                            chatArrayList.clear();
                            for (DocumentSnapshot snapshot : queryDocumentSnapshots){
                                chatArrayList.add(new Chat(snapshot.getString("chat"),snapshot.getString("userid"),snapshot.getTimestamp("timecreated")));
                            }
                            chatAdapter.notifyDataSetChanged();
                            chatAdapter.notifyItemInserted(chatArrayList.size());
                        }
                    }
                });
    }
}