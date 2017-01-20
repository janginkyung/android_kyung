package org.androidtown.mylifecycle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"oncreate 호출됨 ", Toast.LENGTH_LONG).show();


    }

    @Override
    protected void onDestroy() {
        Toast.makeText(getApplicationContext(),"onDestroy 호출됨 ", Toast.LENGTH_LONG).show();

        super.onDestroy();
    }
    private  void saveScore(){
        //정보를 보기위한것 SharedPreferences
        SharedPreferences pref=getSharedPreferences("gosotop", Activity.MODE_PRIVATE) ;
        SharedPreferences.Editor editor=pref.edit() ;//에디터에 정보를 편집한다.
        editor.putInt("score",10000) ;
        editor.commit() ;//정보의 저장이 일어난다.
    }
    @Override
    protected void onPause() {
        Toast.makeText(getApplicationContext(),"onPause 호출됨 ", Toast.LENGTH_LONG).show();
        saveScore() ;

        super.onPause();
    }
    private  void loadScore(){
        SharedPreferences pref=getSharedPreferences("gosotop", Activity.MODE_PRIVATE) ;
        int score=pref.getInt("score",0) ;
        Toast.makeText(getApplicationContext(),"읽어온 값:"+score, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onResume() {
        Toast.makeText(getApplicationContext(),"onResume 호출됨 ", Toast.LENGTH_LONG).show();
        loadScore() ;
        super.onResume();
    }
    protected void onStart(){
        Toast.makeText(getApplicationContext(),"onStart 호출됨 ", Toast.LENGTH_LONG).show();

        super.onStart();
    }
    @Override
    protected void onStop() {
        Toast.makeText(getApplicationContext(),"onStop 호출됨 ", Toast.LENGTH_LONG).show();

        super.onStop();
    }
}
