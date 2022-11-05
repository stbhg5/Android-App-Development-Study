package com.example.activity2_explicitintent_basic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    Button tBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tBtn = (Button)findViewById(R.id.tBtn);
    }

    public void tOnClick(View v){
        finish();
    }

}
