/*
 * Page Use: Message Adapter for Message
 * Date Created: March 11,2020
 * Author: Karan Patel
 */

package com.example.eor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyMessagesDataAdapter extends RecyclerView.Adapter<MyMessagesDataAdapter.MessageViewHolder>
{

    private ArrayList<MyMessagesData>  messagesData;
    private MessageListener messageListener;

    public MyMessagesDataAdapter(MessageListener messagesListener) {
        this.messageListener = messagesListener;
    }

    MyMessagesDataAdapter(){
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
        holder.__textView_MessageView.setText("The newest message here");
        holder.__textview_NameMessage.setText("Name " + position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageListener.ViewonClick(position);
            }
        });

        ConstraintLayout.LayoutParams __constraintLayoutParams_MessageElement = (ConstraintLayout.LayoutParams) holder.itemView.findViewById(R.id.__constraintlayout_messageSingleElement).getLayoutParams();
        if(position % 2 == 0)
        {
                __constraintLayoutParams_MessageElement.setMarginStart(20);
                __constraintLayoutParams_MessageElement.setMarginEnd(0);
                holder.__constraintLayout_MessageView.setPadding(20,0,40,0);
        }
        else {
                __constraintLayoutParams_MessageElement.setMarginStart(0);
                __constraintLayoutParams_MessageElement.setMarginEnd(20);
                holder.__constraintLayout_MessageView.setPadding(40,0,40,0);
        }
    }

    @Override
    public int getItemCount() {
        return 30;
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
