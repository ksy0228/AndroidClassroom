package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextNum1, editTextNum2;
    Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    TextView textViewResult;
    Integer result;
    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        textViewResult = findViewById(R.id.textViewResult);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextNum1.getText().toString().equals("") || editTextNum2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    num1 = Integer.parseInt(editTextNum1.getText().toString());
                    num2 = Integer.parseInt(editTextNum2.getText().toString());
                    switch (v.getId()) {
                        case R.id.btnAdd:
                            result = num1 + num2;
                            textViewResult.setText("계산 결과 : " + Integer.toString(result));
                            break;

                        case R.id.btnSubtract:
                            result = num1 - num2;
                            textViewResult.setText("계산 결과 : " + Integer.toString(result));
                            break;
                        case R.id.btnMultiply:
                            result = num1 * num2;
                            textViewResult.setText("계산 결과 : " + Integer.toString(result));
                            break;
                        case R.id.btnDivide:
                            result = num1 / num2;
                            textViewResult.setText("계산 결과 : " + Integer.toString(result));
                            break;
                    }
                }
            }
        };
        btnAdd.setOnClickListener(listener);
        btnSubtract.setOnClickListener(listener);
        btnMultiply.setOnClickListener(listener);
        btnDivide.setOnClickListener(listener);
    }
}

