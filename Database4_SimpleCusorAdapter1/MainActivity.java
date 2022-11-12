package com.example.database3_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


class DBHelper extends SQLiteOpenHelper {

    private static final  String DATABASE_NAME = "mycontacts.db";
    private static final int  DATABASE_VERSION = 2;  

 
    public  DBHelper(Context context){
        super(context,  DATABASE_NAME, null,  DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
  
        sqLiteDatabase.execSQL( "CREATE TABLE contacts (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,  tel TEXT )");
       
        for(int i=0; i<10; i++){
            sqLiteDatabase.execSQL("INSERT INTO contacts VALUES (null,  '홍길동' ,  '010-1234-567 "+i+ " '); ");
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int  oldversion, int  newversion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }
}


public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_name,  edit_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);

        db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery( "SELECT * FROM contacts", null);


        startManagingCursor(cursor);


  
        LayoutInflater inflater = this.getLayoutInflater();   
        View  rowView = inflater.inflate(R.layout.list, null ,false); 

        
        String[] from = {"name","tel"};
       
        //TextView tname = (TextView) rowView.findViewById(R.id.item_name);
        //TextView ttel     = (TextView) rowView.findViewById(R.id.item_tel);
        int[] to = { R.id.item_name,R.id.item_tel };

                                                                            
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list,                cursor,                     from,               to) ;

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

    }

}












