package com.example.eor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eor.R;
import com.example.eor.activity.SlidingDrawerActivity;
import com.example.eor.model.Chat;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{

    Context mContext;
    static ArrayList<Chat> mData;

    public ChatAdapter(Context mContext, ArrayList<Chat> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        if(mData.get(position).getUserid().equals(SlidingDrawerActivity.USER_ID))
        {
            return R.layout.activity_recevier_chat;
        }
        return R.layout.activity_sender_chat;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        return new ChatViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.ChatDescription.setText(mData.get(position).getChat());
        if(mData.get(position).getUserid().equals("U0013"))
            holder.imageView.setImageResource(R.drawable.prem_1);
        else
            holder.imageView.setImageResource(R.drawable.__default_user);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView ChatDescription;
        View view;
        ImageView imageView;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            ChatDescription = itemView.findViewById(R.id.__textview_description_chat);
            imageView = itemView.findViewById(R.id.__imageview_usericonimage_chat);
            view = itemView;
        }
    }
}
