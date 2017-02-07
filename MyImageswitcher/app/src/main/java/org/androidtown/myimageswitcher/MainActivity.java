package org.androidtown.myimageswitcher;

import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    ImageSwitcher swticher ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swticher=(ImageSwitcher)findViewById(R.id.Swithcer) ;//이미지 스위쳐는 바뀌는 상황에서 애니메이션을 줄 수 있다.
        swticher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {//뷰스위처에서 리턴하는 뷰가 보여지게 된다.
                ImageView imageview=new ImageView(getApplicationContext()) ;
                return imageview ;
            }
        });//뭔가를 만들어준다는 의미이다
        Animation in= AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left) ;
        Animation out= AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right) ;

        swticher.setInAnimation(in);
        swticher.setInAnimation(out);//이미지 변환이 아니라 전환될때 애니메이션이 되도록 설정한 것
    }
    ImageThread thread ;


    public void onButton1Clicked(View v){
        thread=new ImageThread() ;
        thread.start();
    }
    public void onButton2Clicked(View v){
        if(thread!=null) {
            thread.halt();
            thread = null;
        } }
    int index =0;
    Handler handler=new Handler() ;
    int[] imageId = {R.drawable.emo_im_crying, R.drawable.emo_im_happy, R.drawable.emo_im_laughing, R.drawable.emo_im_surprised};
    class ImageThread extends Thread {
        boolean running = false;
        public void run() {
            running = true;
            while (true) {
                //메인 UI부분에 접근하므로 handler가 필요하다.
                //이런 경우에 메인 쓰레드와 새로된 쓰레드의 동시접근이 아니라 순서대로 처리할 수 있게 도와준다.

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //이미지 배열안에 잇는 아이디가 index가 가리키는 애로 변경됨
                        swticher.setImageResource(imageId[index]);
                        swticher.invalidate(); //그래픽을 다시 그려주는 역할
                    }
                });

                try {
                    Thread.sleep(800);
                } catch (Exception e) {

                }
                index++ ;
                if(index>3){
                    index=0 ;
                }
            }
        }
public void halt(){
    running=false ;
}
    }
}
