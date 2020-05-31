package com.app.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.List;

import javax.security.auth.Subject;

public class DayDetail extends AppCompatActivity {

    private ListView listView;
    private TextView textView;
    //For the subjects of a single day
    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Saturday;
    //For the timings of a single day
    public static String[] time1;
    public static String[] time2;
    public static String[] time3;
    public static String[] time4;
    public static String[] time5;
    public static String[] time6;
    private String[] preferredDay;
    private String[] preferredTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);
        listView=(ListView) findViewById(R.id.lvDay);
        textView=(TextView) findViewById(R.id.ttDay);
        textView.setText(Timetable.sharedPreferences.getString(Timetable.SEL_DAY,null));
        setUpListView();

    }

    private void setUpListView() {
        //Store the subjects in the day's array
        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday=getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);
        Saturday = getResources().getStringArray(R.array.Saturday);
        //Store the time in the day's array
        time1 = getResources().getStringArray(R.array.time1);
        time2 = getResources().getStringArray(R.array.time2);
        time3 = getResources().getStringArray(R.array.time3);
        time4 = getResources().getStringArray(R.array.time4);
        time5 = getResources().getStringArray(R.array.time5);
        time6 = getResources().getStringArray(R.array.time6);

        String selectedDay = Timetable.sharedPreferences.getString(Timetable.SEL_DAY, null);

        if (selectedDay.equalsIgnoreCase("Monday")) {
            preferredDay = Monday;
            preferredTime = time1;
        } else if (selectedDay.equalsIgnoreCase("Tuesday")) {
            preferredDay = Tuesday;
            preferredTime = time2;
        } else if (selectedDay.equalsIgnoreCase("Wednesday")) {
            preferredDay = Wednesday;
            preferredTime = time3;
        } else if (selectedDay.equalsIgnoreCase("Thursday")) {
            preferredDay = Thursday;
            preferredTime = time4;
        } else if (selectedDay.equalsIgnoreCase("Friday")) {
            preferredDay = Friday;
            preferredTime = time5;
        } else {
            preferredDay = Saturday;
            preferredTime = time6;
        }

        SimpleAdapter simpleAdapter=new SimpleAdapter(this,preferredDay,preferredTime);
        listView.setAdapter(simpleAdapter);

    }

    public class SimpleAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater layoutInflater;
        private TextView subject,time;
        private String[] subjectArray;
        private String[] timeArray;

        public SimpleAdapter(Context context, String[] subjectArray, String[] timeArray) {
            this.context = context;
            this.subjectArray = subjectArray;
            this.timeArray = timeArray;
            layoutInflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=layoutInflater.inflate(R.layout.day_detail_single_item,null);
            }

            subject=(TextView)convertView.findViewById(R.id.dayDetail);
            time=(TextView)convertView.findViewById(R.id.tvTimeDay);
            subject.setText(subjectArray[position]);
            time.setText(timeArray[position]);

            return convertView;
        }
    }




    }

