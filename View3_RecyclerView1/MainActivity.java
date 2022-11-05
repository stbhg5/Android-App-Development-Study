package com.example.view3_recyclerview1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] data = new String[100];
        for(int i=1 ; i <= 100 ; i++) {
            data[i - 1] = "friend #" + i;
        }
        RecyclerView recyclerView = findViewById(R.id.rview);
        int columns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        //리사이클러 뷰 어댑터 객체 생성
        adapter = new MyRecyclerViewAdapter(this, data);
        //리스너 설정
        adapter.setClickListener(this);
        //뷰에 리사이클러어댑터를 설정
        recyclerView.setAdapter(adapter);
    }

    /**
     * ItemClickListener 인터페이스 구현 메서드
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        //Log.i("TAG", "item: " + adapter.getItem(position) + "number: " + position);    }
        Toast.makeText(getApplicationContext(),"item: " + adapter.getItem(position)+"number: " + position, Toast.LENGTH_SHORT).show();
    }

}