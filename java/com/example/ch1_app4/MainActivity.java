package com.example.ch1_app4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯 가져오기
        Button nate = (Button)findViewById(R.id.nate);
        Button tel = (Button)findViewById(R.id.tel);
        Button pic = (Button)findViewById(R.id.pic);
        Button end = (Button)findViewById(R.id.end);

        //위젯에 이벤트 설정
        nate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//홈페이지 열기
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com"));
                startActivity(mIntent);
            }
        });
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//전화 걸기
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/911"));
                startActivity(mIntent);
            }
        });
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//갤러리 열기
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(mIntent);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//애플리케이션 종료
                finish();
            }
        });
    }

}