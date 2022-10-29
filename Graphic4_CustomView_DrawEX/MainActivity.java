package com.example.graphic4_customview_drawex;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    MyTouchView myDrawView;
    ImageButton currPaint, erasePaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDrawView = (MyTouchView) findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
        currPaint = (ImageButton) paintLayout.getChildAt(0);
        erasePaint = (ImageButton) paintLayout.getChildAt(5);
    }

    public void clicked(View view) {
        if(view != currPaint) {
            String color = view.getTag().toString();

            if(view == erasePaint) {
                myDrawView.paint.setStrokeWidth(80f);
            }
            else {
                myDrawView.paint.setStrokeWidth(10f);
            }
            myDrawView.setColor(color);
            currPaint = (ImageButton) view;
        }
    }

}