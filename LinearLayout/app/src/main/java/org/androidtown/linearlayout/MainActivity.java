package org.androidtown.linearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButton1Clicked(View v){setContentView(R.layout.normal);}
    public void onButton2Clicked(View v){setContentView(R.layout.padding);}
    public void onButton3Clicked(View v){setContentView(R.layout.gravity);}
    public void onButton4Clicked(View v){setContentView(R.layout.weight);}
    public void onButton5Clicked(View v){setContentView(R.layout.baseline);}
    public void onButton6Clicked(View v){setContentView(R.layout.gravitytext01);}
    public void onButton7Clicked(View v){setContentView(R.layout.gravitytext02);}
    public void onButton8Clicked(View v){setContentView(R.layout.gravitytext03);}


}