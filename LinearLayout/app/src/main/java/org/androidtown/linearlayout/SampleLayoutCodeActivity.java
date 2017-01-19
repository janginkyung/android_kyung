package org.androidtown.linearlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SampleLayoutCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout=new LinearLayout(this) ;
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                ,LinearLayout.LayoutParams.WRAP_CONTENT) ;
        Button button01=new Button(this) ;
        button01.setText("Butoon01");
        button01.setLayoutParams(params);
        mainLayout.addView(button01);


        setContentView(mainLayout);
    }
    public void onButton1Clicked(View v){setContentView(R.layout.normal);}
}
