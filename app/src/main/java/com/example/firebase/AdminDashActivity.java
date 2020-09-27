package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AdminDashActivity extends AppCompatActivity {
        Button gotovideoupload;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);
        gotovideoupload = findViewById(R.id.gotovideoupload);
        gotovideoupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideoUploadActivity.class);
                startActivity(intent);
            }
        });
        auth = FirebaseAuth.getInstance();

        ((TextView)findViewById(R.id.details)).setText(auth.getCurrentUser().getDisplayName() + ", " + auth.getCurrentUser().getEmail());

        ((Button)findViewById(R.id.logout_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
                startActivity(intent);
            }
        });
        ((Button)findViewById(R.id.goto_quizactivtiy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}