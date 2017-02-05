package org.androidtown.mycustomview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
        LinearLayout layout ;
    myview view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout= (LinearLayout)findViewById(R.id.container) ;

        view=new myview(this) ;
        view.setBackgroundColor(Color.CYAN);
        layout.addView(view);
    }
}
