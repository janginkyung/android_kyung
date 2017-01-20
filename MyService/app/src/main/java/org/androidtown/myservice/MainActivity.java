package org.androidtown.myservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButton1Clicked(View v){
        Intent intent=new Intent(getApplicationContext(), MyService.class) ;
        intent.putExtra("command","start") ; //onstartcommand로 넘어온다


        startService(intent) ;//시스템쪽으로 서비스를 시작시켜주세요



    }
}
