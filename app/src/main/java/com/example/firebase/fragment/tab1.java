package com.example.firebase.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firebase.R;
import com.example.firebase.Video;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;


public class tab1 extends Fragment {
    private Button chooseBtn;
    private Button uploadBtn;
    private VideoView videoView;
    private Uri videoUri;
    MediaController mediaController;
    private StorageReference mStorageRef;
    private EditText video_name;
    private ProgressBar progressBar;
    private DatabaseReference mDataBaseRef;

    private static final int PICK_VIDEO_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_tab1, container, false);
        chooseBtn = view.findViewById(R.id.choose_btn);
        uploadBtn =  view.findViewById(R.id.upload_btn);
        video_name = view.findViewById(R.id.video_name);
        videoView = view.findViewById(R.id.Video_view);
        progressBar = view.findViewById(R.id.progress_bar);
        mediaController = new MediaController(getContext());

        mStorageRef = FirebaseStorage.getInstance().getReference("videos");
        mDataBaseRef = FirebaseDatabase.getInstance().getReference("videos");

        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();
        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseVideo();
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadVideo();
            }
        });

        return view;
    }
    private  void ChooseVideo(){
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_VIDEO_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            videoUri = data.getData();

            videoView.setVideoURI(videoUri);


        }}
    private String getFileExtension(Uri videoUri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(videoUri));
    }

    private void UploadVideo() {

        progressBar.setVisibility(View.VISIBLE);
        if (videoUri != null){
            StorageReference reference = mStorageRef.child(System.currentTimeMillis() +
                    "." +getFileExtension(videoUri));

            reference.putFile(videoUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(),"Upload successful",Toast.LENGTH_SHORT).show();
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(task ->
                            {
                                String url = task.getResult().toString();
                                Video member = new Video(video_name.getText().toString().trim(),
                                        url);
                                String UploadId = mDataBaseRef.push().getKey();
                                mDataBaseRef.child(UploadId).setValue(member);

                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });


        }else {
            Toast.makeText(getContext(),"No file selected", Toast.LENGTH_SHORT).show();
        }


    }

}