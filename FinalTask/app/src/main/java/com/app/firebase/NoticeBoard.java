package com.app.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.firebase.models.NoticeModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class NoticeBoard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore fb;
    private FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        recyclerView = (RecyclerView) findViewById(R.id.noticeBoardRecycleView);
        fb = FirebaseFirestore.getInstance();
        //Query
        Query query = fb.collection("Notice Board").orderBy("serial", Query.Direction.DESCENDING);

        //Recycler Options
        FirestoreRecyclerOptions<NoticeModel> options = new FirestoreRecyclerOptions.Builder<NoticeModel>().setQuery(query, NoticeModel.class).build();

        adapter = new FirestoreRecyclerAdapter<NoticeModel, NoticeViewHolder>(options) {
            @NonNull
            @Override
            public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_single_item, parent, false);
                return new NoticeViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull NoticeViewHolder holder, int position, @NonNull NoticeModel model) {
                holder.noticeDate.setText(model.getDate());
                holder.noticeHeading.setText(model.getHeading());
                holder.noticeDesc.setText(model.getDescription());
                String linkString=model.getLink();
                Uri linkUri=Uri.parse(linkString);
                holder.noticeLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW,linkUri));
                    }
                });

            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    //Viewholder
    private class NoticeViewHolder extends RecyclerView.ViewHolder {
        private TextView noticeDate, noticeHeading, noticeDesc,noticeLink;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeDate = (TextView) itemView.findViewById(R.id.noticeDate);
            noticeHeading = (TextView) itemView.findViewById(R.id.noticeHeading);
            noticeDesc = (TextView) itemView.findViewById(R.id.noticeDesc);
            noticeLink = (TextView) itemView.findViewById(R.id.noticeLink);


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}