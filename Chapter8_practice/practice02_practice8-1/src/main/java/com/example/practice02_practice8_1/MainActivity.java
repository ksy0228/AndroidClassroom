package com.example.practice02_practice8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    EditText editText;
    Button btnWriter;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        btnWriter = findViewById(R.id.btnWriter);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                fileName = String.format("%d_%d_%d.txt", year, monthOfYear, dayOfMonth);    // 연_월_일.txt
                String str = readDiary(fileName);               // 선택된 날짜 이름을 가진 파읠의 내용을 읽어와서 저장
                editText.setText(str);                          // 읽어온 텍스트를 editText 에 표시
                btnWriter.setEnabled(true);                     // 버튼 활성화


            }
        });

        btnWriter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = editText.getText().toString();
                    outputStream.write(str.getBytes());
                    outputStream.close();
                    Toast.makeText(MainActivity.this, fileName+"이 저장됨", Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

    }
    private String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream inputStream;
        try{
            inputStream = openFileInput(fileName);
            byte[] txt = new byte[500];
            inputStream.read(txt);
            inputStream.close();
            diaryStr = (new String(txt)).trim();
            btnWriter.setText("수정하기");
        }catch (IOException e){
            editText.setHint("일기 없음");
            btnWriter.setText("새로 저장");
        }
        return diaryStr;
    }
}
