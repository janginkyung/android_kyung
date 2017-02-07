package org.androidtown.mygraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Animation grow ;
    ProgressBar bar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grow=AnimationUtils.loadAnimation(this, R.anim.grow) ;
        bar=(ProgressBar)findViewById(R.id.progressBar) ;
//startanimation이 아닌 방법으로 애니메이션을 실행시기는 방법
        bar.setAnimation(grow);//뷰에 애니메이션 객체가 할당된다.

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //액티비티가 화면에 보이게 되는 시점에 호출되는 함수

        Toast.makeText(this, "onWindowFocusChanged호출됨 / hasFocus:"+hasFocus,Toast.LENGTH_LONG).show();
        if(hasFocus){
            grow.start();
        }
        else{
            grow.reset();
        }
    }


}
