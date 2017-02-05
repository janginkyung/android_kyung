package org.androidtown.mysurface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Mysurfaceview view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view=(Mysurfaceview)findViewById(R.id.view );
    }

    public void onButton1Clicked(View v){
        view.dodraw();
    }
}
