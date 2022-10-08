package com.example.ch2_app2layoutjava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        //1. 파라미터 설정
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );

        //2. 레이아웃
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.LTGRAY);

        //3. 버튼 생성
        Button btn = new Button(this);
        btn.setText("버튼");
        btn.setGravity(Gravity.CENTER);
        btn.setBackgroundColor(Color.rgb(100, 150, 200));
        btn.setLayoutParams(params1);

        //margin 적용
        params1.bottomMargin = 100;
        btn.setLayoutParams(params1);

        //4. TextView 생성
        TextView tv = new TextView(this);
        tv.setText("텍스트뷰입니다.");
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(params2);

        //5. 레이아웃에 추가
        linearLayout.addView(btn);
        linearLayout.addView(tv);

        //6. 출력
        setContentView(linearLayout);
    }

}