package com.example.eor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.eor.dao.PostDescription_DAO;
import com.example.eor.model.Chat;
import com.example.eor.model.PostDescription_Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {


    RecyclerView ChatRecyclerView;
    ChatAdapter chatAdapter;
    EditText etMessage;
    ImageView send;
    TextView textView_location;
    TextView textView_username;
    ImageView imageView_chat;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Chat> chatArrayList;
    String name="";

    PostDescription_DAO postDescription_dao;
    PostDescription_Model postDescription_model;
    TextView itemtitle;
    boolean isNew = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        itemtitle=findViewById(R.id.__textview_itemtitle_chat);
        textView_location = findViewById(R.id.__textview_location_chat);
        imageView_chat = findViewById(R.id.__imageview_itemimage_chat);
        textView_username = findViewById(R.id.__textview_username_chat);

        postDescription_dao = new PostDescription_DAO();





        chatArrayList = new ArrayList<>();
        ChatRecyclerView = findViewById(R.id.__recycleview_chatlayout_chat);
        chatAdapter = new ChatAdapter(this,chatArrayList);
        Intent i=getIntent();
        name=i.getStringExtra("Itemname");

        postDescription_model = postDescription_dao.getPost(name.substring(0,5));


        textView_location.setText(postDescription_model.getUser_city());
        textView_username.setText(postDescription_model.getUser_fname());
        Picasso.with(this).load(postDescription_model.getImagePath().get(0)).resize(200,200)
                .centerCrop().into(imageView_chat);
        itemtitle.setText(postDescription_model.getPostTitle());


        isNew = i.hasExtra("new");
        if(isNew) addChat();

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

    private void addChat() {
        Map<String,Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put(name,null);
        db.collection("chats")
                .document(name)
                .set(stringObjectMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("SUCCESS", "onSuccess: CREATED WITH" + name);
                    }
                });
    }

    private void sendMessage(final String message) {

        final String loggedUSer = SlidingDrawerActivity.USER_ID;
        db.collection("chats")
                .document(name)
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
                .document(name)
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
