package com.example.eor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    ItemListener itemListener;
    ViewHolder changeholder = null;
    boolean open = false;

    public HistoryAdapter(ItemListener listener) {
        this.itemListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itemhistory,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.itemTitleTV.setText("Item Title - "+position);
        holder.userNameTV.setText("Username - "+position);
        holder.locationTV.setText("Location - "+position);
        holder.dropDownImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.dropDownImage.setRotation(holder.dropDownImage.getRotation() + 180);
                itemListener.onItemClick(position);
                toggleVisibility(holder.fromddTV.getVisibility(),holder);

            }
        });
        holder.reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onReviewClick(position);
            }
        });
    }

    private void toggleVisibility(int isOn, ViewHolder holder){
        if(isOn == View.VISIBLE){
            holder.fromddTV.setVisibility(View.GONE);
            holder.fromTV.setVisibility(View.GONE);
            holder.toddTV.setVisibility(View.GONE);
            holder.toTV.setVisibility(View.GONE);
            holder.reviewButton.setVisibility(View.GONE);
            open=true;
        }
        else {
            holder.fromddTV.setVisibility(View.VISIBLE);
            holder.fromTV.setVisibility(View.VISIBLE);
            holder.toddTV.setVisibility(View.VISIBLE);
            holder.toTV.setVisibility(View.VISIBLE);
            holder.reviewButton.setVisibility(View.VISIBLE);


            if(changeholder != holder && changeholder!=null )
            {
                changeholder.fromddTV.setVisibility(View.GONE);
                changeholder.fromTV.setVisibility(View.GONE);
                changeholder.toddTV.setVisibility(View.GONE);
                changeholder.toTV.setVisibility(View.GONE);
                changeholder.reviewButton.setVisibility(View.GONE);
                changeholder.dropDownImage.setRotation(changeholder.dropDownImage.getRotation()+ 180);
            }
            changeholder=holder;

        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitleTV, userNameTV, locationTV, fromTV, fromddTV, toTV, toddTV;
        ImageView userNameImg, locationImg, dropDownImage;
        Button reviewButton;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitleTV = itemView.findViewById(R.id.__textview_itemhistorytitle);
            userNameTV = itemView.findViewById(R.id.__textview_historyusername);
            locationTV = itemView.findViewById(R.id.__textview_historylocation);
            userNameImg = itemView.findViewById(R.id.__imageview_usernameimage);
            locationImg = itemView.findViewById(R.id.__imageview_locationimage);
            dropDownImage = itemView.findViewById(R.id.__imageview_dropdown);
            fromTV = itemView.findViewById(R.id.__textview_from);
            fromddTV = itemView.findViewById(R.id.__textview_fromddmmyyyy);
            toTV = itemView.findViewById(R.id.__textview_to);
            toddTV = itemView.findViewById(R.id.__textview_toddmmyyyy);
            reviewButton = itemView.findViewById(R.id.__button_addreview);
            view=itemView;
        }
    }

}
