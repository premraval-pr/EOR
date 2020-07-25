package com.example.eor.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eor.R;
import com.example.eor.adapter.MySavedPostsAdapter;
import com.example.eor.model.MySavedPostsModel;

import java.util.ArrayList;

public class SavedPostsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_posts, container, false);
        ArrayList<MySavedPostsModel> myListData = new ArrayList<MySavedPostsModel>();

        MySavedPostsModel mySavedPostsModel1 = new MySavedPostsModel("Car",
                "Nice Car","34$","Toronto",
                "https://i.ytimg.com/vi/SbXIj2T-_uk/maxresdefault.jpg");
        MySavedPostsModel mySavedPostsModel2 = new MySavedPostsModel("Cabinet",
                "Better Cabinet","84$","Markham",
                "https://cdn.shopify.com/s/files/1/2660/5202/products/qkyfl7exyethumztwur5_1400x.jpg?v=1571710271");
        MySavedPostsModel mySavedPostsModel3 = new MySavedPostsModel("Vase",
                "Best Vase","78$","Etobicoke",
                "https://images-na.ssl-images-amazon.com/images/I/61GY0RzC1tL._SY679_.jpg");
        MySavedPostsModel mySavedPostsModel4 = new MySavedPostsModel("Phone",
                "Future Phone","94$","Brampton",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQW5n30cKOELyv0sdUwxkyF4SE3Yn0DreR-TQ&usqp=CAU");
        MySavedPostsModel mySavedPostsModel5 = new MySavedPostsModel("My Car",
                "Best Car","78$","Etobicoke",
                "https://cdn-images.speedvegas.com/images/uploads/hennessey-venom.jpg");
        MySavedPostsModel mySavedPostsModel6 = new MySavedPostsModel("Food",
                "Future Food","94$","Brampton",
                "https://www.helpguide.org/wp-content/uploads/fast-foods-candy-cookies-pastries-768.jpg");
        myListData.add(mySavedPostsModel1);
        myListData.add(mySavedPostsModel2);
        myListData.add(mySavedPostsModel3);
        myListData.add(mySavedPostsModel4);
        myListData.add(mySavedPostsModel5);
        myListData.add(mySavedPostsModel6);
        RecyclerView recyclerView =  view.findViewById(R.id.__recyclerView_savedposts);
        MySavedPostsAdapter adapter = new MySavedPostsAdapter(myListData,view.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        return view;
    }
}