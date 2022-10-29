package com.example.image3_surfaceview1;

import android.os.Bundle;
/*
     서피스 뷰(SurfaceView)를 활용한 그리기 (애니메이션)
     . 기존 드로어블뷰의 문제점
     복잡하지 않은 그래픽은 캔버스로 그리면 된다. 하지만 복잡해지면... 안드로이드의 메인스레드의 시간을 너무 빼앗아서
     느려지거나, 멈춤현상처럼 느껴지고, ANR(Application Not Responding) 오류가 발생할 수도 있다.
     . SurfaceView를 활용해서 해결
     별도의 스레드로 그림을 그려서 안드로이드 시스템이 그화면을 복사하게 지원하는 방식

     . 구현방법
     1) activity화면으로 SurfaceView를 설정하고
     2) 새로운 Thread에서 SurfaceView에 그림을 그리면 된다.
     3) ★ 이때....주의할 점은 안드로이드가 Surface를 생성하기 전에 그림을 그리면 안된다.
     그래서 Surface가 생성되고 소멸되는 시점을 그림을 담당하는 스레드에게 알려줘야 한다.
     그러한 목적으로 SurfaceHolder.Callback을 구현한다.
     4) 서피스 뷰 객체는 우리가 직접처리할 수 없고 반드시 SurfaceHolder를 통해서 처리해야 한다.
     -  왜냐하면 안드로이드 시스템이 서피스뷰를 실제장치의 뷰화면으로 부지런히 복사해서 구현하기 때문이다.
     >> SurfaceHolder객체 생성
     SurfaceHolder holder = getHolder();
     >> SurfaceHolder의 콜백메소드 받기
     holder.addCallback(this);

     5) ★어떤 순서로 그림을 그리는지 보자! ★
     ① 그림을 그리기전에 lockCanvas()를 호출해서 서피스뷰의 캔버스를 독점적으로 확보해놓는다.
     ② 캔버스에 그림을 그린다.
     ③ 다 그리고 나면 unlockCanvasAndPost()를 호출해서 사용자가 그려놓은 그대로 서피스뷰를 실제장치로 복사한다.
*/

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MySurfaceView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new MySurfaceView(this);
        setContentView(view);
    }

}