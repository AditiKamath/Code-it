package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowQuizActivity extends AppCompatActivity {

    private String name,link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz);
    }

    public void showQuiz(View view)
    {
        EditText editText = (EditText) findViewById(R.id.subject);
        String temp = editText.getText().toString();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Subjects/"+temp+"/Quiz");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {

                    name = snapshot.child("name").getValue().toString();
                    link = snapshot.child("link").getValue().toString();
                    TextView textView = (TextView) findViewById(R.id.quiz_name);
                    textView.setText(name);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(link));
                            startActivity(intent);
                        }
                    });
                }
                catch (Exception e)
                {
                    TextView textView = (TextView) findViewById(R.id.quiz_name);
                    textView.setText("No quiz available");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}