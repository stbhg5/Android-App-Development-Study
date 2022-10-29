package com.example.view1_fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

public class Fragment1 extends androidx.fragment.app.Fragment {

    ImageView imageView;

    /**
     * 프래그먼트가 액티비티와 연결될 경우 호출
     * 여기서 액티비티가 전달된다.
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * ★ 프래그먼트를 생성할 때 호출
     * 프래그먼트가 일시정지나 중단된 후 재개되었을 때 유지해야할 정보(Bundle savedInstanceState 매개변수)
     * 초기화 작업 적용
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * ★ 자신의 뷰를 그리기위해 호출
     * 레이아웃을 inflate 해서 생성
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment1, container, false);
    }
    
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 액티비티가 시작되는 상태에 들어가면서 호출
     * 여기서부터 사용자에게 보여진다.
     */
    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * 사용자와 상호작용을 시작한다.
     * onPause() 메서드를 호출하고 다시 재개되면 onResume() 메서드를 호출하게 된다.
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * ★ 사용자가 프래그먼트를 떠나면 호출
     * 사용자가 다시 오지 않을 수도 있으므로 현재 사용자 세션을 넘어 지속해야 하는 변경사항을 여기서 저장해 놓는다.
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}