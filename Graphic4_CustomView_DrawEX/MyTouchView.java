package com.example.graphic4_customview_drawex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyTouchView extends View {

    public Paint paint = new Paint();
    private Path path = new Path();
    private int paintColor = 0xFF000000;
    private Paint canvasPaint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    /**
     * 생성자1
     * @param context
     */
    public MyTouchView(Context context){super(context);}

    /**
     * 생성자2 - XML에서 구현할 생성자
     * @param context
     * @param attrs
     */
    public MyTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(paintColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    /**
     * 크기가 변화되면 호출되는 메서드
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //그림판 캔버스 생성
        canvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        canvasPaint =  new Paint(Paint.DITHER_FLAG);
    }

    /**
     * 그래픽
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(canvasBitmap,0,0,canvasPaint);
        canvas.drawPath(path,paint);
    }

    /**
     * 터치이벤트
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch(event.getAction()) {//getAction() 액션값 가져옴
            //손가락으로 누르면
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX, touchY); //이동
                break;
            //이동하면
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX,touchY); //그려라
                break;
            //손가락 떼면
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(path, paint); //그림 그리고 나서
                path.reset(); //지워라
            default:
                return false;
        }
        invalidate();
        return true;
    }

    /**
     * 컬러설정 메서드 구현
     * @param newColor
     */
    public void setColor(String newColor) {
        invalidate();
        paintColor = Color.parseColor(newColor); //색상값 저장
        paint.setColor(paintColor);
    }

}