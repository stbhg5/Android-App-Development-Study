package com.example.activity3_explicitinent_returnresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public static final int Request_Code_Menu = 100;

    EditText ip1,ip2;
    Button plus,sub,mul,div,result;
    TextView output;

    Intent intent ;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip1 = (EditText)findViewById(R.id.ip1);
        ip2 = (EditText)findViewById(R.id.ip2);
        plus = (Button)findViewById(R.id.plus);
        sub  = (Button)findViewById(R.id.sub);
        mul  = (Button)findViewById(R.id.mul);
        div  = (Button)findViewById(R.id.div);

        result = (Button)findViewById(R.id.result);
        output = (TextView) findViewById(R.id.output);

        //1. 인텐트 객체생성
        intent = new Intent(getApplicationContext(), Calculation.class);

        flag = 0;
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //2. 인텐트 전송
                startActivityForResult(intent, Request_Code_Menu);
            }
        });
    }

    public void mCal(View view) {
        if(view.getId() == R.id.plus) {
            intent.putExtra("cal", "plus");
            intent.putExtra("first", ip1.getText().toString());
            intent.putExtra("second",ip2.getText().toString());

            if(flag == 0) {
                plus.setEnabled(false);
                flag = 1;
            }else {
                plus.setEnabled(true);
                flag = 0;
            }

        }else if(view.getId() == R.id.sub) {
            intent.putExtra("cal", "sub");
            intent.putExtra("first", ip1.getText().toString());
            intent.putExtra("second",ip2.getText().toString());

            if(flag == 0) {
                sub.setEnabled(false);
                flag = 1;
            }else {
                sub.setEnabled(true);
                flag = 0;
            }

        }else if(view.getId() == R.id.mul){
            intent.putExtra("cal", "mul");
            intent.putExtra("first", ip1.getText().toString());
            intent.putExtra("second",ip2.getText().toString());

            if(flag == 0){
                mul.setEnabled(false);
                flag = 1;
            }else  {
                mul.setEnabled(true);
                flag = 0;
            }

        }else if(view.getId() == R.id.div)  {
            intent.putExtra("cal", "div");
            intent.putExtra("first", ip1.getText().toString());
            intent.putExtra("second",ip2.getText().toString());

            if(flag == 0){
                div.setEnabled(false);
                flag = 1;
            }else {
                div.setEnabled(true);
                flag = 0;
            }
        }

    }

    /**
     * 결과값 받기 - onActivityResult()
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Request_Code_Menu && resultCode == RESULT_OK) {
            double r = data.getDoubleExtra("결과값", 0);
            DecimalFormat form = new DecimalFormat("#.##");
            String result = form.format(r);
            output.setText(result);
            ip1.requestFocus();
        }
    }

}