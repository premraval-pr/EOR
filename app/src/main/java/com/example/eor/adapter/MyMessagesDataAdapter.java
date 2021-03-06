/*
 * Page Use: Message Adapter for Message
 * Date Created: March 11,2020
 * Author: Karan Patel
 */

package com.example.eor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eor.activity.SlidingDrawerActivity;
import com.example.eor.fragment.InboxFragment;
import com.example.eor.listener.MessageListener;
import com.example.eor.R;
import com.example.eor.model.MyMessagesData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MyMessagesDataAdapter extends RecyclerView.Adapter<MyMessagesDataAdapter.MessageViewHolder>
{

    public static ArrayList<MyMessagesData>  messagesData = new ArrayList<>();
    private MessageListener messageListener;
    Context context;

    public MyMessagesDataAdapter(MessageListener messagesListener,Context context) {

        this.messageListener = messagesListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater __layoutinflator_messageview = LayoutInflater.from(parent.getContext());
        View messageItem = __layoutinflator_messageview.inflate(R.layout.layout_messagesingleelement,parent,false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(messageItem);
        return messageViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyMessagesDataAdapter.MessageViewHolder holder, final int position) {
        holder.__textView_MessageView.setText(messagesData.get(position).get__textview_MessageShort());
        holder.__textview_NameMessage.setText(messagesData.get(position).get__textview_NameForMessageView());
        Picasso.with(context).load(messagesData.get(position).get__imageview_Message()).resize(200,200)
                .centerCrop().into(holder.__imageView_MessageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageListener.ViewonClick(position);
            }
        });

        ConstraintLayout.LayoutParams __constraintLayoutParams_MessageElement =
                (ConstraintLayout.LayoutParams)
                        holder.itemView.findViewById(R.id.__constraintlayout_messageSingleElement).getLayoutParams();
        String[] mesList = messagesData.get(position).get__firebaseChatID().split("_");

        if(SlidingDrawerActivity.USER_ID.equals(mesList[1]))
        {
                __constraintLayoutParams_MessageElement.setMarginStart(20);
                __constraintLayoutParams_MessageElement.setMarginEnd(0);
                holder.__constraintLayout_MessageView.setPadding(20,0,40,0);
        }
        else if(SlidingDrawerActivity.USER_ID.equals(mesList[2])) {
                __constraintLayoutParams_MessageElement.setMarginStart(0);
                __constraintLayoutParams_MessageElement.setMarginEnd(20);
                holder.__constraintLayout_MessageView.setPadding(40,0,40,0);
        }

    }

    @Override
    public int getItemCount() {
        return messagesData.size();
    }

    public void setList(List<MyMessagesData> grps) {
        messagesData.clear();
        messagesData.addAll(grps);
        notifyDataSetChanged();
    }


    static class MessageViewHolder extends RecyclerView.ViewHolder {
        ImageView __imageView_MessageView;
        TextView __textView_MessageView;
        TextView __textview_NameMessage;
        ConstraintLayout __constraintLayout_MessageView;


        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.__imageView_MessageView =  itemView.findViewById(R.id.__imageview_iconmessagecontact);
            this.__textView_MessageView =  itemView.findViewById(R.id.__textview_messageshortview);
            this.__textview_NameMessage = itemView.findViewById(R.id.__textview_namemessagecontact);
            this.__constraintLayout_MessageView =  itemView.findViewById(R.id.__constraintlayout_messageSingleElement);
        }

    }


}
