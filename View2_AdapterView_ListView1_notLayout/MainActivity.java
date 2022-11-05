package com.example.view2_adapterview_listview1_notlayout;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리스트뷰 테스트");
        ListView list = findViewById(R.id.listView);
        //리스트 목록
        final String[]  mid =  {
            "히어로즈", "24시", "로스트", "로스트룸", "스몰빌", "탐정몽크",
            "빅뱅이론", "프렌즈", "덱스터", "글리", "가쉽걸", "테이큰", "슈퍼내추럴", "브이"
        };
        //어댑터 객체 생성 (context, 안드로이드가 제공하는 옵션, 데이터(mid)) - 리스트 형식을 옵션 선택
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mid); //리스트 형식
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter <String> adapterMultiChoice = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, mid); //체크박스 형식 - 멀티선택
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter <String> adapterSingleChoice = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, mid); //라디오 형식 - 싱글선택
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"position: " + Integer.toString(position)+", id: "+id, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, mid[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

}