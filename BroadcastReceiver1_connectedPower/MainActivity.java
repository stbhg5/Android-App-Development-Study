package com.example.broadcastreceiver1_connectedpower;

import static android.content.Intent.ACTION_POWER_CONNECTED;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver chargerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1. BroadcastReceiver인터페이스 객체생성 (구현) - onReceive()
        chargerReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext(), "전원이 연결되었습니다.", Toast.LENGTH_SHORT).show();
            }
        };
        //2. registerReceiver(브로드캐스트 객체, 인텐트필터) 구현 - 이런 이벤트액션을 받겠다고 등록
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_POWER_CONNECTED);
        registerReceiver(chargerReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //3. unregisterReceiver(브로드캐스트 객체) - 등록된 수신허용이벤트 해제
        unregisterReceiver(chargerReceiver);
    }

}