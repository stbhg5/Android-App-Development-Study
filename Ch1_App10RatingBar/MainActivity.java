package com.example.ch1_app10ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

/*
RatingBar
(xml)           (java)
numStars        setNumStars()       별의 개수
rating          setRating()         기본 평점지정
setSize         setStepSize()       스텝 크기지정
isIndicator     setIsIndicator()    true - 입력 받을 수 있음, false - 입력 받을 수 없음
 */
public class MainActivity extends AppCompatActivity {

    //전역 변수
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LinearLayout 태그 속성 객체로 가져오기
        ratingBar = (RatingBar)findViewById(R.id.ratingVote);
        Button inc = (Button)findViewById(R.id.btnInc);
        Button dec = (Button)findViewById(R.id.btnDec);
        Button result = (Button)findViewById(R.id.btnResult);
        
        //이벤트 메서드
        inc.setOnClickListener(new View.OnClickListener() {//별 증가
            @Override
            public void onClick(View view) {
                ratingBar.incrementProgressBy(1);
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {//별 감소
            @Override
            public void onClick(View view) {
                ratingBar.incrementProgressBy(-1);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {//결과 값 알림 Toast
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "현재값 = " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}