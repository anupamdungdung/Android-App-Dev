package com.app.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Backpaper extends AppCompatActivity implements View.OnClickListener {

    private EditText departmentName,studentName,regNo,cgpa,phoneNo,eMail;
    private EditText subjectCode1,subjectCode2,subjectCode3,subjectCode4,subjectCode5;
    private EditText subjectName1,subjectName2,subjectName3,subjectName4,subjectName5;
    private EditText subjectCredit1,subjectCredit2,subjectCredit3,subjectCredit4,subjectCredit5;
    private EditText paymentDetails,paymentDate;

    FirebaseFirestore fb;
    DocumentReference documentReference;
    FirebaseAuth auth;
    String uid;

    private Button submit;
    private HashMap<String,Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpaper);
        departmentName=(EditText)findViewById(R.id.departmentName);
        studentName=(EditText)findViewById(R.id.studentName);
        regNo=(EditText)findViewById(R.id.regnNumber);
        cgpa=(EditText)findViewById(R.id.cgpa);
        phoneNo=(EditText)findViewById(R.id.phoneNumber);
        eMail=(EditText)findViewById(R.id.emailStudent);
        subjectCode1=(EditText)findViewById(R.id.subjectCode1);
        subjectCode2=(EditText)findViewById(R.id.subjectCode2);
        subjectCode3=(EditText)findViewById(R.id.subjectCode3);
        subjectCode4=(EditText)findViewById(R.id.subjectCode4);
        subjectCode5=(EditText)findViewById(R.id.subjectCode5);
        subjectName1=(EditText)findViewById(R.id.subjectName1);
        subjectName2=(EditText)findViewById(R.id.subjectName2);
        subjectName3=(EditText)findViewById(R.id.subjectName3);
        subjectName4=(EditText)findViewById(R.id.subjectName4);
        subjectName5=(EditText)findViewById(R.id.subjectName5);
        subjectCredit1=(EditText)findViewById(R.id.subjectCredit1);
        subjectCredit2=(EditText)findViewById(R.id.subjectCredit2);
        subjectCredit3=(EditText)findViewById(R.id.subjectCredit3);
        subjectCredit4=(EditText)findViewById(R.id.subjectCredit4);
        subjectCredit5=(EditText)findViewById(R.id.subjectCredit5);
        paymentDetails=(EditText)findViewById(R.id.paymentDetails);
        paymentDate=(EditText) findViewById(R.id.paymentDate);

        fb=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        uid=auth.getCurrentUser().getUid();

        submit=(Button)findViewById(R.id.submitApplication);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String txtDepartment=departmentName.getText().toString();
        String txtStudentName=studentName.getText().toString();
        String txtRegNo=regNo.getText().toString();
        String txtCgpa=cgpa.getText().toString();
        String txtPhoneNo=phoneNo.getText().toString();
        String txtEMail=eMail.getText().toString();
        String txtSubject1="Subject Code:"+subjectCode1.getText().toString()+"\n"+"Subject Name:"+subjectName1.getText().toString()+"\n"+"Credits:"+subjectCredit1.getText().toString();
        String txtSubject2="Subject Code:"+subjectCode2.getText().toString()+"\n"+"Subject Name:"+subjectName2.getText().toString()+"\n"+"Credits:"+subjectCredit2.getText().toString();
        String txtSubject3="Subject Code:"+subjectCode3.getText().toString()+"\n"+"Subject Name:"+subjectName3.getText().toString()+"\n"+"Credits:"+subjectCredit3.getText().toString();
        String txtSubject4="Subject Code:"+subjectCode4.getText().toString()+"\n"+"Subject Name:"+subjectName4.getText().toString()+"\n"+"Credits:"+subjectCredit4.getText().toString();
        String txtSubject5="Subject Code:"+subjectCode5.getText().toString()+"\n"+"Subject Name:"+subjectName5.getText().toString()+"\n"+"Credits:"+subjectCredit5.getText().toString();
        String txtPaymentDetails=paymentDetails.getText().toString();
        String txtPaymentDate=paymentDate.getText().toString();
        if(txtDepartment.isEmpty() || txtStudentName.isEmpty() || txtRegNo.isEmpty() || txtCgpa.isEmpty() || txtPhoneNo.isEmpty() || txtEMail.isEmpty() ||
        txtPaymentDetails.isEmpty() || txtPaymentDate.isEmpty()){
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
        if(txtSubject1.isEmpty() && txtSubject2.isEmpty() && txtSubject3.isEmpty() && txtSubject4.isEmpty() && txtSubject5.isEmpty()){
            Toast.makeText(this, "Please enter atleast 1 subject", Toast.LENGTH_SHORT).show();
        }
        map=new HashMap<>();
        map.put("Name of Department",txtDepartment);
        map.put("Name of Student",txtStudentName);
        map.put("Registration No",txtRegNo);
        map.put("CGPA in Previous Semester",txtCgpa);
        map.put("Phone",txtPhoneNo);
        map.put("Email",txtEMail);
        map.put("Subject 1",txtSubject1);
        map.put("Subject 2",txtSubject2);
        map.put("Subject 3",txtSubject3);
        map.put("Subject 4",txtSubject4);
        map.put("Subject 5",txtSubject5);
        map.put("Payment Details(Rs.)",txtPaymentDetails);
        map.put("Payment Date",txtPaymentDate);

        documentReference=fb.collection("Backpaper Registration").document(uid);

        documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Backpaper.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Backpaper.this,Academic.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Backpaper.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}
