package com.example.ch1_app9imagebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //전역 변수 선언
    ImageButton btn1, btn2;
    ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LinearLayout 태그 속성 객체로 가져오기
        btn1 = (ImageButton)findViewById(R.id.btn1);
        btn2 = (ImageButton)findViewById(R.id.btn2);
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
    }

    /**
     * 이벤트 메서드
     * (xml 위젯에서 호출)
     * @param view
     */
    public void mOnclick(View view) {
        ImageButton btn = (ImageButton)view;
        changeImage(btn.getId());
    }

    /**
     * 이미지 바꾸는 메서드
     * @param id
     */
    void changeImage(int id) {
        if(id == R.id.btn1) {
            img1.setVisibility(View.GONE); //공간없어짐
            img2.setVisibility(View.VISIBLE); //INVISIBLE : 공간은 남고 안보임
        }else if(id == R.id.btn2) {
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.GONE);
        }
    }

}