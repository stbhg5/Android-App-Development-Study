package com.example.view2_adapterview_listview2_inlayout;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String [] titles = {
        "The Wizard of Oz(1939)",
        "Citzen Kane (1941)",
        "All About Eve (1950)",
        "The Third Man (1949)",
        "A Hard Day's Night (1964)",
        "The Wizard of Oz(1939)",
        "Citzen Kane (1941)",
        "All About Eve (1950)",
        "The Third Man (1949)",
        "A Hard Day's Night (1964)"
    };
    Integer[] images = {
        R.drawable.mov31,
        R.drawable.mov32,
        R.drawable.mov33,
        R.drawable.mov34,
        R.drawable.mov35,
        R.drawable.mov36,
        R.drawable.mov37,
        R.drawable.mov39,
        R.drawable.mov40
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.movieListView);
        //1. 어댑터 생성 - CustomListAdapter
        CustomListAdapter adapter = new CustomListAdapter(MainActivity.this);
        //2. 어댑터를 뷰에 설정
        list.setAdapter(adapter);
        //3. 리스너 구현
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),titles[position],Toast.LENGTH_SHORT).show(); //getBaseContext() 동일
            }
        });
    }

    /**
     * Adapter 구현
     */
    public class CustomListAdapter extends ArrayAdapter<String> {//배열에서 가져옴

        private final Activity context;

        CustomListAdapter(Activity context){
            super(context, R.layout.listitem, titles);
            this.context = context;
        }

        /**
         * getView() 메서드 재구현(오버라이드)
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //1. xml을 View 객체로 만들기 위해 inflater 객체 사용
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null ,false);

            //2. rowView(listitem.xml) 속에 있는 뷰(위젯)을 가져온다 - 내부클래스처럼 접근
            ImageView imageView = (ImageView)rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView rating= (TextView) rowView.findViewById(R.id.rating);
            TextView genre = (TextView) rowView.findViewById(R.id.genre);
            TextView year  = (TextView) rowView.findViewById(R.id.releaseYear);

            //3. 데이터 설정
            title.setText(titles[position]);
            imageView.setImageResource(images[position]);
            rating.setText("9.0" + position);
            genre.setText("DRAMA");
            year.setText(1930 + position + "");
            return rowView;
        }

    }
    
}