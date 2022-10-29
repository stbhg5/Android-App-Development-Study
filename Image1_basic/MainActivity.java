package com.example.image1_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*
이미지 사용방법
    1) 방법1 : 이미지를 리소스에 추가해서 사용하는 방법
    - 이미지를 res/drawable폴더에 추가한다.
    - 안드로이드는 확장자를 제외한 파일의 이름을 식별자로 활용한다.

    2) 방법2 : 직접 이미지 파일을  읽어서  그리는 방법
    - 이미지 경로를 이용해서 >> 비트맵으로 읽어오는 방법이다.
    - 게임과 같은 애플리케이션에서는 개발자가 이미지를 직접 그려주는 방법을 선호한다.

    - 순서 1) 비트맵 읽기
    Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath());
    또는 Bitmap bitmap = BitmapFactory.decodeResource(getResource(), R.drawable.이미지)
    - getResource() : 이미지 데이터를 포함하는 애플리케이션 리소스객체
    즉, Resource객체를 가져와서... R.drawable.이미지 객체를 잦아
    디코딩해서  비트맵으로 전환한다.
    - R.drawable.이미지 : 이미지의 리소스 식별자

    - 순서 2) 비트맵 그리기 - drawBitmap()
    . canvas.drawBitmap(Bitmap bitmap, float left, float top, Paint paint)
    - bitmap : 화면에 그릴 비트맵
    - x, y : 그릴 위치
    - patint:  이미지를 변환할  변환행렬이 들어있는 Pait객체 (없으면  null)

    - 확대 축소 - createScaledBitmap
    . Bitmap.createScaledBitmap(bitmap, width, height, flase) :
    - width * height의 크기로 비트맵을 생성한다.

    - 변환 - preScale(float sx, float sy)
    . 도형의 크기를 x와 y축으로 sx, sy만큼 변경한다.
    . M' = M * S(sx, sy)
*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    float scaleX = 1.0f, scaleY = 1.0f, rotateAngle;
    MyView displayView;

    /**
     * 내부클래스 - 사용자 뷰
     */
    public class MyView extends View {

        public MyView(MainActivity context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            //이미지의 중심점
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            //크기변환
            canvas.scale(scaleX, scaleY, centerX, centerY);
            //회전
            canvas.rotate(rotateAngle, centerX, centerY);
            //비트맵 객체 생성
            Bitmap picture = BitmapFactory.decodeResource(this.getResources(),  R.drawable.lena);
            //이미지 그리기
            canvas.drawBitmap(picture, 0, 0, paint);
        }

    }

    //실행
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.imageDisplay);
        //사용자 뷰를 특정한 컨테이너에 추가
        displayView = new MyView(this);
        layout.addView(displayView);
    }

    public void expand(View view) {
        scaleX += 0.3;
        scaleY += 0.3;
        displayView.invalidate();
    }

    public void shrink(View view) {
        scaleX -= 0.3;
        scaleY -= 0.3;
        displayView.invalidate();
    }

    public void rotateLeft(View view) {
        rotateAngle -= 30;
        displayView.invalidate();
    }

    public void rotateRight(View view) {
        rotateAngle += 30;
        displayView.invalidate();
    }

}