package com.example.view4_viewpager1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    int[] images = {R.drawable.pic4, R.drawable.pic2, R.drawable.pic3,R.drawable.pic5};
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. ViewPager 위젯을 가져온다
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        //2. 만들어 놓은  MyPagerAdapter 객체를 생성한다.
        myPagerAdapter = new MyPagerAdapter(MainActivity.this, images);

        //3. VewPAger에 Adapter를 붙인다.
        viewPager.setAdapter(myPagerAdapter);
    }

}