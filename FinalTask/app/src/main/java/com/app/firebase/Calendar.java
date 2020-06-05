package com.app.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Calendar extends AppCompatActivity {
    private PhotoView photoView;
//    ImageView calendar;
//    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//    private DatabaseReference databaseReference=firebaseDatabase.getReference();
//    private DatabaseReference first=databaseReference.child("calendar");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
//        calendar=(ImageView)findViewById(R.id.calendarImageView);
        photoView=(PhotoView)findViewById(R.id.holidaysPhotoView);

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        first.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String link=dataSnapshot.getValue(String.class);
//                Picasso.get().load(link).into(calendar);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}