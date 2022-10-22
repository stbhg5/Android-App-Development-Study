package com.example.ch5_widget4chronomenter2_progressbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb;
    TextView tv;
    Button sbtn,ebtn,rbtn;
    MyThread mt = new MyThread();
    int delay = 5000;
    int value ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Chronometer ch = (Chronometer)findViewById(R.id.cb);
        pb = (ProgressBar) findViewById(R.id.pb);
        tv = (TextView) findViewById(R.id.tv2);
        sbtn = (Button) findViewById(R.id.sbtn);
        ebtn = (Button) findViewById(R.id.ebtn);
        rbtn = (Button) findViewById(R.id.rbtn);

        //시작버튼
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mt.start();
            }
        });

        // 일시중지버튼 - ??
       ebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              try {
                   value = pb.getProgress();
                // mt.wait(delay);                // 화면이 꺼져버림
                mt.sleep(delay);   // 5초동안 일시정지
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       });

       //리스타트버튼
       rbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               try{
                   mt.start();
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
       });
    }

    public class MyThread extends Thread {

        public void run() {
            for(int i=0 ; i <= 100 ; i++) {
                tv.setText(Integer.toString(i)+"%");
                if(i==100){
                    tv.setText("종료!!");
                }
                pb.setProgress(i);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), "에러발생", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}