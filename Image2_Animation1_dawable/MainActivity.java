package com.example.image2_animation1_dawable;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable mAnimation;
    EditText state ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.imgFrame);
        state = (EditText)findViewById(R.id.state);
        imageView.setBackgroundResource(R.drawable.myanimation);
        //애니메이션드로우어블 객체 초기화
        mAnimation = (AnimationDrawable)imageView.getBackground();
    }

    /**
     * 버튼 눌렀을 때 호출 메서드
     * @param v
     */
    public void mOnClick(View v) {
        switch(v.getId()) {
            case R.id.btnStart:
                mAnimation.start(); //start() : 애니메이션 시작
                state.setText("걷기시작합니다.");
                state.setTextColor(Color.BLUE);
                break;
            case R.id.btnStop:
                mAnimation.stop(); //stop() : 애니메이션 중지
                state.setText("정지했습니다.");
                state.setTextColor(Color.RED);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            mAnimation.start();
            state.setTextColor(Color.BLUE);
            return true;
        }else if(event.getAction() == MotionEvent.ACTION_UP) {
            mAnimation.stop();
            state.setTextColor(Color.RED);
            return true;
        }
        return super.onTouchEvent(event);
    }

}