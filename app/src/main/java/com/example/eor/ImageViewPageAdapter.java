package com.example.eor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageViewPageAdapter extends PagerAdapter {

    PostDescription_Model postDescription_model;

    private Context context;
    private LayoutInflater layoutInflater;
    public List<String> images;

    public ImageViewPageAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        images=PostDescription_DAO.image_post;
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
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
