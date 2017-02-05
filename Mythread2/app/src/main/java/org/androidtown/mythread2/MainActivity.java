package org.androidtown.mythread2;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView textView01;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView01 = (TextView) findViewById(R.id.textView01);

        // 버튼 이벤트 처리
        Button requestBtn = (Button) findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                request();
            }
        });

    }

    /**
     * 요청 메소드
     */
    private void request() {
        String title = "원격 요청";
        String message = "데이터를 요청하시겠습니까?";
        String titleButtonYes = "예";
        String titleButtonNo = "아니오";

        android.app.AlertDialog dialog = makeRequestDialog(title, message, titleButtonYes, titleButtonNo);
        dialog.show();

        textView01.setText("원격 데이터 요청 중 ...");
    }

    /**
     * 요청 대화상자 만들기
     *
     * @param title
     * @param message
     * @param titleButtonYes
     * @param titleButtonNo
     * @return
     */
    private android.app.AlertDialog makeRequestDialog(CharSequence title, CharSequence message,
                                                      CharSequence titleButtonYes, CharSequence titleButtonNo) {

        android.app.AlertDialog.Builder requestDialog = new android.app.AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(message);
        requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {

                for (int k = 0; k < 10; k++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
                textView01.setText("원격 데이터 요청 완료.");
                //RequestHandler handler = new RequestHandler();
                //handler.sendEmptyMessageDelayed(0, 20);
            }
        });

        requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        return requestDialog.show();
    }

    /**
     * 요청 스레드
     *
     * @author michael
     */
    class RequestHandler extends Handler {
        public void handleMessage(Message msg) {
            for (int k = 0; k < 10; k++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
            textView01.setText("원격 데이터 요청 완료.");
        }
    }
}
/*
        text=(TextView)findViewById(R.id.textView) ;
    }
    public void onButtonClicked(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this) ;
        builder.setTitle("알림") ;
        builder.setMessage("데이터를 저장하시겠습니까?" ) ;
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //쓰레스드를 생성한다.
                Request thread=new Request() ;
                thread.start(); // 실제 호출되는 건 run이다.
            }
        }) ;
        AlertDialog dialog=builder.create() ;
        dialog.show();

    }
    class Request extends  Thread{
        @Override
        public void run() {
            for(int i=0 ; i <100 ; i++){
                println("#"+i+"번째 호출됨") ;

                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        public void println(final  String data){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    text.setText(data);
                }
            },1000) ;
        }
    }
}
*/