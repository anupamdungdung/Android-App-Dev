package com.app.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Timetable extends AppCompatActivity {

    private ListView daysLv;
    private String[] week;

    public static SharedPreferences sharedPreferences;
    public static String SEL_DAY;

    Days monday=new Days(R.drawable.monday);
    Days tuesday=new Days(R.drawable.tuesday);
    Days wednesday=new Days(R.drawable.wednesday);
    Days thursday=new Days(R.drawable.thursday);
    Days friday=new Days(R.drawable.friday);
    Days saturday=new Days(R.drawable.saturday);

    Days[] daysArray=new Days[]{
            monday,tuesday,wednesday,thursday,friday,saturday
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        daysLv=(ListView)findViewById(R.id.lvWeek);
        setUpListView();

        sharedPreferences=getSharedPreferences("MY DAY",MODE_PRIVATE);
    }

    public void setUpListView(){
        week=getResources().getStringArray(R.array.Week);
        WeekAdapter adapter=new WeekAdapter(this,R.layout.activity_week_single_item,week);
        daysLv.setAdapter(adapter);
        daysLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0: {startActivity(new Intent(Timetable.this,DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Monday").apply();
                        break;
                    }
                    case 1: {startActivity(new Intent(Timetable.this,DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Tuesday").apply();
                        break;
                    }
                    case 2: {startActivity(new Intent(Timetable.this,DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply();
                        break;
                    }
                    case 3: {startActivity(new Intent(Timetable.this,DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Thursday").apply();
                        break;
                    }
                    case 4: {startActivity(new Intent(Timetable.this,DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Friday").apply();
                        break;
                    }
                    case 5: {startActivity(new Intent(Timetable.this,DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Saturday").apply();
                        break;
                    }
                    default:break;
                }

                
            }
        });

    }

    public class WeekAdapter extends ArrayAdapter{
        private int resource;
        private LayoutInflater layoutInflater;//It has implementation methods which will help you load another layout(Nested Layout)
        private String[] week=new String[]{};


        public WeekAdapter(Context context, int resource,String[] objects) {
            super(context, resource,objects);
            this.resource=resource;
            this.week=objects;
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder=new ViewHolder();//
                convertView=layoutInflater.inflate(resource,null);
                holder.ivLogo=(ImageView) convertView.findViewById(R.id.letterImage);
                holder.tvWeek=(TextView)convertView.findViewById(R.id.week);
                convertView.setTag(holder);//Memory Location to our ViewHolder Box
            }
            else{
                holder=(ViewHolder)convertView.getTag();//This optimizes the load time
            }
            holder.ivLogo.setImageResource(daysArray[position].getImage());
            holder.tvWeek.setText(week[position]);
            return convertView;

        }

        class ViewHolder{
            private ImageView ivLogo;
            private TextView tvWeek;

        }
    }
}
