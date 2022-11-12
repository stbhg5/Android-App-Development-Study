package com.example.service1_startservice1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
    서비스(Service)
        . 사용자 인터페이스 없이(화면없이) 백그라운드에서 실행되는 동작을 구현한다.

        . 특징
           - 사용자 인터페이스를 가지지 않는다.
           - 일반적으로 애플리케이션에 의해 시작된다.
           - 한번 시작된 서비스는 사용자가 다른 애플리케이션으로 이동하더라도 계속 백그라운드에서 실행된다.

            . 사용자 인터페이스 없이 백그라운드에서 실행되게 하는 메커니즘이다.
            . 애플리케이션에 의해 시작되고, 한번 시작된 서비스는 애플리케이션이 이동되더라도 계속 백그라운드에서 실행된다.
            . 활용용도
                  -  배경 음악재생, 파일 입출력, 네트워크 트랜젝션, 콘텐트 제공자와  통신 등에 활용된다.
            . 종류
                1)  시작타입 서비스  :  서비스가 실행되면  서비스 시작을 담당했던  컴포넌트가 소멸되더라도  서비스는 백그라운드에서 실행된다.
                                       중지하는 방법은....  stopService()호출   또는   서비스 스스로   stopSelf() 사용 자체적으로 중지처리  두가지가 있다.

                                                    (MainActivity)                                              (Service)
                            -  startService( intent ) 호출   :  서비스 시작    --- > onStartComman()에서 서비스를 시작시킨다.
                            -  stopService( intent )  호출  :   서비스 종료    --- > 여기에 대응되는 콜백메소드가 없다.
                                                                                     onDestory()에서   실행하고 있는 서비스를 중지처리 하면된다.

                2)  연결타입 서비스: 클라이언트 - 서버 모델에서  서버의 역할을 하는 서비스이다.
                                                 컴포넌트들로 부터 요청을 받고  결과를 보낼수 있다.
                                                 애플리케이션의 컴포넌트들이  연결되어 있는 한, 해당 작업을 수행한다.

                        - bindService() 호출 : 서비스 시작
                        - unbindService()로 연결해체 : 서비스 종료

          .  구현방법
                    1) 사용자 인터페이스는 필요없다 -  xml, view 필요없다.
                    2) Service를 상속받은  클래스로 서비스를 구현한다. 이때 클래스는 public이어야 한다.
                        .  Service의 onBind() 추상메소드 구현
                        .  onCreate() 재정의 - 어떤 서비스를 할것인지 서비스 생성
                        .  onStartCommand() 재정의 - 서비스객체. start() - 서비스시작
                        .  onDestroy() 재정의 - 서비스객체.stop() - 서비스종료
                    2) MainActivity.java에서 startService(inten)로 서비스 매니페스트에 서비스 등록
                        .  startService( new Intent(this, 서비스클래스.class)
                        .  stopService( new Intent(this, 서비스클래스.class)
                    3) 매니페스트에 서비스 등록
                        <application>안에 <serviec> 등록
                -  다른 컴포넌트가 이 액티비티
*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener {//View.OnClickListener 구현

    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);

        //View.OnClickListen 인터페이스의 메소드
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    //View.OnClickListen 인터페이스의 메소드 추상메소드
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                startService(new Intent(this, MusicService.class)); //사용자 인터페이스 없이 넘어간다. manifest에 등록 안해도 된다.
                break;
            case R.id.stop:
                stopService(new  Intent(this, MusicService.class));
                break;
        }
    }

}