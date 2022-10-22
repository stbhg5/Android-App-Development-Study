package com.example.ch2_app8_framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //전역 변수 선언
    ImageView imageView1, imageView2, imageView3, imageView4;
    int imgeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 가져온 객체
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
    }

    /**
     * onClick 이벤트로 호출되는 메서드
     */
    public void onBtnClick(View v) {
        changeImge();
    }

    /**
     * 실제 기능구현 메서드
     */
    public void changeImge() {
        if(imgeIndex == 0) {
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            imageView4.setVisibility(View.INVISIBLE);
            imgeIndex = 1;
        }else if(imgeIndex == 1) {
            imageView1.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            imageView4.setVisibility(View.INVISIBLE);
            imgeIndex = 2;
        }else if(imgeIndex == 2) {
            imageView1.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.VISIBLE);
            imageView4.setVisibility(View.INVISIBLE);
            imgeIndex = 3;
        }else {
            imageView1.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            imageView4.setVisibility(View.VISIBLE);
            imgeIndex = 0;
        }
    }

}