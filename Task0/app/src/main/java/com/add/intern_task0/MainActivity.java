package com.add.intern_task0;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button button;
    TextView namedisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.regn_no_edittext);
        button = (Button) findViewById(R.id.show_name_button);
        namedisplay = (TextView) findViewById(R.id.name_textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = name.getText().toString();
                if (s.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please Enter a Registration Number!",Toast.LENGTH_SHORT).show();
                } else {
                    button.setBackgroundColor(Color.GREEN);
                    namedisplay.setText("Anupam Dung Dung");
                }

            }
        });
    }
}
