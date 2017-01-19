package org.androidtown.framelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView01;
    ImageView imageView02;
    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 첫번째 이미지 뷰
        imageView01 = (ImageView) findViewById(R.id.imageView01);

        // 두번째 이미지 뷰
        imageView02 = (ImageView) findViewById(R.id.imageView02);
    }

    public void onButton1Clicked(View v) {
        changeImage();
    }

    public void changeImage() {
        if (imageIndex == 0) {
            imageView01.setVisibility(View.VISIBLE);
            imageView02.setVisibility(View.INVISIBLE);

            imageIndex = 1;
        } else if (imageIndex == 1) {
            imageView01.setVisibility(View.INVISIBLE);
            imageView02.setVisibility(View.VISIBLE);

            imageIndex = 0;
        }
    }

}
