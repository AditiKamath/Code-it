package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static boolean isviewingGraph = false;
    DatabaseReference reff;

    public static ArrayList<String>courseName = new ArrayList<String>();
    public static ArrayList<Float>progressStats = new ArrayList<Float>();

    public void getCourse() {


        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dss: snapshot.child("Courses").getChildren()){
                        String cName = dss.getValue().toString();
                        courseName.add(cName);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void getProgress() {


        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dss: snapshot.child("Progress").getChildren()){
                        String progress = dss.getValue().toString();
                        progressStats.add(Float.parseFloat(progress));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reff = FirebaseDatabase.getInstance().getReference().child("Student").child("1");
        getCourse();
        getProgress();
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        if(isviewingGraph) {

            setupPieChart();

        }else{
            startActivity(intent);
        }
    }

    private  void setupPieChart() {
        // populating a list of pie entries
        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry>();
        for(int i = 0 ; i<progressStats.size() ; i++){
            pieEntries.add(new PieEntry(progressStats.get(i),courseName.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"Student Course Progress Details");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        //Get the chart
        PieChart chart = findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
    }
}