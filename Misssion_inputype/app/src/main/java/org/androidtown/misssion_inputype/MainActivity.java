package org.androidtown.misssion_inputype;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Calendar c=Calendar.getInstance();
    int tyear=c.get(Calendar.YEAR) ;
    int tmonth=c.get(Calendar.MONTH)+1 ;
    int tday =c.get(Calendar.DAY_OF_MONTH) ;

    Button button1 ;

    EditText ed1,ed2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1=(EditText)findViewById(R.id.editText) ;
        ed2=(EditText)findViewById(R.id.editText2) ;
        ed2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        button1=(Button )findViewById(R.id.button) ;
        button1.setText(tyear+"년 "+tmonth+"월 "+tday+"일 ");

    }

    public void onButton1Clicked(View v){

        DatePickerDialog dialog=new DatePickerDialog(this,listener,tyear,tmonth,tday) ;
        dialog.show();
    }
    private DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month1, int dayOfMonth) {
            tyear=year;
            tmonth=month1+1;
            tday=dayOfMonth ;
            button1.setText(tyear+"년 "+tmonth+"월 "+tday+"일 ");
            Toast.makeText(getApplicationContext(), year+"년"+month1+1+"월"+dayOfMonth+"일",Toast.LENGTH_LONG).show();
        }
    } ;
public void onButton2Clicked(View v){
    Toast.makeText(getApplicationContext(), "이름:"+ed1.getText().toString()+" 나이:"+ed2.getText().toString()+"생년월일: "+tyear+"년 "+tmonth+"월 "+tday+"일 ",Toast.LENGTH_LONG).show();

}

}
