package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartActivity extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseAuth auth;
    TextView topTextView;
    public void toGraph(View view){
        MainActivity.isviewingGraph = true;
        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent1);

    }
    public void toCourses(View view){
        Intent intent2 = new Intent(getApplicationContext(),CourseActivity.class);
        startActivity(intent2);
    }

    public void toprofile(View view){
        Intent intent3 = new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(intent3);
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
        Button button_courses = findViewById(R.id.buttonCourses);
        button_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CourseActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        Button logout = findViewById(R.id.log_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Student");
        topTextView = findViewById(R.id.topTextView);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dss: snapshot.getChildren()){
                        //if condition needs to be added for user that has signed in or it will iterate and add all the users available in the
                        //database
                        String userName = dss.getValue().toString();
                        topTextView.setText(auth.getCurrentUser().getDisplayName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}