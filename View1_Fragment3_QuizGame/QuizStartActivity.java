package com.example.view1_fragment3_quizgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class QuizStartActivity extends AppCompatActivity implements View.OnClickListener{

    Button next;
    Fragment fragment;
    int prob_count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_start);
        //프래그먼트 객체 생성
        fragment = new Quiz1Fragment();
        //트랜잭션 처리 메서드
        transit(fragment);

        next = findViewById(R.id.next);
        next.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        prob_count++;

        if(prob_count==2) {
            fragment = new Quiz2Fragment();
            transit(fragment);
        }
        else if(prob_count==3) {
            fragment = new Quiz3Fragment();
            transit(fragment);
        }
        else if(prob_count==4) {
            fragment = new Quiz4Fragment();
            transit(fragment);
        }
        else if(prob_count==5) {
            fragment = new Quiz5Fragment();
            transit(fragment);
            next.setText("Submit");
        }else if(prob_count==6) {//경고 다이얼로그 생성
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("점수 기능을 추가해보세요: ");
            builder.setMessage("5점");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish(); //현재 Activity 종료
                    Intent intent = new Intent(QuizStartActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    /**
     * 프래그먼트 트랜잭션 처리
     * @param fragment
     */
    void transit(Fragment fragment) {
        //1. 프래그먼트 매니저
        FragmentManager manager = getSupportFragmentManager();
        //2. 트랜잭션
        FragmentTransaction transaction = manager.beginTransaction();
        //3. 교체
        transaction.replace(R.id.framelayoutid,fragment);
        //4. 커밋
        transaction.commit();
    }

}
