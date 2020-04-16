package com.example.eor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.eor.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
        }
        ViewPager viewPager = findViewById(R.id.__viewpager_pager);
        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new LoginFragmentActivity());
        pagerAdapter.addFragment(new SignupFragmentActivity());
        viewPager.setAdapter(pagerAdapter);
        CirclePageIndicator circlePageIndicator = findViewById(R.id.__indicator_card);
        circlePageIndicator.setViewPager(viewPager);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    static class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }
}
