package org.androidtown.mythread3;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView01, textView02;
    EditText editText01, editText02;
    /**
     * 메인 스레드의 핸들러
     */
    MainHandler mainHandler;

    /**
     * 새로 만든 스레드
     */
    ProcessThread thread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainHandler = new MainHandler();
        thread1 = new ProcessThread();

        textView01 = (TextView) findViewById(R.id.textView01);
        textView02 = (TextView) findViewById(R.id.textView02);
        editText01 = (EditText) findViewById(R.id.editText01);
        editText02 = (EditText) findViewById(R.id.editText02);

        // 버튼 이벤트 처리
        Button processBtn = (Button) findViewById(R.id.processBtn);
        processBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String inStr = editText01.getText().toString();
                Message msgToSend = Message.obtain();
                msgToSend.obj = inStr;

                thread1.handler.sendMessage(msgToSend);
            }
        });

        thread1.start();

    }

    /**
     * 새로 정의한 스레드
     */
    class ProcessThread extends Thread {
        // 새로운 스레드를 위한 핸들러
        ProcessHandler handler;

        public ProcessThread() {
            handler = new ProcessHandler();
        }

        public void run() {
            // 루퍼 사용
            Looper.prepare();
            Looper.loop();
        }

    }

    class ProcessHandler extends Handler {
        public void handleMessage(Message msg) {
            Message resultMsg = Message.obtain();
            resultMsg.obj = msg.obj + " Mike!!!";

            mainHandler.sendMessage(resultMsg);
        }
    }

    class MainHandler extends Handler {
        public void handleMessage(Message msg) {
            String str = (String) msg.obj;
            editText02.setText(str);
        }
    }


}
    /*
    ProcessThread thread ;
    private static final String TAG="MainActivity" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread=new ProcessThread()  ;
        thread.start();
    }
    public void onButton1Clicked(View v){
        //메인쓰레드에서 다른 쓰레드 쪽으로 보낸다.
        thread.processHandler.post(new Runnable() {
            @Override
            public void run() {
              //prcoessthread안에서 작동하는 것이다.
                Log.d(TAG ,"메인쓰레드에서 새로운 스레드로 전달됨 :" ) ;
            }
        }) ;
    }
    class ProcessThread extends Thread{
        Handler processHandler =new Handler() ; //ProcessThread안에서 다룰수 있는 핸들러


        public void run(){
            Looper.prepare();
            Looper.loop();
        for(int i=0 ; i<100 ; i ++){
            Log.d(TAG ,"스레드 동작중 ") ;
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
        }
        public void ProcessThread(){

        }
    }
}
*/
