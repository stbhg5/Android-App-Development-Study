package com.example.file1_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    String FILENAME = "test.txt";
    EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯을 가져온다.
        data = (EditText) findViewById(R.id.data);
        Button readBtn = (Button) findViewById(R.id.read);
        Button writeBtn = (Button) findViewById(R.id.write);
        //쓰기
        writeBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try{
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND); //Context.MODE_PRIVATE
                    fos.write(data.getText().toString().getBytes());
                    fos.close();
                    data.setText(" ");
                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                }catch (IOException e) {}
                return true;
            }
        });
        // 읽기
        readBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    FileInputStream fis = openFileInput(FILENAME);
                    byte[] buffer = new byte[1000];
                    fis.read(buffer);
                    // File file = getApplicationContext().getFilesDir();
                    File file = getApplicationContext().getFileStreamPath(FILENAME);
                    data.setText(new String(buffer) + "\n 파일경로: " + file.getPath());
                    fis.close();
                }catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "파일을 읽을수가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

}