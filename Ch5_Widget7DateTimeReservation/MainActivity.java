package com.example.ch5_widget7datetimereservation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    //1. 필드변수 선언
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
    CalendarView calView;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;

    int selectYear,selectDay,selectMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("시간예약");

        btnStart = (Button)findViewById(R.id.btnStart) ;
        btnEnd =(Button)findViewById(R.id.btnEnd);

        chrono = (Chronometer)findViewById(R.id.chronometer1);

        rdoCal = (RadioButton)findViewById(R.id.rdoCal);
        rdoTime= (RadioButton)findViewById(R.id.rdoTime);

        calView=(CalendarView)findViewById(R.id.calendarView1);
        tPicker=(TimePicker)findViewById(R.id.timePicker1);

        tvYear = (TextView)findViewById(R.id.tvYear);
        tvMonth= (TextView)findViewById(R.id.tvMonth);
        tvDay  = (TextView)findViewById(R.id.tvDay);
        tvHour =(TextView)findViewById(R.id.tvHour);
        tvMinute=(TextView)findViewById(R.id.tvMinute);

        tPicker.setVisibility(View.INVISIBLE);  // 타임피커를 보이지 않게 초기화
        calView.setVisibility(View.INVISIBLE);  // 캘린더뷰를 보이지 않게 초기화

        //캘린더 라디오버튼에 리스너 설정
        rdoCal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);//시간가려주고
                calView.setVisibility(View.VISIBLE);//달력은보여주고
            }
        });
        //타임 라디오버튼에 리스너 설정
        rdoTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE); // 시간보여주고
                calView.setVisibility(View.INVISIBLE); //달력 가려주고
            }
        });

        //예약시작버튼에 리스너 설정
        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime()); //예약시간을 누르면 크로노미터를 0으로 초기화
                chrono.start(); // 예약을 시작 --> 크로노미터를 시작한다.
                chrono.setTextColor(Color.RED); //글자색상 지정
            }
        });

        //예약완료버튼 리스너설정
        btnEnd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);

                String yy = (String)Integer.toString(selectYear);
                String mm = (String)Integer.toString(selectMonth);
                //예약완료옆에 년월일시분 을  출력해주는 문장 --> 정수타입의 select~필드를 문자로 형변환후 출력한다
                tvYear.setText(yy);
                tvMonth.setText(mm);
                tvDay.setText(Integer.toString(selectDay));

                tvHour.setText(Integer.toString(tPicker.getCurrentHour()));  // 타임피커에서 현재시간을 가져온다.
                tvMinute.setText(Integer.toString(tPicker.getCurrentMinute())); //타임피커에서 현재분을 가져온다
            }
        });
        //캘린더뷰에 날짜설정
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month+1;
                selectDay= dayOfMonth;
            }
        });
    }
}