package com.example.menu2_optionmenu2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);
    }

    /**
     * 메뉴등록
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //메인메뉴 등록
        MenuItem item1 = menu.add(0,1,0,"move1");
        item1.setIcon(R.drawable.ic_launcher_background);
        MenuItem item2 = menu.add(0,2,0,"move2");
        item2.setIcon(R.drawable.ic_launcher_background);
        menu.add(0,3,0,"move3").setIcon(R.drawable.ic_launcher_background);
        //서브메뉴 추가작성방법 - Menu 객체를 추가한후 작성한다.
        Menu sub = menu.addSubMenu("서브영화");
        sub.add(1,4,0,"라디오스타").setIcon(R.drawable.ic_launcher_background);
        sub.add(2,5,0,"비열한거리").setIcon(R.drawable.ic_launcher_background);
        return true;
    }

    /**
     * 선택했을 때 할 일 등록
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case 1:
                img.setImageResource(R.drawable.mov01);
                Toast.makeText(this,"사과",Toast.LENGTH_LONG).show();
                break;
            case 2:
                img.setImageResource(R.drawable.mov02);
                Toast.makeText(getApplicationContext(),"딸기",Toast.LENGTH_LONG).show();
                break;
            case 3:
                img.setImageResource(R.drawable.mov03);
                Toast.makeText(getApplicationContext(),"수박",Toast.LENGTH_LONG).show();
                break;
            case 4:
                img.setImageResource(R.drawable.mov04);
                Toast.makeText(getApplicationContext(),"수박",Toast.LENGTH_LONG).show();
                break;
            case 5:
                img.setImageResource(R.drawable.mov05);
                Toast.makeText(getApplicationContext(),"수박",Toast.LENGTH_LONG).show();
                break;
            case 6:
                img.setImageResource(R.drawable.mov06);
                Toast.makeText(getApplicationContext(),"수박",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

}