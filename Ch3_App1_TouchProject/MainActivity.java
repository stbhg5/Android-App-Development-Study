package com.example.ch3_app1_touchproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //MyView 클래스 객체 생성하며 초기화
        MyView w = new MyView(this, 120, 500);
        //실행
        setContentView(w);
    }

}

/**
 * 커스텀 뷰생성 클래스
 */
class MyView extends View {

    //전역변수
    int x, y;
    Context context;

    /**
     * 전역변수 초기화 생성자
     * @param context
     * @param x
     * @param y
     */
    MyView(Context context, int x, int y) {
        super(context); //View 클래스에게
        this.context = context;
        this.x = x;
        this.y = y;
        setBackgroundColor(Color.rgb(245,245,220));
    }

    //터치 이벤트 추가 
    int r,g,b;
    /**
     * 터치 이벤트 추가 메서드
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int)event.getX();
        y = (int)event.getY();
        //랜덤함수로 색 만들기
        r = (int)(Math.random() * 255) + 1;
        g = (int)(Math.random() * 255) + 1;
        b = (int)(Math.random() * 255) + 1;
        invalidate(); //손가락으로 터치한 곳의 포인트 다시 그려준다.
        return super.onTouchEvent(event);
    }

    /**
     * 그래픽
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        //원 그리기1
        paint1.setTextSize(30);
        paint1.setColor(Color.rgb(r, g, b));
        canvas.drawCircle(x, y, 50, paint1);
        //원 그리기2
        paint2.setColor(Color.rgb(r - 50, g - 50, b - 50));
        canvas.drawCircle(x, y, 40, paint2);
        //글자 및 쓰여지는 위치 지정
        canvas.drawText("(" + x + "," + y + ")에서 터치 이벤트가 발생되었습니다.", x, y + 100, paint1);
    }
}