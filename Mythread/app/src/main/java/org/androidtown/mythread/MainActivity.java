package org.androidtown.mythread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {
        private  static final String TAG="Mainactivity" ;
    TextView text ;
    Handler handler=new Handler() ;
   // ResponseHandler handler=new ResponseHandler() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView)findViewById(R.id.textView) ;
    }
    public void onButtonClicked(View v){
        Log.d(TAG,"첫번째 버튼 클림됨 ") ;

        //쓰레스드를 생성한다.
        Request thread=new Request() ;
        thread.start(); // 실제 호출되는 건 run이다.

    }
    class Request extends Thread{
      public void run(){
        for (int i=0;i <100 ; i++){
            println("#"+i+"번째 호출됨") ;

            try{
            Thread.sleep(500);
        }catch (Exception e){e.printStackTrace();
            }
        }
      }
        public void println(final String data){
            Log.d(TAG,data );
            //text.setText(data);앱이 중단된다. 이유는 쓰레드안에서
            //텍스트뷰라는 UI객체에 비정상적으로 접근하기 때문이다.

           /*
            Message message=handler.obtainMessage() ;
            Bundle bundle=new Bundle() ;
            bundle.putString("data",data);
            message.setData(bundle); //bundle: intent객체에서 부가데이터와 같은 방법

            handler.sendMessage(message) ;//메세지를 보내면 핸들러쪽으로 온다.

        */
            handler.postDelayed(new Runnable()//핸들러가 알아서 처리를 해줘서 메인 쓰레드에서
                //동작을 해준다. 그래서 메인 ui를 마음대로 접근이 가능하다.
            {
                @Override
                public void run() {
                    text.setText(data);
                }
            },10000);
        }
    }
    /*
    class ResponseHandler extends Handler{
        @Override
        public void handleMessage(Message msg)//핸들러로 넘어온것은 이 메소드를 통해 관리
        // 유아이를 접근가능한 메인 쓰레드 쪽이다
        {
            //아까 보냈던 정보를 받는다.
            Bundle bundle=msg.getData() ;
            String data=bundle.getString("data") ;
            text.setText(data);
            super.handleMessage(msg);
        }

    }
    */
}
