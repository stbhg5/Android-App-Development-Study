package com.example.ch1_app6;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯 객체 가져오기
        TextView tv = (TextView)findViewById(R.id.tv1);
        //TextView 객체를 자바를 통해 조작하기
        tv.setText("자바코드로 글자를 바꿉니다");
        tv.setTextSize(20);
        tv.setTextColor(Color.RED);

        //위젯 객체 가져오기
        Button btn1 = (Button)findViewById(R.id.btn1);
        //Button 객체를 자바를 통해 조작하기
        btn1.setVisibility(View.VISIBLE);
        btn1.setRotation(45);

        //위젯 객체 가져오기
        Button btn2 = (Button)findViewById(R.id.btn2);
        //Button 객체를 자바를 통해 조작하기
        btn2.setClickable(false);
        btn2.setTextColor(Color.BLACK);
        btn2.setBackgroundColor(Color.GREEN);
        btn2.setGravity(Gravity.RIGHT|Gravity.TOP);
        btn2.setPadding(5, 5, 5, 5);

        //위젯 객체 가져오기
        Button btn3 = (Button)findViewById(R.id.btn3);
        //Button 객체를 자바를 통해 조작하기
        btn3.setBackgroundColor(Color.RED);
        btn3.setText("수고하셨습니다");
        btn3.setScaleX(2);
    }

}