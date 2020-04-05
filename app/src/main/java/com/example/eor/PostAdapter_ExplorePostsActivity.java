package com.example.eor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PostAdapter_ExplorePostsActivity extends RecyclerView.Adapter<PostAdapter_ExplorePostsActivity.VH>
{


    ItemListener_ExplorePostsActivity iL;
    public PostAdapter_ExplorePostsActivity(ItemListener_ExplorePostsActivity iL)
    {
           this.iL=iL;
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
        return 30;
    }

    @Override
    public void onBindViewHolder(@NonNull final  PostAdapter_ExplorePostsActivity.VH holder, final int position)
    {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             iL.onCLickPost(position);
            }
        });

    }

    public class VH extends RecyclerView.ViewHolder
    {

        TextView __textview_itemTitle;
        TextView __textview_userName;
        TextView __textview_location;
        TextView __textview_price;



        public VH(@NonNull View itemView) {
            super(itemView);

            __textview_itemTitle=itemView.findViewById(R.id.__textview_itemtitle);
            __textview_userName=itemView.findViewById(R.id.__textview_username);
            __textview_location=itemView.findViewById(R.id.__textview_location);
            __textview_price=itemView.findViewById(R.id.__textview_price);


        }
    }
}
