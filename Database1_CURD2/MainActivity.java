package com.example.database1_curd2;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
// SQLiteOpenHelper 클래스 생성
    --  SQLiteOpenHelper  : 데이터베이스를 감싸고 있는 도우미 클래스이다.
              .  SQLiteDatabase    getWritableDatabase()    -   읽기/ 쓰기 모드로   데이터베이스를 오픈한다.
              .  SQLiteDatabase     getReadableDatabase()   -   읽기 전용모드로 데이터베이스를 오픈한다.

   --  SQLiteDatabase
              .  void  execSQL (String sql)    -  select문을 제외한    insert,upgrade,delete 에  사용된다.
              .  Cursor  rawQuery(String sql,   String[] selectionArgs)
              ---------------------전용 메소드 지원-------------------------------------
              .  Cursor query( boolena distinct ,  String table, String[] columns,  String selection, String[] selectionArgs, String groupBy, String having, orderBy, String limit )
                                  - distinct          : 만약 각 행이 유일하다며  true이다.
                                  - table             :  쿼리 대상이 되는 테이블
                                  - columns       :   어떤 컬럼을 반환할 것인지를 결정한다.    null은   모든 컬럼을 반환한다는 의미이다.
                                  - selection      :  SQL WHERE에 해당되는 필터이다.  null은 모든 행을 의미한다.
                                  - groupBy       : SQL GROUP BY절에  해당하는 필터이다.
                                  - having         : SQL Having절에 해당하는 필터이다.
                                  - orderBy       : SQL ORDER BY절에 해당하는 필터이다.
                                  - limit            : 반환되는 행의 개수를 제한한다.

              . long insert( String table,  String nullColumnHack,  ContentValues values )
                                  - table          : 행을 추가하는 테이블
                                  - nullColumnHack:  만약 null이 아니면,,,   null값을 삽입하는 컬럼의 이름이 된다.
                                  - values        :  삽입되는 값

              . int delete( String table,  String whereClause,  String[] whereArgs )
                                  - table         :  행을 추가하는 테이블
                                  - nullColumnHack: 만약 null이 아니면,  null 값을 삽입하는 컬럽의 이름이 된다.
                                  - values       :  삽입되는 값

               . int delete( String table, String whereClause,  String[] whereArgs )
                                   - 데이터베이스에 서 조건에 맞는 행을 삭제

               . int update( String table, ContentValues values, String whereClause,  String[] whereArgs )
                                    - 데이터베이스에서 조건에 맞는 행을 갱신한다.


*/

class DBHelper extends SQLiteOpenHelper{

    
    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 2;

    DBHelper(Context context){
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL( "CREATE TABLE contacts (" +
                                                     " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                     "name TEXT," +
                                                     "tex TEXT)");
    }

    
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion,  int  newversion) {
        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS  contacts");  // 현재것은 지우고
        onCreate(sqLiteDatabase);  // 다시 생성한다.
    }
}




public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_name , edit_tel;
    TextView edit_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);


        try{
            db = helper.getWritableDatabase();
        }catch (SQLiteException e){
            db = helper.getReadableDatabase();
        }
  
        edit_name = (EditText) findViewById(R.id.name);
        edit_tel = (EditText) findViewById(R.id.tel);
        edit_result = (TextView) findViewById((R.id.textView));
    }

  
    public void insert(View targe){
        
        String name = edit_name.getText().toString();
        String tel = edit_tel.getText().toString();
       
        db.execSQL( "INSERT INTO contacts VALUES (null, '"+name+" ',' "+tel+"');");  
      
        Toast.makeText(getApplicationContext(), "성공적으로 추가되었습니다", Toast.LENGTH_SHORT).show();       
        edit_name.setText(" ");
        edit_tel.setText(" ");
    }
    // 특정검색
    public void search(View target){
        String name = edit_name.getText().toString();
       
        Cursor cursor = db.rawQuery( "SELECT name, tel FROM contacts WHERE name = ' "+name+" ';", null);

        while (cursor.moveToNext()){
            String tel = cursor.getString(1); 
            edit_tel.setText(tel); 
        }
    }

    // 전체검색
    public void select_all(View target){
        Cursor cursor = db.rawQuery( "SELECT * FROM contacts", null);

        String s="id               name            tel \r\n";   
        while (cursor.moveToNext()){
            s += cursor.getString(0)+"          ";
            s += cursor.getString(1)+"             ";
            s += cursor.getString(2)+"          \r\n";
        }

        edit_result.setText(s);

    }
}