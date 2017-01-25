package org.androidtown.mypagesliding;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout sliding;
    Button button;
    Animation translate_left;
    Animation translate_right;
    boolean click ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        click=false ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translate_left=AnimationUtils.loadAnimation(this, R.anim.translate) ;
        translate_right=AnimationUtils.loadAnimation(this, R.anim.translate_right) ;

        translate_left.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(click==false){
                button.setText("닫기");
                    click=true;
                }
                else{
                    button.setText("열기");
                    sliding.setVisibility(View.INVISIBLE);
                    click=false;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
         sliding=(LinearLayout)findViewById(R.id.sliding) ;
         button =(Button)findViewById(R.id.button) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click==false){
                sliding.setVisibility(View.VISIBLE);
                sliding.startAnimation(translate_left);}
                else{
                    sliding.startAnimation(translate_right);
                }



            }
        });
    }
}
