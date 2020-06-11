package com.example.exam_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    RadioButton radioCall, radioCamera;
    Button btnBack;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = findViewById(R.id.textView);
        radioCall = findViewById(R.id.radioCall);
        radioCamera = findViewById(R.id.radioCamera);
        btnBack = findViewById(R.id.btnBack);

        Intent outIntent = getIntent();
        text= outIntent.getStringExtra("text");
        textView.setText(text);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioCall.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    int i =1;
                    intent.putExtra("radio", i);
                    setResult(RESULT_OK, intent);
                    finish();
                }else if(radioCamera.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    int i =2;
                    intent.putExtra("radio", i);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"선택해주라ㅏ",Toast.LENGTH_LONG);
                }
            }
        });
    }
}
