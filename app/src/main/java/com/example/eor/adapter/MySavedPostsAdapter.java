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
import com.example.eor.model.MySavedPostsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MySavedPostsAdapter extends RecyclerView.Adapter<MySavedPostsAdapter.SavedPostsViewHolder> {


    ArrayList<MySavedPostsModel> mySavedPostsModelList;
    Context context;

    public MySavedPostsAdapter(ArrayList<MySavedPostsModel> myListData,Context context) {
        this.mySavedPostsModelList = myListData;
        this.context = context;
    }

    @NonNull
    @Override
    public SavedPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View card_saved_posts = layoutInflater.inflate(R.layout.card_saved_post,parent,false);
        SavedPostsViewHolder savedPostsViewHolder = new SavedPostsViewHolder(card_saved_posts);
        return savedPostsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPostsViewHolder holder, int position) {
        final MySavedPostsModel mySavedPostsModel = mySavedPostsModelList.get(position);

        holder.textView_saved_post_title.setText(mySavedPostsModel.getTitle());
        holder.textView_saved_post_description.setText(mySavedPostsModel.getDescription());
        holder.textView_saved_post_rent.setText(mySavedPostsModel.getRent());
        holder.textView_saved_post_location.setText(mySavedPostsModel.getLocation());
        Picasso.with(context).load(mySavedPostsModel.getImage_url()).into(holder.imageView_saved_post);
    }

    @Override
    public int getItemCount() {
        return mySavedPostsModelList.size();
    }

    public class SavedPostsViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_saved_post;
        public TextView textView_saved_post_title;
        public TextView textView_saved_post_description;
        public TextView textView_saved_post_rent;
        public TextView textView_saved_post_location;

        public SavedPostsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView_saved_post_title = itemView.findViewById(R.id.__textview_saved_post_title);
            this.textView_saved_post_description = itemView.findViewById(R.id.__textview_saved_post_description);
            this.textView_saved_post_rent = itemView.findViewById(R.id.__textview_saved_post_rent);
            this.textView_saved_post_location = itemView.findViewById(R.id.__textview_saved_post_location);
            this.imageView_saved_post = itemView.findViewById(R.id.__imageview_saved_posts_image);
        }
    }
}
