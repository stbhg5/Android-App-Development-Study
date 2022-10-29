package com.example.view1_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 버튼에 따른 이벤트 메서드1
     * @param v
     */
    public void setFrag1(View v) {
        //1. 프래그먼트 매니저 객체를 가져온다 - getSupportFragmentManager()
        FragmentManager manager = getSupportFragmentManager();
        //2. 프래그먼트 매니저 객체에서 프래그먼트 트랜젝션 객체 가져옴
        FragmentTransaction ft = manager.beginTransaction();
        Fragment1 f1 = new Fragment1();
        //3. 프래그먼트 교체
        ft.replace(R.id.fame_container, f1, "첫번째프래그먼트");
        //4. 커밋처리
        ft.commit();
        //ft.commitAllowingStateLoss(); //상태에 대한 손실을 우려해서 즉시 실행하고자 할 때 사용
        //ft.commitNow(); //현재 제출하려 하는 트랜젝션만 실행처리, back stack에 추가할 트랜젝션을 사용할 수 없다.
    }

    /**
     * 버튼에 따른 이벤트 메서드2
     * @param v
     */
    public void setFrag2(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment2  f2 = new Fragment2();
        ft.replace(R.id.fame_container, f2, "두번째프래그먼트");
        //ft.commit();
        ft.commitAllowingStateLoss();
        // ft.commitNow();
    }

}