package com.example.exercise6_3_countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //1. 참조 변수 선언
    TextView textView;
    Button startBtn, stopBtn, pauseBtn;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. 참조변수에 위젯 연결
        textView = findViewById(R.id.textView);
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        pauseBtn = findViewById(R.id.pauseBtn);

        countDownTimer(30*1000,1000);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.start();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pauseBtn.getText().equals("PAUSE")){
                    pauseBtn.setText("RESTART");
                }else{
                    pauseBtn.setText("PAUSE");
                }
            }
        });

    }

    private void countDownTimer(long time, final long countDownInterval){
        countDownTimer = new CountDownTimer(time, countDownInterval) {
            @Override
            public void onTick(long l) {
                textView.setText(String.valueOf(l/countDownInterval));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "CountDown Finished", Toast.LENGTH_SHORT).show();
            }
        };

    }
}
