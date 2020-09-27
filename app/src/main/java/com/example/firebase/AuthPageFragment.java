package com.example.firebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AuthPageFragment extends Fragment
{

    class ViewPageAdapter extends FragmentPagerAdapter
    {
        ViewPageAdapter(FragmentManager fm)
        {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position)
        {
            if(position == 0)
            {
                LoginFragment frag = new LoginFragment();
                frag.authEntity = authEntity;
                return frag;
            }
            else
            {
                RegisterFragment frag = new RegisterFragment();
                frag.authEntity = authEntity;
                return frag;
            }
        }

        @Override
        public int getCount()
        {
            return 2;
        }
    }

    boolean authEntity; //Student or admin

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_auth_page, container, false);

        //Setting the view pager adapter
        ((ViewPager)fragmentView.findViewById(R.id.auth_pager)).setAdapter(new ViewPageAdapter(getChildFragmentManager()));

        Log.d("auth", authEntity ? "Student" : "Admin");

        return fragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {

    }
}