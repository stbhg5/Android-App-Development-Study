package com.example.map1_getlocation;

// 소스만 입력하고 Alt+Enter를 눌러서 import 문장을 자동으로 생성한다.

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/*
   사용자 위치가져오기
    0. 매니페스트에 권한등록
    1. 동적으로 권한 부여
    2. LocationManager 객체생성 : getSystemService( Context.LOCATION_SERVICE )
    3. LocationListener  인터페이스 객체생성
    4. 위치가 변하면 자동으로 호출되는 콜백메소드 : 변했을때 할 일을 코드구현 : onLocationChanged(Location location)
    5. 위치를 업데이트받기위해 리스너를 LocationManager에게 등록:
       >> locationManager.requestLocationUpdates( LocationManager . GPS_PROVIDER, 0 , 0 , locationListener )
          . GPS콘텐트제공자 : LocationManager
          .  0 : 최소시간간격 (밀리초)
          .  0:  최소거리간격 (미터)
          . locationListener : 리스너
*/

public class MainActivity extends AppCompatActivity {

    //RequestCode 값
    private int MY_PERMISSIONS_REQUEST_LOCATION = 10;
    TextView status;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = (TextView) findViewById(R.id.status);
        //1. 동적으로 권한 부여
        //권한부여
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        //2. LocationManager 객체생성 : getSystemService( Context.LOCATION_SERVICE )
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //3. LocationListener 인터페이스 객체생성
        LocationListener locationListener = new LocationListener() {
            //4. 위치가 변하면 자동으로 호출되는 콜백메소드 : 변했을때 할 일을 코드구현
            public void onLocationChanged(Location location) {
                status.setText("위도; " + location.getLatitude() + "\n경도:"
                        + location.getLongitude() + "\n고도:"
                        + location.getAltitude());
            }
            //나머지 콜백메소드 그냥둔다.
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };

        //여기서 다시한번 권한체크 - 안되어 있으면 경고 토스트
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "First enable LOCATION ACCESS in settings.", Toast.LENGTH_LONG).show();
            return;
        }

        //5. 위치를 업데이트 받기위해 리스너를 LocationManager에게 등록
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }

}