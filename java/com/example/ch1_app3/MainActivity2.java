package com.example.ch1_app3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //헤더UI 그리기
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher2); //사진삽입
        
        //위젯 가져오기
        Button btn = (Button)findViewById(R.id.button); //타입으로 가져옴
        //TextView tv = (TextView)findViewById(R.id.) //이런식으로 가져옴
        btn.setOnClickListener(new View.OnClickListener() {//위젯에 이벤트 설정
            @Override
            public void onClick(View view) {//View는 Button의 부모객체
                //출력해서 보기위해 토스트 구현
                Toast.makeText(getApplicationContext(), "환영합니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

}