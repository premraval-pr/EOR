package com.example.eor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
public class PostAdapter_ExplorePostActivity_UserPosts extends RecyclerView.Adapter<PostAdapter_ExplorePostActivity_UserPosts.VH> {

    ItemListener_ExplorePostsActivity iL;
    List<ExplorePost_Model> list;
    View vw;

    public PostAdapter_ExplorePostActivity_UserPosts(ItemListener_ExplorePostsActivity iL, List<ExplorePost_Model> list)
    {
        this.iL=iL;
        this.list =ExplorePost_UserPost_DAO.list;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_post,parent,false);
        VH vh=new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder,final int position) {

        holder.__textview_itemTitle.setText(list.get(position).getTitle());
        holder.__textview_userName.setText(list.get(position).getUsername());
        holder.__textview_location.setText(list.get(position).getLocation());
        holder.__textview_price.setText(String.valueOf(list.get(position).getPrice()));

        if(list.get(position).getImage_path()==null) {
            holder.__imageview_post.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.__defaultimageicon));
        }
        else {
            Picasso.with(holder.itemView.getContext()).load(list.get(position).image_path).resize(200, 200).
                    centerCrop().into(holder.__imageview_post);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iL.onCLickPost(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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

            __textview_itemTitle=itemView.findViewById(R.id.__textview_userpost_itemtitle);
            __textview_userName=itemView.findViewById(R.id.__textview_userpost_username);
            __textview_location=itemView.findViewById(R.id.__textview_userpost_location);
            __textview_price=itemView.findViewById(R.id.__textview_userpost_price);
            __imageview_post=itemView.findViewById(R.id.__imageview_userpost_defaultimage);
        }
    }
}
