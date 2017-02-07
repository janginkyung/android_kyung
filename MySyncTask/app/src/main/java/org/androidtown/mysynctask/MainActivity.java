package org.androidtown.mysynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text ;
    ProgressBar bar ;
    Background task ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text= (TextView)findViewById(R.id.textView) ;
        bar=(ProgressBar)findViewById(R.id.progressBar) ;
    }
    //버튼을 클릭햇을때 쓰레드를 만들지 않고 async task를 만들어서 하는 방법을 본다

    public void OnButton1Clicked(View v){
        task=new Background() ;
        task.execute(100) ;//실행이 되기 시작한다.
    }
    public void OnButton2Clicked(View v){
        task.cancel(true) ;
    }

    int value=0 ;
    class Background extends AsyncTask<Integer,Integer, Integer>
    {
        @Override
        protected Integer doInBackground(Integer... params) {
           while (isCancelled()==false){
               value++ ;
               if(value>100){break ;}
               else{
                   publishProgress(value);
               }
               try {
                   Thread.sleep(200);
               }catch (Exception e){

               }
           }
            return value ;
        }

        @Override
        //ui를 접근할수 있으면서 마지막 단계
        protected void onPostExecute(Integer integer) {
            value=0 ;
            bar.setProgress(value);
            text.setText("중지됨");

        }

        @Override
        protected void onPreExecute() {
            value=0 ;
            bar.setProgress(value);
        }

        @Override
        //중간에 유아이를 변경하는 경우
        protected void onProgressUpdate(Integer... values) {
           bar.setProgress(values[0].intValue());
            text.setText("진행중"+values[0].toString());
        }
    }

}
