package com.example.menu7_customdialog1_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 이벤트 메서드
     * @param v
     */
    @Override
    public void onClick(View v) {
        Dialog  loginDialog = new Dialog(this);

        loginDialog.setContentView(R.layout.custom_dialog);
        loginDialog.setTitle("로그인 화면");

        Button login = (Button)loginDialog.findViewById(R.id.login);
        Button cancel = (Button)loginDialog.findViewById(R.id.cancel);

        EditText username = (EditText)loginDialog.findViewById(R.id.username);
        EditText password = (EditText)loginDialog.findViewById(R.id.password);

        //login 버튼을 눌렀을 때 리스너
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().trim().length() > 0  && password.getText().toString().trim().length() > 0) {
                    Toast.makeText(getApplicationContext(), "로그인 성공",   Toast.LENGTH_LONG).show();
                    loginDialog.dismiss();
                }else {
                    Toast.makeText(getApplicationContext(), "다시 입력하시오",
                    Toast.LENGTH_LONG).show();
                }
            }
        });
        
        //cancel 버튼을 눌렀을 때 리스너
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog.dismiss(); //dismiss() : 사용자 다이얼로그 닫기
            }
        });
        
        //보여주기
        loginDialog.show();
    }

}