package org.androidtown.myevent;

import android.content.res.Configuration;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView ;
    GestureDetector detector ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView) ;
        detector=new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                textView.setText("on scroll()호출됨 : "+distanceX+","+distanceY);

                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                textView.setText("onFling()호출됨 : "+velocityX+","+velocityY);

                return super.onFling(e1, e2, velocityX, velocityY);
            }
        }) ;
        final Window win=getWindow() ;
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(detector!=null ){
            //내가 오버라이드한 터치이벤트가 불려진다.
            return detector.onTouchEvent(event) ;
        }
        else{
        return super.onTouchEvent(event);}
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
    }




//키패드를 누르는 모든 동작
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        //휴대폰에서 돌아가기 버튼 눌르는거
        Toast.makeText(getApplicationContext(), "onbackpressed 호출됨 ",Toast.LENGTH_LONG).show();
        return  ;//백키를 눌러도 어떤 동작을 하지 않는 상황을 만들게 된다.
    }






    @Override
    //상태가 바뀌게 되면 정보가 날아온다
    public void onConfigurationChanged(Configuration newConfig) {
        if(newConfig.orientation== Configuration.ORIENTATION_LANDSCAPE)//가로방향
        {
            Toast.makeText(getApplicationContext(),"가로방향",Toast.LENGTH_LONG).show();
        }
        else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(getApplicationContext(),"세로방향",Toast.LENGTH_LONG).show();

        }
        super.onConfigurationChanged(newConfig);
    }
}
