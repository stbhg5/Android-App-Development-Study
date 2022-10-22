package com.example.ch2_app6_tablecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //전역 변수 선언
    //xml에서 가져온 객체
    EditText editText1, editText2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;

    //EditText에서 넘어올 때 String으로 넘어옴
    String num1, num2;
    Integer result;

    //버튼 숫자 가져옴
    Integer[] numBtnIDs = {
        R.id.btnNum0, R.id.btnNum1, R.id.btnNum2, R.id.btnNum3, R.id.btnNum4,
        R.id.btnNum5, R.id.btnNum6, R.id.btnNum7, R.id.btnNum8, R.id.btnNum9
    };

    //배열 인덱스
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("테이블 계산");
        //가져올 때 상수로 가져온다(int)
        editText1 = (EditText)findViewById(R.id.edit1);
        editText2 = (EditText)findViewById(R.id.edit2);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnMul = (Button)findViewById(R.id.btnMul);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        textResult = (TextView)findViewById(R.id.textResult);
        
        //더하기
        btnAdd.setOnTouchListener(new View.OnTouchListener() {//자바 내부 익명 클래스로 구현
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = editText1.getText().toString(); //문자로 바꿔야 함
                num2 = editText2.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2); //계산시 숫자로
                textResult.setText("계산 결과 : " + result.toString()); //출력시 문자로
                return false;
            }
        });
        //빼기
        btnSub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = editText1.getText().toString();
                num2 = editText2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());
                return false;
            }
        });
        //곱하기
        btnMul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = editText1.getText().toString();
                num2 = editText2.getText().toString();
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());
                return false;
            }
        });
        //나누기
        btnDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = editText1.getText().toString();
                num2 = editText2.getText().toString();
                //result = Integer.parseInt(num1) / Integer.parseInt(num2);
                Double r = Double.parseDouble(num1) / Double.parseDouble(num2);
                //소수점포맷
                DecimalFormat df = new DecimalFormat("#,###.##");
                String re = df.format(r);
                textResult.setText("계산 결과 : " + re);
                return false;
            }
        });

        //숫자버튼을 활용한 숫자입력 처리
        Button[] numButtons = new Button[10];
        for(i=0 ; i < numButtons.length ; i++) {//배열 가져오기
            numButtons[i] = (Button)findViewById(numBtnIDs[i]);
        }
        for(i=0 ; i < numBtnIDs.length ; i++) {
            final int index;
            index = i;
            numButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(editText1.isFocused() == true) {//첫번째 숫자 입력항목 포커스
                        num1 = editText1.getText().toString() + numButtons[index].getText().toString(); //기존값 + 버튼값
                        editText1.setText(num1);
                    }else if(editText2.isFocused() == true) {//두번째 숫자 입력항목 포커스
                        num2 = editText2.getText().toString() + numButtons[index].getText().toString(); //기존값 + 버튼값
                        editText2.setText(num2);
                    }else {//포커스가 없을 때
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}