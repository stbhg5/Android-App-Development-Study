package com.example.ch1_app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //앱 실행
        setContentView(R.layout.activity_main); //activity_main_.xml
    }
}