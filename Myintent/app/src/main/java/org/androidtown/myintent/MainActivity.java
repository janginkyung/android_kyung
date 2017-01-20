package org.androidtown.myintent;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClicked(View v){
        //Intent intent=new Intent(getApplicationContext(),MenuActivity.class) ;
        //startActivityForResult(intent,1001);

        //화면을 띄우는 또다른방법
        Intent intent=new Intent() ;
        //동적으로 처리하고 싶을 때 사용이 가능하다.
        ComponentName name=new ComponentName("org.androidtown.myintent","org.androidtown.myintent.MenuActivity") ;
        intent.setComponent(name) ;
        intent.putExtra("title","소녀시대") ;

        Person person01=new Person("걸스데이",21) ;
        intent.putExtra("person",person01) ;
        startActivityForResult(intent, 1001);

    }
}
