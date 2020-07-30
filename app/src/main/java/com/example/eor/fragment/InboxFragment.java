package com.example.eor.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eor.R;
import com.example.eor.activity.ChatActivity;
import com.example.eor.activity.SlidingDrawerActivity;
import com.example.eor.adapter.MyMessagesDataAdapter;
import com.example.eor.dao.PostDescription_DAO;
import com.example.eor.listener.MessageListener;
import com.example.eor.model.MyMessagesData;
import com.example.eor.model.PostDescription_Model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment implements MessageListener {

    RecyclerView messagerecyclerView;
    PostDescription_DAO postDescription_dao;
    PostDescription_Model postDescription_model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inboxView = inflater.inflate(R.layout.fragment_inbox,container,false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final MyMessagesDataAdapter myMessagesDataAdapter = new MyMessagesDataAdapter(this,getContext());

        postDescription_dao = new PostDescription_DAO();

        messagerecyclerView = inboxView.findViewById(R.id.__recyclerview_messageView);
        messagerecyclerView.setHasFixedSize(true);
        messagerecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        messagerecyclerView.setAdapter(myMessagesDataAdapter);

        db.collection("chats")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) { List<MyMessagesData> grps = new ArrayList<>();
                        if(queryDocumentSnapshots!=null){
                            for(DocumentSnapshot doc: queryDocumentSnapshots){
                                if(doc.getId().contains("_" + SlidingDrawerActivity.USER_ID)){
                                    postDescription_model = postDescription_dao.getPost(doc.getId().substring(0,5));
                                    grps.add(new MyMessagesData(null,
                                            doc.getId(),postDescription_model.getPostTitle(),
                                            null,
                                            postDescription_model.getImagePath().get(0),postDescription_model.getUser_fname(),postDescription_model.getUser_city()));
                                }
                            }
                        }
                        Log.d("CHATS", "onEvent: "+grps);
                        myMessagesDataAdapter.setList(grps);Log.d("TAG", "onSuccess: ");
                    }
                });

        return inboxView;
    }

    @Override
    public void ViewonClick(int pos) {
        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra("Itemname",MyMessagesDataAdapter.messagesData.get(pos).get__firebaseChatID());
        startActivity(intent);
    }
}
