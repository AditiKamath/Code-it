package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;

public class CourseActivity extends AppCompatActivity {
    ListView listView;
    public static ArrayAdapter arrayAdapter;
    Button get_videos ;
    Button get_docs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.courseName);
        listView.setAdapter(arrayAdapter);
        get_videos = findViewById(R.id.get_videos);
        get_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),Videoretrieve.class);
                startActivity(intent2);
            }
        });
        get_docs = findViewById(R.id.get_documents);
        get_docs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), FileActivity.class);
                startActivity(intent2);
            }
        });

    }
}