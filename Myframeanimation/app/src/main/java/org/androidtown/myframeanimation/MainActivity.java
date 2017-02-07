package org.androidtown.myframeanimation;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView image ;
    AnimationDrawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res=getResources() ;
        Drawable frame1=res.getDrawable(R.drawable.emo_im_crying) ;
        Drawable frame2=res.getDrawable(R.drawable.emo_im_happy) ;
        Drawable frame3=res.getDrawable(R.drawable.emo_im_laughing) ;
        Drawable frame4=res.getDrawable(R.drawable.emo_im_surprised) ;

        image=(ImageView)findViewById(R.id.imageView) ;
        int duration=500 ;
        drawable=new AnimationDrawable() ;

        //애니메이션의 한장면을 frame이라고 할수 있다.
        drawable.addFrame(frame1, duration);
        drawable.addFrame(frame2, duration);
        drawable.addFrame(frame3, duration);
        drawable.addFrame(frame4, duration);
        drawable.setOneShot(false);
    }
    public void onButton1Clicked(View v){
        //이미지 객첸느 drawavble이라는 애를 갖게 되고
        //drawable은 4개의 frame을 가지는 애이다.
        image.setBackground(drawable);

        drawable.setVisible(true ,true ) ;
        drawable.start();

    }
}
