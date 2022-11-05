package com.example.activity2_explicitintent_basic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioButton secondChk,threeChk;
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //체크박스
        secondChk = (RadioButton)findViewById(R.id.secondChk);
        threeChk  = (RadioButton)findViewById(R.id.thirdChk);
        //버튼
        mBtn      = (Button)findViewById(R.id.mBtn);
    }

    public void mOnClick(View v) {
        Intent intent = null;
        if(secondChk.isChecked() == true) {
            //1. 인텐트 객체 생성 - 이동할 Activity.class
            intent = new Intent(getApplicationContext(), SecondActivity.class);
            //2. 인텐트에 데이터 설정 putExtra(String key, value) - getIntExtra(key, 디폴트값), getStringExtra(key)
            intent.putExtra("name","홍길동");
            intent.putExtra("age", 30);
            //intent.setAction();
        }else if(threeChk.isChecked() == true) {
            intent = new Intent(getApplicationContext(), ThirdActivity.class);
        }
        //3. 이동처리
        startActivity(intent);
    }

}