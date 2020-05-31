package com.app.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Academic extends AppCompatActivity {
    Button feeStructure, onlinepayment, backpaper, retreive;
    TextView retrieveDetails;
    StorageReference storageReference;
    StorageReference ref;

    private FirebaseAuth auth;
    private FirebaseFirestore fb;
    private DocumentReference documentReference;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);
        feeStructure = (Button) findViewById(R.id.feeStructure);
        onlinepayment = (Button) findViewById(R.id.onlinePayment);
        backpaper = (Button) findViewById(R.id.backpaper);
        retreive = (Button) findViewById(R.id.retrieveBackpaper);
        retrieveDetails = (TextView) findViewById(R.id.retrieveDetailsTv);

        fb = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();

        feeStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }
        });
        onlinepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.cet.edu.in/billpayment/");
            }
        });
        backpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Academic.this, Backpaper.class));
            }
        });
        retreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentReference = fb.collection("Backpaper Registration").document(uid);
                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String nameDepartment = documentSnapshot.getString("Name of Department");
                            String nameStudent = documentSnapshot.getString("Name of Student");
                            String regnNo = documentSnapshot.getString("Registration No");
                            String phone = documentSnapshot.getString("Phone");
                            String email = documentSnapshot.getString("Email");
                            String subject1 = documentSnapshot.getString("Subject 1");
                            String subject2 = documentSnapshot.getString("Subject 2");
                            String subject3 = documentSnapshot.getString("Subject 3");
                            String subject4 = documentSnapshot.getString("Subject 4");
                            String subject5 = documentSnapshot.getString("Subject 5");
                            String paymentDetails = documentSnapshot.getString("Payment Details(Rs.)");
                            String paymentDate = documentSnapshot.getString("Payment Date");
                            retrieveDetails.setText("Name of Department: " + nameDepartment + "\n" +
                                    "Name of Student: " + nameStudent + "\n" + "Registration No: " + regnNo + "\n" +
                                    "Phone: " + phone + "\n" + "Email: " + email + "\n\n" + "Subject 1: " + subject1 + "\n\n" +
                                    "Subject 2:" + subject2 + "\n\n" + "Subject 3: " + subject3 + "\n\n" + "Subject 4: " + subject4 + "\n\n" +
                                    "Subject 5: " + subject5 + "\n\n" + "Payment Details: " + paymentDetails + "\n" + "Payment Date: " + paymentDate
                            );


                        } else {
                            Toast.makeText(Academic.this, "Registration details does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Academic.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d("Academic Activity", e.toString());

                    }
                });
            }
        });

    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);

    }

    private void download() {
        storageReference = FirebaseStorage.getInstance().getReference();
        ref = storageReference.child("feeStructure.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(Academic.this, "Fee Structure", ".pdf", DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);
    }
}
