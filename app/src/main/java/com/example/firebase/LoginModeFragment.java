package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class LoginModeFragment extends Fragment
{

    View.OnClickListener optionBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            Log.d("b", "click");
            AuthPageFragment authPageFragment = new AuthPageFragment();
            authPageFragment.authEntity = (v.getId() == R.id.auth_student_btn);
            ((AuthActivity)getActivity()).displayFragment(authPageFragment, true);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View fragmentView =  inflater.inflate(R.layout.fragment_login_mode, container, false);

        //Setting the click listener
        ((Button)fragmentView.findViewById(R.id.auth_student_btn)).setOnClickListener(optionBtnClickListener);
        ((Button)fragmentView.findViewById(R.id.auth_admin_btn)).setOnClickListener(optionBtnClickListener);

        return fragmentView;
    }
}