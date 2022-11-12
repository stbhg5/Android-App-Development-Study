package com.example.file2_memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText fileName;
    EditText memo;
    String filestr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = (EditText)findViewById(R.id.fileName);
        memo = (EditText)findViewById(R.id.memo);

        //저장
        Button writeBtn = (Button) findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    filestr = fileName.getText().toString();

                    //출력스트림을 연다
                    FileOutputStream fos = openFileOutput(filestr,  Context.MODE_PRIVATE);
                    fos.write(memo.getText().toString().getBytes());
                    memo.setText(" ");
                    fos.close();

                    File file =  getApplicationContext().getFileStreamPath( filestr );
                    Toast.makeText(getApplicationContext(), file.getPath() + "파일이 생성되었습니다.", Toast.LENGTH_SHORT).show();
                }catch (IOException e) {}
            }
        });

        //읽기
        fileName.setText(filestr);
        Button readBtn = (Button) findViewById(R.id.readBtn);
        readBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(filestr);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    memo.setText(new String(buffer));
                    fis.close();
                } catch (IOException e) {
                }
            }
        });

        //삭제
        Button deleteButton = (Button) findViewById(R.id.deleteBtn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(filestr != null) {
                    deleteFile(filestr);
                    memo.setText(" ");
                    fileName.setText(" ");
                }
                //이제부터는  파일리스트를 출력해보자 -        String[]    getApplicationContext().fileList()   활용
                String filelist ="";
                String[] file = getApplicationContext().fileList();
                for (int i=0; i<file.length; i++){
                    filelist += file[i].toString()+",  ";
                }
                if(filelist != null && filelist != ""){
                    Toast.makeText(getApplicationContext(), "남아있는 파일은 : " + filelist, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "남아있는 파일이 없습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}