package com.example.ch3_app6_viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    //전역 변수
    Button btnStart, btnStop;
    ViewFlipper vf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //xml에서 가져온 객체
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        vf = (ViewFlipper)findViewById(R.id.viewFliper1);
        //리스너 부착
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //vf.showNext(); //다음 화면 직접 넘김(수동)
                vf.startFlipping(); //화면 전환 시작
                vf.setFlipInterval(1000); //전환 간격
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vf.stopFlipping(); //화면 멈춤
            }
        });
    }

}