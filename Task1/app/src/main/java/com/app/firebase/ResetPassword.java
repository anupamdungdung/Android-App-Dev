package com.app.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    EditText email;
    Button resetPassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        email=(EditText)findViewById(R.id.emailReset);
        resetPassword=(Button)findViewById(R.id.passwordReset);
        auth=FirebaseAuth.getInstance();
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailReset=email.getText().toString();
                if(TextUtils.isEmpty(emailReset)){
                    Toast.makeText(ResetPassword.this, "Please enter your correct e-mail", Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.sendPasswordResetEmail(emailReset).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetPassword.this, "Please visit your e-mail to reset your password", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPassword.this, LoginActivity.class));
                            }
                            else{
                                String message=task.getException().getMessage();
                                Toast.makeText(ResetPassword.this, "Error: "+message, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }
}
