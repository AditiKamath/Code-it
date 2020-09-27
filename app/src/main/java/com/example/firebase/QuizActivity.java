package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void showQuiz(View view)
    {
        startActivity(new Intent(this,ShowQuizActivity.class));
    }

    public void addQuiz(View view)
    {
        startActivity(new Intent(this,AddQuizActivity.class));
    }
}