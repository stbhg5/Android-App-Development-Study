package com.example.menu1_optionmenu1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img =(ImageView)findViewById(R.id.img);
    }

    /**
     * 메뉴등록
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.myoptionmenu, menu); //XML을 객체화
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
            case R.id.apple:
                img.setImageResource(R.drawable.redapple);
                Toast.makeText(this,"사과",Toast.LENGTH_LONG).show();
                break;
            case R.id.strawberry:
                img.setImageResource(R.drawable.strawberry);
                Toast.makeText(getApplicationContext(),"딸기",Toast.LENGTH_LONG).show();
                break;
            case R.id.watermelon:
                img.setImageResource(R.drawable.watermelon);
                Toast.makeText(getApplicationContext(),"수박",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

}