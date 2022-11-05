package com.example.activity3_explicitinent_returnresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Calculation extends Activity {

    String cal, first, second ;
    double result;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculation);

        btn = (Button)findViewById(R.id.back);

        //1. 인텐트를 받아온다.
        Intent intent = getIntent();

        cal = intent.getStringExtra("cal").toString();
        first = intent.getStringExtra("first").toString();
        second= intent.getStringExtra("second").toString();

        Toast.makeText(getApplicationContext(),"첫번째 수 " + first + "와 두번째 수 " + second + "를" + cal + "하시오.", Toast.LENGTH_LONG).show();

        btn.setOnClickListener(new View.OnClickListener(){

            Intent intentB = new Intent(getApplicationContext(), MainActivity.class);
            @Override
            public void onClick(View view) {
                if ( cal.equals("plus")) {
                    result = Double.parseDouble(first)+ Double.parseDouble(second);
                    intentB.putExtra("결과값", result);
                }else if(cal.equals("sub")) {
                    result = Double.parseDouble(first) - Double.parseDouble(second);
                    intentB.putExtra("결과값", result);
                }else if(cal.equals("mul")) {
                    result = Double.parseDouble(first) * Double.parseDouble(second);
                    intentB.putExtra("결과값", result);
                }else if(cal.equals("div")) {
                    result = Double.parseDouble(first) / Double.parseDouble(second);
                    intentB.putExtra("결과값", result);
                }
                //결과값 설정
                setResult(RESULT_OK, intentB);
                //현재 페이지 종료
                finish();
            }
        });
    }

}
