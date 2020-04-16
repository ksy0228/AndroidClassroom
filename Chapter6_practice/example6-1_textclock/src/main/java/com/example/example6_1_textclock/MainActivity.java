package com.example.example6_1_textclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //1. 참조 변수 생성
    Button setBtn;
    TextView result;
    TextClock time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. 참조 변수에 객체 생성
        setBtn = findViewById(R.id.setBtn);
        result = findViewById(R.id.result);
        time = findViewById(R.id.time);

        //3. 버튼 클릭 이벤트 처리
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Time : " + time.getText());
            }
        });
    }
}
