package com.example.practice6_1_datepicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer1;
    RadioButton daySet, timeSet;
    DatePicker datePicker;
    TimePicker timePicker;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        chronometer1 = findViewById(R.id.chronometer1);
        daySet = findViewById(R.id.daySet);
        timeSet = findViewById(R.id.timeSet);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        tv = findViewById(R.id.tv);

        daySet.setVisibility(View.INVISIBLE);
        timeSet.setVisibility(View.INVISIBLE);

        timePicker.setVisibility(View.INVISIBLE);
        datePicker.setVisibility(View.INVISIBLE);

        //region 날짜, 시간 예약
        daySet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });
        timeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });
        //endregion

        chronometer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer1.setBase(SystemClock.elapsedRealtime());
                chronometer1.start();
                chronometer1.setTextColor(Color.RED);

                daySet.setVisibility(View.VISIBLE);
                timeSet.setVisibility(View.VISIBLE);
            }
        });

        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                chronometer1.stop();

                tv.setText(Integer.toString(datePicker.getYear())+"년"+ Integer.toString(1+datePicker.getMonth())+"월"+ Integer.toString(datePicker.getDayOfMonth())+"일"
                +Integer.toString(timePicker.getCurrentHour())+"시"+Integer.toString(timePicker.getCurrentMinute())+"분 예약됨");

                daySet.setVisibility(View.INVISIBLE);
                timeSet.setVisibility(View.INVISIBLE);

                timePicker.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.INVISIBLE);

                return false;
            }
        });
    }
}
