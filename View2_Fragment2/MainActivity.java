package com.example.view2_fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Sub sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sub = new Sub();
    }

    public void setFrag(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.btn1:
                //Intent : 현재 액티비티 정보 가지고 다음 페이지로 정보 넘겨줌
                Intent intent = new Intent(getApplicationContext(), Sub.class); //Intent(this, 이동할 페이지(Java))
                ft.replace(R.id.container, new Fragment1(), "myfrag1");
                ft.commit();
                break;
            case R.id.btn2:
                ft.replace(R.id.container, new Fragment2(), "myfrag2");
                ft.commitAllowingStateLoss();
                break;
        }
    }

    //서브창가기 인텐트
    public void moveToSub(View v) {
        Intent intent = new Intent(this, Sub.class);
        startActivity(intent);
    }

}