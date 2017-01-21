package org.androidtown.myfragment;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    fragment2 fragment2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //코드에서 프레그먼트를 전환하게 되고 액티비티처럼 매니저를 불러서 관리를 한다.
    public void onFragmentchanged(int num){
        if (fragment2==null){
            fragment2=new fragment2() ;
        }
        //begintansition:프래그먼트에 여러명령을 내릴수있게한다.
         getSupportFragmentManager().beginTransaction().replace(R.id.container1,fragment2).commit() ;
        //프레그먼트를 참조하는 방법
    }
}
