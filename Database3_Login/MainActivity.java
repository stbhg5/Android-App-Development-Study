package com.example.database4_login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login.db";

    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user ( _id INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, pass TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
}

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_id, edit_pass;
    TextView edit_result;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);

        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }

        edit_id = (EditText) findViewById(R.id.id);
        edit_pass = (EditText) findViewById(R.id.pass);
        edit_result = (TextView) findViewById(R.id.textView);
    }


    public void enroll(View target) {

        String id = edit_id.getText().toString();
        String pass = edit_pass.getText().toString();

        db.execSQL("INSERT INTO user VALUES (null, '" + id + "', '" + pass+ "');");
        Toast.makeText(getApplicationContext(), "성공적으로 추가되었음", Toast.LENGTH_SHORT).show();

        edit_id.setText("");
        edit_pass.setText("");
    }

    // 로그인버튼을 누르면
    public void login(View target) {

        String id = edit_id.getText().toString();
        String pass = edit_pass.getText().toString();

        Cursor cursor;
        cursor = db.rawQuery("SELECT id, pass FROM user WHERE id='"+ id + "';", null);

        while (cursor.moveToNext()) {

            String pass2 = cursor.getString(1);  

            if(pass.equals(pass2))
                Toast.makeText(getApplicationContext(), "로그인 성공입니다.",Toast.LENGTH_SHORT).show();

        }
    }
}

