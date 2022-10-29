package com.example.menu6_dialog2_listalert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout)findViewById(R.id.backColor);
    }

    public void open(View view) {
        final CharSequence[] items = {"Red", "Green", "Blue"};
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //제목
        alertDialogBuilder.setTitle("색상을 선택하시오 ");
        //-------------------------------------------------------------------------------------------------
        //리스트 달기
        alertDialogBuilder.setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {//checkedItem -1은 아직 선택되지 않음
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {//배열 인덱스 가져옴
                if(i == 0) {
                    layout.setBackgroundColor(Color.RED);
                }else if(i == 1) {
                    layout.setBackgroundColor(Color.GREEN);
                }else if(i == 2) {
                    layout.setBackgroundColor(Color.BLUE);
                }
                Toast.makeText(getApplicationContext(),items[i],  Toast.LENGTH_SHORT).show();
            }
        });
        //-------------------------------------------------------------------------------------------------

        alertDialogBuilder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "색상선택이 되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                layout.setBackgroundColor(Color.WHITE);
                Toast.makeText(MainActivity.this,"색상선택이 취소되었습니다..",Toast.LENGTH_SHORT ).show();
                //finish(); // 이것을 적용하면 액티비티가 종료된다.
            }
        });
        
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}