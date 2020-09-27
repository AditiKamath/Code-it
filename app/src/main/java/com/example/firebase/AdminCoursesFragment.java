package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AdminCoursesFragment extends Fragment {

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
        class RecyclerViewHolder extends RecyclerView.ViewHolder {
            RecyclerViewHolder(View cardView) {
                super(cardView);
            }
        }

        private ArrayList<DocumentSnapshot> queryResult;

        RecyclerAdapter(QuerySnapshot qs) {
            if (qs != null)
                queryResult = (ArrayList) qs.getDocuments();
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View cardView = LayoutInflater.from(getActivity()).inflate(R.layout.course_card, parent, false);

            return new RecyclerViewHolder(cardView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            ((TextView) holder.itemView.findViewById(R.id.course_name)).setText(queryResult.get(position).get("name").toString());
            ((TextView) holder.itemView.findViewById(R.id.course_enrolled)).setText(String.format("Enrolled : %d", (Long) queryResult.get(position).get("enrolled")));
        }

        @Override
        public int getItemCount() {
            return queryResult != null ? queryResult.size() : 0;
        }

        void update(QuerySnapshot qs) {
            queryResult = (ArrayList) qs.getDocuments();
            notifyDataSetChanged();
        }
    }

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    RecyclerAdapter recyclerAdapter;
    Button goto_quizactivtiy;
    Button gotovideoupload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_admin_courses, container, false);

        RecyclerView rv = fragmentView.findViewById(R.id.admin_courses_list);
        recyclerAdapter = new RecyclerAdapter(null);
        rv.setAdapter(recyclerAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        getCourses();
        goto_quizactivtiy = fragmentView.findViewById(R.id.goto_quizactivtiy);
        goto_quizactivtiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                startActivity(intent);
            }
        });
        gotovideoupload = fragmentView.findViewById(R.id.gotovideoupload);
        gotovideoupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VideoUploadActivity.class);
                startActivity(intent);
            }
        });

        return fragmentView;
    }

    private void getCourses() {
        db.collection("courses").whereEqualTo("adminId", firebaseAuth.getCurrentUser().getEmail()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                            recyclerAdapter.update(task.getResult());
                        else
                            Log.e("queryErr", task.getException().getMessage());
                    }
                });
    }

}