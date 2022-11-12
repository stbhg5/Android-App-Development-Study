package com.example.service2_bindsevice1;

/*
    바인더 클래스를 만들어서  onBind()에서   객체생성후 반환한다.
*/

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

// 1. [필수] Service추상클래스를 상속받는다.
public class MyService extends Service {

    public MyService() {}

    @Override
    public IBinder onBind(Intent intent) {
        //3. [필수] 아래 내부클래스로 구현해놓은 LocalBinder의 객체를 생성해서 IBinder타입으로 반환한다.
        //- LocalBinder속에는 MyService객체를 담고 있다.
        LocalBinder localBinder = new LocalBinder();
        return localBinder;
    }

    //2. [필수] 바인더 클래스 정의: 현재 서비스 객체 리턴 *************************************
    public class LocalBinder extends Binder {//Binder상속 - IBider타입에 MyService를 묶기위해 상속받아 구현한다.

        //현재 서비스 객체 리턴
        public MyService getService() {
            return MyService.this; //MyService 즉 이 클래스 객체를 호출한 곳으로 반환한다.
        }

    }
   // *******************************************************************************************

    //4. [필수] 서비스(두수를 받아서 곱해주는 서비스) 해야할 일을 정의한 메서드 추가
    public int CalcNum(int n, int m){
        return n*m;
    }

}