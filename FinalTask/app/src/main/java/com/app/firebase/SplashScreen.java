package com.app.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread myThread = new Thread(() -> {
            try {
                sleep(2500);
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        myThread.start();
    }
}
