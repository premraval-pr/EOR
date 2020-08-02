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
import com.example.eor.model.ExplorePost_Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    Context context;
    List<ExplorePost_Model> items;
    public ProfileAdapter(Context context,   List<ExplorePost_Model> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_profile,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        System.out.println("Title --->"+items.get(position).getTitle());
        Picasso.with(context)
                .load(items.get(position).getImage_path())
                .resize(600,600)
                .into(holder.images);
        //holder.price.setText((int) items.get(position).getPrice());
        System.out.println("Price --->"+items.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, fullName, username, location, totalNumberPost;
        ImageView images;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.__textviewPost_nameOfPost);
            images = itemView.findViewById(R.id.__imageviewPost_imageOfPost);
            price = itemView.findViewById(R.id.__textviewPost_price);
//            fullName = itemView.findViewById(R.id.__textviewPost_firstname);
//            username = itemView.findViewById(R.id.__textviewPost_username);
//            location = itemView.findViewById(R.id.__textviewPost_location);
//            totalNumberPost = itemView.findViewById(R.id.__textviewPost_numberOfPostsinNumber);
        }
    }
}
