package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                textView.setText("메뉴1 선택");
                return true;

            case R.id.menu2:
                textView.setText("메뉴2 선택");
                return true;

            case R.id.menu3:
                textView.setText("메뉴3 선택");
                return true;

            case R.id.subMenu1:
                textView.setText("서브메뉴1 선택");
                return true;

            case R.id.subMenu2:
                textView.setText("서브메뉴2 선택");
                return true;
        }
        return false;
    }
}
