package com.example.exam_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText editNum1, editNum2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textView;
    int num1, num2, result;
    double divResult, dNum1, dNum2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2102 김소연");

        editNum1 = findViewById(R.id.editNum1);
        editNum2 = findViewById(R.id.editNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        textView = findViewById(R.id.textView);

        View.OnClickListener listener = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editNum1.getText().toString().equals("") || editNum2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자 적어라ㅏ", Toast.LENGTH_LONG).show();
                } else {
                    num1 = Integer.parseInt(editNum1.getText().toString());
                    num2 = Integer.parseInt(editNum2.getText().toString());

                    switch (v.getId()) {
                        case R.id.btnAdd:
                            result = num1 + num2;
                            textView.setText("계산 결과 : " + result);
                            break;
                        case R.id.btnSub:
                            result = num1 - num2;
                            textView.setText("계산 결과 : " + result);
                            break;
                        case R.id.btnMul:
                            result = num1 * num2;
                            textView.setText("계산 결과 : " + result);
                            break;
                        case R.id.btnDiv:
                            dNum1 = Double.parseDouble(editNum1.getText().toString());
                            dNum2 = Double.parseDouble(editNum2.getText().toString());
                            divResult = dNum1 / dNum2;
                            textView.setText("계산 결과 : " + divResult);
                            break;
                    }
                }
            }
        });

        btnAdd.setOnClickListener(listener);
        btnSub.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
    }
}
