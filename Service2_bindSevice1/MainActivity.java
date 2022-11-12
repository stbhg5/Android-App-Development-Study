package com.example.service2_bindsevice1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*
    연결타입 서비스
            ; 컴포넌트들로 부터 서비스 요청을 받고 그 결과를 보낼수 있다
              그런 의미에서  클라이언트와 서버모델과 유사하며 서비스가 서버모델역할을 하는 것이다.

       (구현방법)
        1) MyService.java

               ① Binder를 상속받은  LocalService 내부 클래스를  구현해서  MyService의 객체를 담아 놓는다.
               ② onBind메소드에서    IBider타입의 객체를 만들어서  return해 놓는다.
                         public IBinder onBind(Intent intent) {
                                // LocalServce객체를  IBinder타입으로 리턴해 놓는다.
                        return  localBinder;
                ③ 서비스를 정의해 놓은  메소드를 구현해 놓는다.

         2)  MainActivity.java
                ① ServiceConnection인터페이스객체를 생성한다.  이때  두개의 추상메소드를 구현한다.
                    - onServiceConnected(ComponentName componentName, IBinder iBinder)
                           // 여기에서  매개변수로 받은  iBinder속에서  MyService객체를  가져온다.
                                >>  getService()를 호출해서   서비스를 연결한다.
                                >> 이 객체에서  서비스메소드를 호출해서  아래 이벤트로 처리하려고 하는 것이다.
                                >> 연결된것에 대해  flag 값을  둬서   true로  설정해 둔다.
                    - onServiceDisconnected(ComponentName componentName)
                               >> 연결해제 하면   flag값을  둬서  false로 설정해 둔다.

          3)  서비스를 연결한다.
                @Override
                protected void onStart() {
                    Intent intent = new Intent(this,MyService.class);
                    bindService(intent,  mCon, Context.BIND_AUTO_CREATE);  // 인텐트, 서비스객체, 바인드자동생성모드
                }
         4)  서비스 연결해제
                @Override
                protected void onStop() {
                    if(mBound){
                        unbindService(mCon);   // 서비스
                        mBound=false;
                    }
                }
         5)  서비스처리
*/

public class MainActivity extends AppCompatActivity {

    MyService mService;
    boolean mBound = false; //서비스와 연결여부 체크용 변수 : 연결되면 true, 연결안되면 false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //1. ★ ServiceConnetcion객체생성 - 2개의 추상메소드 구현
    //1) onServiceConnected(컴포넌트, IBinder)로 IBinder가 MyService onBind()의 return된 IBinder로 받아와진다.
    //2) onServiceDisconnected(컴포넌트)
    ServiceConnection mCon = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {//iBinder라는 틀의 객체를 가져오는데.. 이것이 MyService객체가 들어있는 것이다.
            //3. 바인더 객체의 메서드  호출 : 서비스 객체얻기 --- MyService에서 만들어 놓은 LocalBinder클래스객체와 그속의 getService()메소드
            mService = ((MyService.LocalBinder)iBinder).getService(); //MyService클래스속의 내부클래스 LocalBinder타입의  iBinder(매개변수로 받은것)속의  getService()호출 >> MyService객체 가져온다
            mBound = true; //서비스와 연결되면 true
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false; //서비스와 연결안되면 false
        }

    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyService.class);
        //2.서비스와 연결하기.
        bindService(intent, mCon, Context.BIND_AUTO_CREATE); //(인텐트, 서비스객체, 바인드자동생성모드)
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBound) {
            unbindService(mCon); //5. 서비스와 연결해제
            mBound=false;
        }
    }

    //이벤트 메소드
    public void mOnClick(View view) {
        switch(view.getId()) {
            case R.id.btnCalc :
                if(mBound) {//해야할 서비스가 있다면
                    EditText editNum1 = (EditText)findViewById(R.id.editNum1);
                    EditText editNum2 = (EditText)findViewById(R.id.editNum2);
                    if(editNum1.length() > 0 && editNum2.length() > 0) {
                        int num1 = Integer.parseInt(editNum1.getText().toString());
                        int num2 = Integer.parseInt(editNum2.getText().toString());
                        //4. 서비스 객체를 통해 서비스 메서드 호출
                        int result = mService.CalcNum(num1, num2);
                        Toast.makeText(this,"계산결과 = "+result,Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

}

//실행 :  두개의 숫자를 입력하고  두 수의 곱계산 버튼을 클릭하면 서비스와 통신하여 계산하고 그 결과를 토스트 메시지로 표시한다.