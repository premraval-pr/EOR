package com.example.eor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.eor.R;
import com.example.eor.dao.PostDescription_DAO;
import com.example.eor.model.PostDescription_Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageViewPageAdapter extends PagerAdapter {

    PostDescription_Model postDescription_model;

    private Context context;
    private LayoutInflater layoutInflater;
    public ArrayList<String> images;

    public ImageViewPageAdapter(Context context, ArrayList<String> imagePath) {
        this.context = context;
        images=imagePath;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_imageview,null);
        ImageView imageView = view.findViewById(R.id.__imageview_sliderimage);
        Picasso.with(context).load(images.get(position)).into(imageView);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,position);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
