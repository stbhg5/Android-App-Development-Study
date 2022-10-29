package com.example.graphic1_customview_basic1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

//View 클래스 상속받아 구현
class MyView extends View {

    int x = 300, y = 100;
    int r, g, b;

    /**
     * 생성자
     * @param context
     * @param a
     * @param y
     */
    public MyView(Context context, int a, int y) {
        super(context);
        this.y += b;
        //색상 추가
        setBackgroundColor(Color.argb(20,50,80,180));
    }

    /**
     * 그리기 - 그래픽
     * canvas : 그림판 - 도형, 이미지, 글자
     * paint : 그리기 도구
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //그리기 도구 - Paint 객체생성
        Paint paint = new Paint();

        //랜덤색상 출력
        r  = (int)(Math.random() * 256);
        g = (int)(Math.random() * 256);
        b = (int)(Math.random() * 256);

        //paint 설정
        /*
        - Paint 객체 메서드
          setColor() : 색
          setStrokeWidth() : 선 두께
          setTextSize() : 글자크기
          setAntiAlias(true/false) : 경계선을 부드럽게 처리
          setStyle(Paint.Style.FILL/STROKE/FILL_AND_STROKE) 등
         */
        paint.setColor(Color.rgb(r, g, b));
        paint.setStyle(Paint.Style.STROKE); //Paint.Style.FILL은 채우기
        paint.setStrokeWidth(7); //선의 굵기

        //도형그리기
        /*
        - 점 그리기 : drawPoint(x, y, paint);
        - 직선 그리기 : drawLine(x1, y1, x2, y2, paint);
        - 원 그리기 : drawCircle(중심x, 중심y, 반지름, paint);
        - 원호 그리기 : drawAct(new RectF(x1, y1, x2, y2)내접원, 시작각, 회전각, 중심점사용여부(false), paint);
        - 사각형 그리기 : drawRect(좌상x, 좌상y, 우하x, 우하y, paint);
        - 둥근사각형 그리기 : drawRoundRect(Rect rect, 모서리사각형너비(width), 모서리사각형높이(height), paint);
        - 글자 그리기 : drawText(글자문자열, x, y, paint);
        - 기하학적인 도형 : drawPath(Path path, paint);
          path.moveTo(x, y) : 이동
          path.lineTo(x, y) : 연결해가는 그리기
         */
        canvas.drawCircle(x - 100 , y + 70,80, paint); //원
        canvas.drawArc(new RectF(700,400, 1000,700), 45, 320, true, paint); //원호
   
        //글자쓰기
        paint.setTextSize(50);
        Typeface t = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC); //폰트 타입객체 생성
        paint.setTypeface(t); //폰트 타입설정
        canvas.drawText("안드로이드",500,200, paint);

        //경로그리기
        Path path = new Path();
        path.moveTo(500,500);
        path.lineTo(700,700);
        path.lineTo(500,700);
        path.lineTo(500,500);
        canvas.drawPath(path, paint);
        canvas.drawRect(x, y,x+150,y+150, paint);

        //화면을 다시 그리기
        invalidate();

        //스레드를 통해 인터벌 주기
        try {
            Thread.sleep(500);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 터치이벤트 추가
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바닥에 닫으면 맨 위로 좌표 이동
        if(this.getHeight() > y) {
            y = y + 100; //100씩 이동
        }else {
            y = 0;
        }
        return false;
    }

}

public class MainActivity extends AppCompatActivity {

    int x = 0, y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //사용자 뷰 객체 적용
        MyView w = new MyView(this,50,50);
        setContentView(w);
    }

}