package com.example.eor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eor.listener.ItemListener_ExplorePostsActivity;
import com.example.eor.R;
import com.example.eor.dao.ExplorePost_DAO;
import com.example.eor.model.ExplorePost_Model;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PostAdapter_ExplorePostsActivity extends RecyclerView.Adapter<PostAdapter_ExplorePostsActivity.VH>
{
    List<ExplorePost_Model> list;
    ItemListener_ExplorePostsActivity iL;
    public static String post_id="";
    int count;




    public PostAdapter_ExplorePostsActivity(ItemListener_ExplorePostsActivity iL, List<ExplorePost_Model> list)
    {
        this.iL=iL;
        this.list = ExplorePost_DAO.list;
    }




    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_explorepostsitem,parent,false);
        VH vh=new VH(view);
        return vh;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final  PostAdapter_ExplorePostsActivity.VH holder, final int position)
    {


            holder.__textview_itemTitle.setText(list.get(position).getTitle());
            holder.__textview_userName.setText(list.get(position).getUsername());
            holder.__textview_location.setText(list.get(position).getLocation());
            holder.__textview_price.setText(String.valueOf(list.get(position).getPrice()));

            if (list.get(position).getImage_path() == null) {
                holder.__imageview_post.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.__defaultimageicon));
            } else {
                Picasso.with(holder.itemView.getContext()).load(list.get(position).getImage_path()).resize(200, 200).
                        centerCrop().into(holder.__imageview_post);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iL.onCLickPost(position);
                    post_id = list.get(position).getId();
                }
            });




    }





    public class VH extends RecyclerView.ViewHolder
    {

        TextView __textview_itemTitle;
        TextView __textview_userName;
        TextView __textview_location;
        TextView __textview_price;
        ImageView __imageview_post;



        public VH(@NonNull View itemView) {
            super(itemView);

            __textview_itemTitle=itemView.findViewById(R.id.__textview_itemtitle);
            __textview_userName=itemView.findViewById(R.id.__textview_username);
            __textview_location=itemView.findViewById(R.id.__textview_location);
            __textview_price=itemView.findViewById(R.id.__textview_price);
            __imageview_post=itemView.findViewById(R.id.__imageview_defaultimage);


        }
    }

}
