package com.example.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    EditText edit2;
    Button returnBtn;
    String receiveStr;
    String giveStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = findViewById(R.id.textView);
        edit2 = findViewById(R.id.edit2);
        returnBtn = findViewById(R.id.returnBtn);

        final Intent intent = getIntent();
        receiveStr = intent.getStringExtra("text");
        textView.setText(receiveStr);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveStr = edit2.getText().toString();

                intent.putExtra("text2", giveStr);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
