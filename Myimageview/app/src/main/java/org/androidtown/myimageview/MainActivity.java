package org.androidtown.myimageview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout=(LinearLayout)findViewById(R.id.container) ;
        View view=new myview(this) ;
        view.setBackgroundColor(Color.CYAN);
        layout.addView(view);
    }
}
