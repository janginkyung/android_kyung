package org.androidtown.threadanimation;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by InKyung on 2017-02-05.
 */

public class Threadanim extends ImageView {
    Context mcontext;
    Handler handler=new Handler() ;
    int[] imageId = {R.drawable.emo_im_crying, R.drawable.emo_im_happy, R.drawable.emo_im_laughing, R.drawable.emo_im_surprised};//리소스 아이디는 정수형으로 되어있다.

    public Threadanim(Context context) {
        super(context);
        init(context);
    }

    public Threadanim(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);


    }

    private void init(Context context) {
        mcontext = context;
        //애니메이션을 동작시키도록 만들어야한다.
        ImageThread thread = new ImageThread();
        thread.start();

        this.setImageResource(R.drawable.emo_im_crying);
    }
    int index ;
    class ImageThread extends Thread {
        boolean running = false;
        int interval=800 ;
        public void run() {
            running = true;
            index = 0;

            while (true) {
                //메인 UI부분에 접근하므로 handler가 필요하다.
                //이런 경우에 메인 쓰레드와 새로된 쓰레드의 동시접근이 아니라 순서대로 처리할 수 있게 도와준다.

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //이미지 배열안에 잇는 아이디가 index가 가리키는 애로 변경됨
                        setImageResource(imageId[index]);
                        invalidate(); //그래픽을 다시 그려주는 역할
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

    }
}
