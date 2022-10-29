package com.example.image3_surfaceview1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

class MySurfaceView  extends SurfaceView  implements SurfaceHolder.Callback {//implement 방법 : ctrl + i

    public Ball basket[] = new Ball[20]; //Ball 객체 저장 배열
    private MyThread thread; //내부클래스로 구현된 클래스

    public MySurfaceView(Context context) {
        super(context);
        SurfaceHolder holder = getHolder(); //서피스뷰 홀더 객체를 가져온다.
        holder.addCallback(this); //서피스뷰 홀더의 콜백메서드를 받을 수 있게 서피스뷰 홀더에 콜백메서드 추가한다.
        thread = new MyThread(holder); //스레드 생성
        for(int i=0 ; i < 20 ; i++) {
            basket[i] = new Ball(20);
        }
    }

    /**
     * 스레드 객체 반환 메서드
     * @return
     */
    public MyThread getThread() {
        return thread;
    }

    //-------------------------------------------------------------------------------------

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        //스레드 시작
        thread.setRunning(true); //스레드에 구현해 놓은 메서드 : true, false 제어
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int  width,  int  height) {   // SurfaceHolder surfaceHolder, int i, int i1, int i2
        //Ball.WIDTH = width;
        //Ball.HEIGHT = height;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        thread.setRunning(false);
        while(retry) {
            try {
                thread.join(); //메인스레드와 통합시켜놓음
                retry = false; //스레드 종료
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //-------------------------------------------------------------------------------------

    /**
     * 스레드 클래스 구현
     */
    public class MyThread extends Thread {

        private boolean mRun = false;
        private SurfaceHolder mSurfaceHolder;
        public MyThread(SurfaceHolder surfaceHolder) {
            this.mSurfaceHolder = surfaceHolder;
        }

        @Override
        public void run() {
            while(mRun) {
                Canvas canvas = null;
                try {
                    canvas = mSurfaceHolder.lockCanvas(null);
                    canvas.drawColor(Color.BLACK);
                    synchronized (mSurfaceHolder) {
                        for(Ball ball : basket) {
                            ball.paint(canvas);
                        }
                    }
                }finally {
                    //캔버스가 완료되면
                    if(canvas != null) {
                        //캔버스 lock을 푼다 - 메인쪽에서 가져다 카피해서 붙인다.
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

        /**
         * 스레드 제어할 메서드
         * @param b
         */
        public void setRunning(boolean b) {
            mRun = b;
        }

    }

}