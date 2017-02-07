package org.androidtown.mysocket;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView text ;
    Handler handler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView)findViewById(R.id.textView) ;
        handler=new Handler( ) ;
    }
    public void onButtonClicked(View v){
//쓰레드를 사용하기위해 복사한 코드를 run안에 넣어준다.
        ConnetThread thread=new ConnetThread() ;
        thread.start();
    }
private void println(final String data){
    handler.post(new Runnable() {
        @Override
        public void run() {
            text.append(data+"/n");
        }
    });

}

    class ConnetThread extends Thread{
        public void run(){
            //단말은 pc와 다른 단말기이므로 host부분을 고쳐야한다.
            String host="172.16.214.14" ;
            int port=5001 ;//서버와 같은 포트번호를 가져야 통신이 가능하다 .
            try {

                Socket socket=new Socket(host,port) ;
                println("서버로 연결되었습니다.  host/port : "+host+port);
                String output="hello " ;
                //서버쪽으로 보낼 정보
                ObjectOutputStream outstream=new ObjectOutputStream(socket.getOutputStream() );
                outstream.writeObject(output );
                outstream.flush(); //뭔가 써줄때는 꼭해야한다 .
                println("서버로 보낸 데이터 : "+output);
                //정보를 받는 경우
                ObjectInputStream instream=new ObjectInputStream(socket.getInputStream()) ;
                Object input=instream.readObject() ;
               println("서버로부터 받은 데이터 : "+input);

                instream.close();
                outstream.close();
                socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
