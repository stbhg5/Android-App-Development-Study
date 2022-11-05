package com.example.activity1_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("초기화","onCreat()호출");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.d("시작","onStart()호출");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("출력","onReSum()호출");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("중지","onPause()호출");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.d("재실행","onReStart()호출");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d("실행종료","onStop()호출");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("끄기","onDestroy()호출");
        super.onDestroy();
    }

}