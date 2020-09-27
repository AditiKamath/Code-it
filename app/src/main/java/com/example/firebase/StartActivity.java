package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    public void toGraph(View view){
        MainActivity.isviewingGraph = true;
        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent1);

    }
    public void toCourses(View view){
        Intent intent2 = new Intent(getApplicationContext(),CourseActivity.class);
        startActivity(intent2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button button_quiz = findViewById(R.id.buttonQuizes);
        button_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),ShowQuizActivity.class);
                startActivity(intent2);
            }
        });
    }
}