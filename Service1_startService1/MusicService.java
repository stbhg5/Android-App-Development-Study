package com.example.service1_startservice1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

//public 클래스이어야 한다.
public class MusicService extends Service {

    MediaPlayer player;

    //Service가 가지고 있는 추상메소드 - 오버라이딩 꼭 해둬야 한다.
    //클라이언트가 데이터를 보내고 서비스 결과를 받아야 하는 연결서비스에서 구현한다.
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //아래 필요한 Life Cycle 메소드들을 꺼내서 오버라이딩해서 구현한다.
    @Override
    public void onCreate() {//준비
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.old_pop);
        player.setLooping(false); //무한반복 - true
    }

    //서비스 시작 - 서비스.start()
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();  //시작
        Toast.makeText(getApplicationContext(), "서비스가 시작되었습니다.", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    //서비스 종료 - 서비스.stop()
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop(); // 종료
        Toast.makeText(getApplicationContext(), "서비스가 종료되었습니다.", Toast.LENGTH_SHORT).show();
    }

    //이제  AndroidManifest.xml의 <application> 안에 <service>를 등록한다.
    //물론 다른 액티비티에서 이 액티비를 실행하겠다고 한다면 인텐트필터를 등록해둘 수 있다.

}