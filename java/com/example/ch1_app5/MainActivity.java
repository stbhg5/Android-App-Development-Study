package com.example.ch1_app5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯 객체 가져오기
        EditText et = (EditText)findViewById(R.id.et);
        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        RadioButton radio1 = (RadioButton)findViewById(R.id.radio1);
        RadioButton radio2 = (RadioButton)findViewById(R.id.radio2);
        ImageView img = (ImageView)findViewById(R.id.img);

        //위젯 이벤트 처리
        btn1.setOnClickListener(new View.OnClickListener() {//글자 나타내기 버튼
            @Override
            public void onClick(View view) {
                String text = et.getText().toString(); //et 객체의 text String으로 가져옴
                if(text.length() != 0 && text != null) {
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "입력한 문자가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {//홈페이지 열기 버튼
            @Override
            public void onClick(View view) {
                String uri = et.getText().toString();
                try {//예외상황 잡기 위함
                    Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(website);
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "오류발생", Toast.LENGTH_SHORT).show();
                }
            }
        });
        radio1.setOnClickListener(new View.OnClickListener() {//11.0(R) radio 클릭
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.icon);
            }
        });
        radio2.setOnClickListener(new View.OnClickListener() {//12.0(S) radio 클릭
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.strawberry);
            }
        });
    }
}