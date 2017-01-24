package org.androidtown.myanim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView) findViewById(R.id.textView);

    }
    public void onButtonClicked(View v){
        Animation translate=AnimationUtils.loadAnimation(this, R.anim.translate) ;
        //textView.startAnimation(translate);
        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(getApplication(), "애니메이션 시작됨 ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplication(), "애니메이션 종료됨 ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ViewGroup container=(ViewGroup)findViewById(R.id.activity_main) ;
        container.startAnimation(translate);


    }
}
