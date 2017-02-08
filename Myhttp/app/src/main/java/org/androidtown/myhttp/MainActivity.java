package org.androidtown.myhttp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText edit ;
    TextView text ;
    Handler handler=new Handler( ) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit=(EditText)findViewById(R.id.editText );
        text=(TextView)findViewById(R.id.textView) ;


    }
    public void onButtonClicked(View v){
            Requestthread thread=new Requestthread() ;
        thread.start() ;
    }

class Requestthread extends Thread{
    public void run(){
try{
    StringBuilder outputbuilder=new StringBuilder() ;
    String urlstr=edit.getText().toString() ;
    URL url=new URL(urlstr) ;
    HttpURLConnection con=(HttpURLConnection)url.openConnection() ;
    con.setDoInput(true);
    con.setDoOutput(true);
    //연결이 안도면 15초 후에 연결을 끊어버린다.
    con.setConnectTimeout(15000);

    int rescode=con.getResponseCode() ;
    if(rescode==HttpURLConnection.HTTP_OK) {
        //reader는 문자열
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String line = null;
        while (true) {
            line = reader.readLine();
            if (line == null) {
                break;
                   }
            outputbuilder.append(line+"\n") ;
        }
reader.close();
        con.disconnect();

    }
    String output=outputbuilder.toString() ;
    println(output);
}catch(Exception e){
    e.printStackTrace();
}

    }
}
private void println(final String data){
    handler.post(new Runnable() {
        @Override
        public void run() {
            text.append(data+"\n");
        }
    });

}
}
