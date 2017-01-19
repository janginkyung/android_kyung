package org.androidtown.relativelayout;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 첫번째 버튼을 눌렀을 때 normal.xml 에 정의된 화면 레이아웃을 보여줍니다.
     * @param v
     */
    public void onButton1Clicked(View v) {
        setContentView(R.layout.normal);
    }

    /**
     * 두번째 버튼을 눌렀을 때 overlay.xml 에 정의된 화면 레이아웃을 보여줍니다.
     * @param v
     */
    public void onButton2Clicked(View v) {
        setContentView(R.layout.overlay);
    }

    /**
     * 세번째 버튼을 눌렀을 때 centerfill.xml 에 정의된 화면 레이아웃을 보여줍니다.
     * @param v
     */
    public void onButton3Clicked(View v) {
        setContentView(R.layout.centerfill);
    }



}
