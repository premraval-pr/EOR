package com.example.eor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{

    Context mContext;
    List<Chat> mData;

    public ChatAdapter(Context mContext, List<Chat> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        if(mData.get(position).isMe())
        {
            return R.layout.activity_sender_chat;
        }
        return R.layout.activity_recevier_chat;

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
        holder.Usericon.setImageResource(mData.get(position).getUsericon());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView ChatDescription;
        ImageView Usericon;
        View view;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            ChatDescription = itemView.findViewById(R.id.__textview_description_chat);
            Usericon = itemView.findViewById(R.id.__imageview_usericonimage_chat);
            view = itemView;
        }
    }
}
