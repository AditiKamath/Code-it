package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
    }

    public void submitQuiz(View view) {
        String temp;
        EditText editText = (EditText) findViewById(R.id.subject);
        temp=editText.getText().toString();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Subjects/"+temp+"/Quiz");
        editText = (EditText) findViewById(R.id.name);
        temp=editText.getText().toString();
        databaseReference.child("name").setValue(temp);
        editText = (EditText) findViewById(R.id.link);
        temp=editText.getText().toString();
        databaseReference.child("link").setValue(temp);
        Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}