package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.firebase.fragment.tab1;
import com.example.firebase.fragment.tab2;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

  public PagerAdapter(FragmentManager fm,int numOfTabs){
      super(fm);
      this.numOfTabs = numOfTabs;

  }

    @NonNull
    @Override
    public Fragment getItem(int position) {
      switch (position){
          case 0 :
              return new tab1();
          case 1:
              return new tab2();
          default:
              return  null;
      }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
