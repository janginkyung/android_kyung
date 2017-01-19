package org.androidtown.scrollview;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView01 ;
    ImageView imageView01 ;
    BitmapDrawable bitmap ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView01=(ScrollView)findViewById(R.id.scrollview);
        imageView01=(ImageView)findViewById(R.id.iamageview01);
        Button butoon01=(Button)findViewById(R.id.button01);


        scrollView01.setHorizontalScrollBarEnabled(true);

        Resources res=getResources() ;
        bitmap=(BitmapDrawable)res.getDrawable(R.drawable.image01);
        int bitmapWidth=bitmap.getIntrinsicWidth() ;
        int bitmapHeight=bitmap.getIntrinsicHeight() ;

        imageView01.setImageDrawable(bitmap);
        imageView01.getLayoutParams().width=bitmapWidth;
        imageView01.getLayoutParams().height=bitmapHeight;
    }

    public void onButtonClicked(View v){

        changeImage() ;
    }
    private void changeImage(){
        Resources res=getResources() ;
        bitmap=(BitmapDrawable)res.getDrawable(R.drawable.image02) ;
        int bitmapWidth=bitmap.getIntrinsicWidth() ;
        int bitmapHeight=bitmap.getIntrinsicHeight() ;


        imageView01.setImageDrawable(bitmap);
        imageView01.getLayoutParams().width=bitmapWidth;
        imageView01.getLayoutParams().height=bitmapHeight;
    }

}
