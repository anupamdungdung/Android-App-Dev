package com.app.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class LibraryFine extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button pickdate;
    TextView duedate,currentdate,totalFineET,daysPastDue;
    EditText fineET;
    long fine,totalFine;
    String totalfine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_fine);
        pickdate=(Button)findViewById(R.id.pickdate);
        currentdate=(TextView)findViewById(R.id.currentdate);
        fineET=(EditText)findViewById(R.id.fine);
        totalFineET=(TextView) findViewById(R.id.totalfine);
        duedate=(TextView)findViewById(R.id.duedate);
        daysPastDue=(TextView)findViewById(R.id.dayspastdue);
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        String date=day+"/"+(month+1)+"/"+year;
        currentdate.setText(date);
        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker=new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month+1) + "/" + year;

        duedate.setText(date);
        Date today=new Date();
        Calendar c=Calendar.getInstance();
        c.set(year,month,dayOfMonth);
        Date duedate=c.getTime();
        long daysbetween=daysBetween(today,duedate);//Calculating the days between the two dates
        String daysbetweenS=String.valueOf(daysbetween);
        daysPastDue.setText(daysbetweenS);
        totalFineET.setText("");
        String fineS=fineET.getText().toString();
        if(fineS.equals("")){
            Toast.makeText(this, "Please Enter Fine Amount and Try Again", Toast.LENGTH_SHORT).show();
        }
        else {
            fine = Long.parseLong(fineS);
            totalFine = (daysbetween * fine);
            totalfine = String.valueOf(totalFine);
            totalFineET.setText(totalfine);
        }
    }

    private long daysBetween(Date today, Date duedate) {
        if (duedate.getTime() > today.getTime()) {
            Toast.makeText(this, "Please enter a valid due date", Toast.LENGTH_SHORT).show();
        } else {
            long difference = (today.getTime() - duedate.getTime()) / 86400000;//86400000 is the number of milliseconds
            return Math.abs(difference);
        }
        return 0;
    }
}
