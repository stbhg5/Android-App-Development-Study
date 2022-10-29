package com.example.graphic2_customview_basic2;

import static android.graphics.drawable.GradientDrawable.LINE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1, CYCLE = 2, RECT = 3;
    static int shape = LINE;
    static Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView myView = new MyView(this);
        setContentView(myView);
        setTitle("그림판");
    }

    /**
     * 커스텀 뷰 - 내부클래스
     */
    class MyView  extends View {

        int startX = -1, startY= -1, stopX = -1, stopY = -1 ;

        /**
         * 생성자
         * @param context
         */
        public MyView(Context context){
            super(context);
        }

        /**
         * 그래픽 그리기
         * @param canvas
         */
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);

            switch (shape){
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY,paint);
                    break;
                case CYCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX-startX,2)+Math.pow(stopY - startX, 2));
                    canvas.drawCircle(startX,startY,radius,paint);
                    break;
                case RECT:
                    canvas.drawRect(startX,startY,startX+ Math.abs((stopX-startX)), startY+Math.abs((stopY-startX)), paint);
            }
        }

        /**
         * 터치이벤트 추가
         * @param event
         * @return
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch(event.getAction()) {//액션값 읽어오기
                //눌렀을 때
                case MotionEvent.ACTION_DOWN:
                    startX = (int)event.getX();
                    startY = (int)event.getY();
                    break;
                //누르고 이동
                case MotionEvent.ACTION_MOVE:
                //뗐을 때
                case MotionEvent.ACTION_UP:
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                    //다시 그려준다.
                    this.invalidate();
                    break;
            }
            return true;
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------

    /*
    - 옵션메뉴를 작성한다.
    1) 메뉴를 구현 : onCreateOptionsMenu(Menu menu) 메서드 오버라이딩 구현
    2) 메뉴가 선택되었을 때 할 일 : onOptionsItemSelected(@NonNull MenuItem item) 오버라이딩 구현
     */

    /**
     * 메뉴 구현
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //add(그룹ID, itemID, orderID); -> getGroupId(), getItemId(), getOrderId()
        //메인 메뉴
        menu.add(0, 1, 0, "선 그리기");
        menu.add(0, 2,0,"원 그리기");
        menu.add(0, 3,0,"사각형그리기");
        //서브 메뉴 - Menu 객체 생성해서 추가한다.
        Menu sub = menu.addSubMenu("색상변경");
        sub.add(1,4,1,"빨강");
        sub.add(1,4,2,"초록");
        sub.add(1,4,3,"파랑");
        return true;
    }

    /**
     * 메뉴선택시 이벤트처리
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case 1:
                shape = LINE; //shape변수값을 LINE으로 변경
                return true;
            case 2:
                shape = CYCLE; //원
                return true;
            case 3:
                shape = RECT; //사각형
                return true;
            case 4:
                if(item.getOrder() == 1) {
                    paint.setColor(Color.RED);
                }else if(item.getOrder() == 2) {
                    paint.setColor(Color.GREEN);
                }else if(item.getOrder() == 3) {
                    paint.setColor(Color.BLUE);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}