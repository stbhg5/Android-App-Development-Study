package com.example.graphic3_customview_seeinxml;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView  extends View {

    /**
     * 생성자1
     * @param context
     */
    public CustomView(Context context) {
        super(context);
        setBackgroundColor(Color.YELLOW);
    }

    /**
     * 생성자2 - XML에서 구현할 생성자
     * @param context
     * @param attrs
     */
    public CustomView(Context context, AttributeSet attrs) {
        super(context);
        setBackgroundColor(Color.YELLOW);
    }

    //그리기
    float x = 0, y = 0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawArc(new RectF(50 + x, 50 + y , 600 + x, 600 + y), 45, 300 - (int)x, true, paint);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         x = event.getX();
         y = event.getY();
        return true;
    }

}