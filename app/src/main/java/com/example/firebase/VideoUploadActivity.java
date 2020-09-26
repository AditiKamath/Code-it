package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class VideoUploadActivity extends AppCompatActivity {
   private TabLayout tabLayout;
   private ViewPager viewPager;
   private TabItem tab1,tab2;
   public PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoupload);
        tabLayout = findViewById(R.id.tab_layout);
        tab1 = findViewById((R.id.tab1));
        tab2 = findViewById((R.id.tab2));
        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new com.example.firebase.PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager .setAdapter(pagerAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
                    pagerAdapter.notifyDataSetChanged();
                else if(tab.getPosition()==1)
                    pagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}