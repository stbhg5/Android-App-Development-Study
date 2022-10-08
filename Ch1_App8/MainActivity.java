package com.example.ch1_app8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    //전역 변수 선언
    LinearLayout mLayout;
    RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //LinearLayout 태그 속성 객체로 가져오기
        mLayout = (LinearLayout)findViewById(R.id.backColor);
        mRadioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        
        //Radio 초기값 설정
        int id = R.id.blue;
        mRadioGroup.check(id); //체크박스에 체크
        colorChange(id); //체크하고 난 뒤에 이벤트 실행
    }

    /**
     * 이벤트 메서드
     * (xml 위젯에서 호출)
     * @param view
     */
    public void mOnClick(View view) {
        RadioButton radioButton = (RadioButton)view;
        colorChange(radioButton.getId());
    }

    /**
     * 색깔 바꾸는 메서드
     * @param id
     */
    void colorChange(int id) {
        switch(id) {
            case R.id.red:
                mLayout.setBackgroundColor(0xffff0000); //투명도 + RGB 16진수
                break;
            case R.id.blue:
                mLayout.setBackgroundColor(0xff0000ff); //투명도 + RGB 16진수
                break;
            case R.id.green:
                mLayout.setBackgroundColor(0xff00ff00); //투명도 + RGB 16진수
                break;
        }
    }
    
}