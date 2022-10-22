package com.example.ch3_app2_viewdrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //전역 변수 초기화
    int x = 200, y = 300;
    String str;

    /**
     * 내부 클래스
     */
    protected class MyView extends View {

        /**
         * 생성자 초기화
         * @param context
         */
        MyView(Context context) {
            super(context);
            setBackgroundColor(Color.YELLOW);
        }

        /**
         * 그래픽
         * @param canvas
         */
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawCircle(x, y, 100, paint);
            paint.setTextSize(50);
            canvas.drawText("액션종류: " + str, 10, 50, paint);
        }

        /**
         * 터치이벤트 리스너 추가
         * @param event
         * @return true
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x = (int)event.getX();
            y = (int)event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                str = "Action Down";
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE) {
                str = "Action Move";
            }
            if(event.getAction() == MotionEvent.ACTION_UP) {
                str = "Action Up";
            }
            invalidate();
            return true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        MyView w = new MyView(this);
        setContentView(w);
    }
    
}