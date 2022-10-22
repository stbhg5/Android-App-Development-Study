package com.example.ch3_app3_draw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SingleTouchView(this,null));
    }

}

//클래스 생성
class SingleTouchView extends View {

    Paint paint = new Paint();
    Path path = new Path();

    SingleTouchView(Context context, AttributeSet attrs) {
        super(context,attrs);
        paint.setAntiAlias(true);   //인접한 스팩트럼이 겹쳐서 출력이 왜곡되는 현상을 계단 현상(에일리어싱, Aliasing),계단형식을 매끄럽게 처리
        paint.setStrokeWidth(10f);  //선두께
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE); //채우기 없이 선만 그림, fill채우기
        paint.setStrokeJoin(Paint.Join.ROUND); //선의 끝 모양
        // MITER : 모서리를 각진 모양으로 만듭니다.
        // BEVEL : 모서리가 둥글게 살짝 깍인 모양으로 만듭니다.
        // ROUND : 모서리를 둥근 모양으로 만듭니다.
    }

    /**
     * 그래픽
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    /**
     * 이벤트 리스너
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //마우스가 터치될 위치
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX,eventY); //터치가 눌러지면 path(경로)에 위치를 저장한다
                return true;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX,eventY); //누르는 도중에 움직이면 경로에 직선그리기를 저장한다
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                return false;
        }
        invalidate(); //새로 그려준다
        return true;
/*
  onTouchEvent()의 반환값
       이벤트에서
          . 반환값  true : 이벤트 처리를 완벽하게 했다는의미
                    false: 다른 메소드가 동일한 이벤트를 다시 처리하는 것을 허용하게 new 된다

        onTouchEvent()에서는 약간 미묘한 문제있다.
          . 반환값 false : 안드로이드는 현재의 이벤트가 완전히 처리되지 않는 것으로 판단하고
                           현재의 이벤트에 이어지는 ACTION_MOVE, ACTION_UP 이벤트는 없애버린다. (ACTION_DOWN은 미삭제함)
                           터치 이벤트의 특징상 상당히 많이 발생되므로 불가피하게 이렇게 처리한다.
                           따라서 터치이벤트에서  3가지 이벤트(MOVE,UP,DOWN)을 하고 싶다면 반드시 return true해야 한다.

*/
        // 과제:  r,g,b 색상선택을 구현해 오시오.  지우기도 추가할 것
    }
}