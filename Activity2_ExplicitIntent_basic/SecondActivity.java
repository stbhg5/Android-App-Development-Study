package com.example.activity2_explicitintent_basic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button sBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        sBtn = (Button)findViewById(R.id.sBtn);
        //1. 인텐트를 받는다.
        Intent intent = getIntent();
        //2. 데이터를 꺼내온다 - get데이터타입Extra(key)
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age",0);
        Toast.makeText(getApplicationContext(), "이름: " + name + " 나이: " + Integer.toString(age), Toast.LENGTH_LONG).show();
    }

    //3. 돌아가기 - finish(): 현재 페이지를 스택에서 없애기 - 현재 액티비티 종료시킨다.
    public void sOnClick(View v){
        finish();
    }

}
