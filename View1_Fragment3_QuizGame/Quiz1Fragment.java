package com.example.view1_fragment3_quizgame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;


public class Quiz1Fragment extends Fragment {

    //반환타입이 View객체이므로 View로 업캐스팅해서 보낸다.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.question1, container, false);
        return view;

    }

}
