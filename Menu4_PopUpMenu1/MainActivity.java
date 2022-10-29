package com.example.menu4_popupmenu1;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View textview) {
        //1. 팝업 메뉴 객체 생성
        PopupMenu popup = new PopupMenu(this, textview); //특정 뷰를 지정
        //2. inflate() 메서드로 xml 메뉴 파일을 객체화시켜 메뉴로 등록한다.
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
        //3. 메뉴를 선택했을 때 이벤트리스너 등록
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), "클릭된 팝업 메뉴: "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //4. 팝업 보여주기
        popup.show();
    }

}