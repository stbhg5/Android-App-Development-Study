package com.example.database1_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

class MyDBHelper extends SQLiteOpenHelper {// Ctrl+i 오버라이드 처리

    /**
     * mytest.db를 생성
     * @param context
     */
    MyDBHelper(Context context) {
        super(context, "mytest.db", null,1); //버전지정
        //나중에 테이블 구조가 변경되어 앱을 빌드/설치/실행하면 아래에 있는 onUpgrade() 메서드를 자동 호출
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //테이블 생성
        sqLiteDatabase.execSQL("create table people (_id integer primary key autoincrement, name text, age text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //테이블을 수정하기 위해서 기존의 테이블은 삭제
        sqLiteDatabase.execSQL("drop table if exists pepole;");
    }

}

//MainActivity
public class MainActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    EditText mEditName;
    EditText mEditAge;
    TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DBHelper 객체 생성
        myDBHelper = new MyDBHelper(this);
        mEditName  = (EditText)findViewById(R.id.editeName);
        mEditAge   = (EditText)findViewById(R.id.editeAge);
        mTextResult= (TextView)findViewById(R.id.textResult);
    }

    public void mOnClick(View v) {
        SQLiteDatabase db;
        ContentValues values; //하나의 레코드를 정의하는 객체(레코드 생성). 레코드의 삽입과 갱신에 사용(쓰기시 사용).
        String [] projection = {"_id", "name", "age"};
        Cursor cur; //여러개의 레코드를 가져와서 객체화시키고 하나에 접근하여 필드값을 꺼낼 수 있다(읽기시 사용).
        switch(v.getId()) {

            case R.id.btnInsert:
                if(mEditName.getText().length() > 0 && mEditAge.getText().length() > 0) {
                    //쓰기 데이터베이스
                    db = myDBHelper.getWritableDatabase();
                    //쓰기위한 레코드를 생성
                    values = new ContentValues();
                    values.put("name", mEditName.getText().toString());
                    values.put("age", mEditAge.getText().toString());
                    //INSERT
                    db.insert("people", null, values);
                    //종료
                    db.close();
                }
                break;

            case R.id.btnSelectAll:
                //읽기 데이터베이스
                db = myDBHelper.getReadableDatabase();
                //읽어와서 Cursor 객체를 얻는다.
                cur = db.query("people", projection, null, null, null, null, null);
                if(cur != null) {
                    showResult(cur); //Cursor에 있는 검색 결과를 구현해 놓은 showResult() 메서드 호출해서 출력한다.
                    cur.close();
                }
                //닫기
                myDBHelper.close();
                break;

            case R.id.btnSelectName:
                if(mEditName.getText().length() > 0) {
                    db = myDBHelper.getReadableDatabase();
                    String name = mEditName.getText().toString();
                    cur = db.query("people", projection, "name=?", new String[]{name}, null, null, null);
                    if(cur != null) {
                        showResult((cur));
                        cur.close();
                    }
                    myDBHelper.close();
                }
                break;

            case R.id.btnUpdateAge:
                if(mEditName.getText().length() > 0 && mEditAge.getText().length() > 0) {
                    db = myDBHelper.getWritableDatabase();
                    String name = mEditName.getText().toString();
                    values = new ContentValues();
                    values.put("age",mEditAge.getText().toString());
                    db.update("people", values, "name=?", new String[]{name});
                    myDBHelper.close();
                }
                break;

            case R.id.btnDeleteName:
                if(mEditName.getText().length() > 0) {
                    db = myDBHelper.getWritableDatabase();
                    String name = mEditName.getText().toString();
                    db.delete("people", "name", new String[]{name});
                    myDBHelper.close();
                }
                break;

            case R.id.btnDeleteAll:
                db = myDBHelper.getWritableDatabase();
                db.delete("people", null, null);
                myDBHelper.close();
                break;

        }
    }

    void showResult(Cursor cur) {
        mTextResult.setText("");
        int name_col = cur.getColumnIndex("name");
        int age_col = cur.getColumnIndex("age");
        while (cur.moveToNext()) {
            String name = cur.getString(name_col);
            String age  = cur.getString(age_col);
            mTextResult.append(name+","+age+"\n");
        }
    }

}