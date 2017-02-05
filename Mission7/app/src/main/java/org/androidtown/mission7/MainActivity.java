package org.androidtown.mission7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    CalendarMonthView monthView ;
    CalendarMonthAdapter monthviewAdapter ;
    TextView monthText ;
    int curyear ;
    int curMonth;
    EditText edit ;
    Button store ;

    HashMap<Integer,String > hashtable=new HashMap<Integer,String>(32) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit=(EditText)findViewById(R.id.editText) ;
        store=(Button)findViewById(R.id.button) ;

        curMonth=0 ;
        curyear=0 ;
        monthView=(CalendarMonthView)findViewById(R.id.monthView) ;
        monthviewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthviewAdapter);


        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
            @Override
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                MonthItem curitem=(MonthItem) monthviewAdapter.getItem(position) ;
                final int day=curitem.getDay() ;
                String fix=hashtable.get(day) ;
                edit.setText(fix);
                Toast.makeText(getApplicationContext(), day+"일이 선택",Toast.LENGTH_LONG).show();
                store.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sc= edit.getText().toString() ;
                        hashtable.put(day,sc) ;
                    }
                });
            }
        });

        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthviewAdapter.setPreviousMonth();
                monthviewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthviewAdapter.setNextMonth();
                monthviewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

    }

    private void setMonthText() {
        curyear = monthviewAdapter.getCurYear();
        curMonth = monthviewAdapter.getCurMonth();

        monthText.setText(curyear + "년 " + (curMonth+1) + "월");
    }

}
