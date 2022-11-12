package com.example.broadcastreceiver2_sms;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10; //Request Code
    TextView sms;

    //1. 방송수신자 객체생성
    BroadcastReceiver receiver = new BroadcastReceiver() {

        //onReceive 구현 - 방송 수신시 해야할 코드
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                String smsSender = "홍길동님";
                String smsBody = " ";
                for(SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    smsBody += smsMessage.getMessageBody();
                }
                //Toast.makeText(getApplicationContext(), smsBody, Toast.LENGTH_SHORT).show();
                sms.setText(smsSender+":   \n "+smsBody);
            }
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sms = (TextView)findViewById(R.id.tv);
        //앱에 권한이 설정되어 있는지 체크 - 매니페스트에 해놨지만, 앱이 실행되기전에 다시 허락을 맡는것이 필요하다. 그래서 동적으로 권한부여한다.
        //1) 권한들을 배열로 설정
        String[] permission = new String[]{
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        //2) 배열 체크 - 승인 : int PERMINSSION_GRANTED, 미승인 : int PackageManager.PERMISSION_DENIED
        for(int i = 0; i < permission.length; i++) {
            //권한 체크 - (checkSelfPermission(MainActivity.this, String permission[i]);
            int permChk = ContextCompat.checkSelfPermission(MainActivity.this, permission[i]);
            Toast.makeText(getApplicationContext(), permission[i] + "권한승인이 안되어 있습니다. 승인처리합니다.", Toast.LENGTH_SHORT).show();
            //권한 요청 - requestPermissions(Context context, permission, RequestCode)
            if(permChk != PackageManager.PERMISSION_GRANTED) {//PackageManager.PERMISSION_DENIED
                ActivityCompat.requestPermissions(MainActivity.this, permission, MY_PERMISSIONS_REQUEST_SMS_RECEIVE);
            }
        }
    }

    //앱이 실행되는 동안에만 방송수신을 하고 싶을때 : onResume()에서 연결 ~ onPause()에서 해제한다.
    public void onResume() {
        super.onResume();
        //1) 인텐트 필터
        IntentFilter filter = new IntentFilter();
        //2) 필요한 액션을 추가한다.
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //3) 등록한다. - 연결
        registerReceiver(receiver, filter);
    }

    public void onPause() {
        super.onPause();
        //4) 해제한다.
        unregisterReceiver(receiver);
    }

}