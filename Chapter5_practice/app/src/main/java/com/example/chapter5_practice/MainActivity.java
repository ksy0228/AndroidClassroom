package com.example.chapter5_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //1. view 참조 변수 생성
    Button btn_visible, btn_invisible;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. View 참조변수에 View 인스턴스 연결
        btn_visible = findViewById(R.id.btn_visible);
        btn_invisible = findViewById(R.id.btn_invisible);
        image = findViewById(R.id.imageView);

        //3. 버튼 클릭 이벤트 처리 1
        /*btn_visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setVisibility(View.VISIBLE);
            }
        });

        btn_invisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setVisibility(View.INVISIBLE);
            }
        });*/

        //4. 버튼 클릭 이벤트 처리 2
        /*View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_visible:
                        image.setVisibility(View.VISIBLE);
                        break;

                    case R.id.btn_invisible:
                        image.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        };
        btn_visible.setOnClickListener(listener);
        btn_invisible.setOnClickListener(listener);*/

        //5. 버튼 클릭 이벤트 처리3
        /*btn_visible.setOnClickListener((View.OnClickListener) this);
        btn_invisible.setOnClickListener((View.OnClickListener) this);*/
    }

    /*@Override
    public void onClick(View view){
        if(btn_visible.equals(view)){
            image.setVisibility(View.VISIBLE);
        } else if(btn_invisible.equals(view)){
            image.setVisibility(View.INVISIBLE);
        }
    }*/
}
