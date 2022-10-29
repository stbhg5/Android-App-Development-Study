package com.example.image3_surfaceview1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class Ball {

    int x, y, xInc = 1, yInc = 1;
    int diameter; //공의 반지름
    static int WIDTH = 1080, HEIGHT = 1550; //움직이는 공간의 크기(화면너비)

    public Ball(int d) {
        this.diameter = d; //반지름
        //벽에 닿았는지 체크
        x = (int)(Math.random() * (WIDTH - d) + 3);
        y = (int)(Math.random() * (HEIGHT - d) + 3);
        
        xInc = (int)(Math.random() * 30 + 1);
        yInc = (int)(Math.random() * 30 + 1);
    }

    public void paint(Canvas g) {
        Paint paint = new Paint();
        if(x < diameter || x > (WIDTH - diameter)) {
            xInc = -xInc;
        }
        if(y < diameter || y > (HEIGHT - diameter)) {
            yInc = -yInc;
        }
        x += xInc; //~Inc: 움직이는 거리
        y += yInc;
        paint.setColor(Color.RED);
        g.drawCircle(x, y, diameter, paint);
    }

}