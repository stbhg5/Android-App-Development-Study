package com.example.provider1_picture;

import android.Manifest;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.File;



public class MainActivity extends AppCompatActivity {

    private Cursor cursor;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.picture);
        //동적으로 권한요청
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    public void displayFirstImage(View v) {
        Toast.makeText(getApplicationContext(), "displayFirstImage()", Toast.LENGTH_LONG).show();
        try {
            String[] projection = new String[] {
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA
            };

            cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);

            int size = cursor.getCount();
            if(size == 0) {
                Toast.makeText(getApplicationContext(), "장치에 이미지가 없음!", Toast.LENGTH_LONG).show();
            }else {
                if(cursor.moveToFirst()) {
                    String imageLocation = cursor.getString(1); //두번째의 MediaStore.Images.ImageColumns.DATA
                    Toast.makeText(getApplicationContext(), imageLocation, Toast.LENGTH_LONG).show();
                    File imageFile = new File(imageLocation);
                    if(imageFile.exists()) {
                        Bitmap bm = BitmapFactory.decodeFile(imageLocation);
                        imageView.setImageBitmap(bm);
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void displaySecondImage(View v) {
        Toast.makeText(getApplicationContext(), "displayFirstImage()", Toast.LENGTH_LONG).show();
        try {
            String[] projection = new String[] {
                    MediaStore.Images.ImageColumns._ID,
                    MediaStore.Images.ImageColumns.DATA,
                    MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.DATE_TAKEN,
                    MediaStore.Images.ImageColumns.MIME_TYPE
            };
            cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);
            int size = cursor.getCount();
            if(size == 0) {
                Toast.makeText(getApplicationContext(), "장치에 이미지가 없음!", Toast.LENGTH_LONG).show();
            }else {
                if (cursor.moveToLast()) {
                    String imageLocation = cursor.getString(1);
                    Toast.makeText(getApplicationContext(), imageLocation, Toast.LENGTH_LONG).show();
                    //파일 생성
                    File imageFile = new File(imageLocation);
                    if(imageFile.exists()) {
                        //비트맵 출력
                        Bitmap bm = BitmapFactory.decodeFile(imageLocation);
                        imageView.setImageBitmap(bm);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}