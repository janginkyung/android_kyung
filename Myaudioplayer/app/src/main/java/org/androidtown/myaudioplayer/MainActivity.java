package org.androidtown.myaudioplayer;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //String url="http://sites.google.com/site/ubiaccessmobile/sample_audio.amr" ;
    String path="/sdcard/recorded.mp4" ;

    MediaPlayer player ;
    MediaRecorder recorder ;
int position ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButton1Clicked(View v) {
        Log.d("mainactivity","시작버튼클릭됨") ;

        killplayer() ;
        player = new MediaPlayer();
        try {
            player.setDataSource(path);//url은 path를 받을수있다.
            player.prepare();//준비하는 과정이 필요하다.
            player.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "재생버튼이 클릭되었습니다. ",Toast.LENGTH_LONG).show();
    }
    private void killplayer(){
        Log.d("mainactivity","일시정지버튼클릭됨") ;

        if(player!=null){
            player.release();//리소스를 해제하는 메서드
            player=null ;
            Toast.makeText(getApplicationContext(), "일시정지버튼이 클릭되었습니다. ",Toast.LENGTH_LONG).show();

        }

    }
    public void onButton2Clicked(View v){

        if(player!=null&&player.isPlaying()){
            position=player.getCurrentPosition() ;
            player.pause();
        }
    }
    public void onButton3Clicked(View v){
        Log.d("mainactivity","종료버튼클릭됨") ;

//일시정지한다음 그 위치부터 다시 재생해야한다.
        if(player!=null&&!player.isPlaying()){
            player.start();
            player.seekTo(position);
        }

        Toast.makeText(getApplicationContext(), "재생을 재시작합니다. ",Toast.LENGTH_LONG).show();

    }


    public void onButton4Clicked(View v){
        Log.d("mainactivity","종료버튼클릭됨") ;
if(player!=null&&player.isPlaying()){
    player.stop();
}
        Toast.makeText(getApplicationContext(), "재생을 중지합니다. ",Toast.LENGTH_LONG).show();
    }
    public void onButton5Clicked(View v){
        try{
            if (recorder != null) {
                recorder.stop();
                recorder.release();
                recorder=null ;
            }
        recorder=new MediaRecorder() ;
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//음성이 마이크로 들어가므로
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);


        //아웃풋파일지정
        recorder.setOutputFile(path);
        //녹음을 시작하는거
        recorder.prepare();
        recorder.start();
            Toast.makeText(getApplicationContext(), "녹음을 시작합니다. ",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){e.printStackTrace();}
    }

    public void onButton6Clicked(View v){
    if(recorder!=null){
        Toast.makeText(getApplicationContext(), "녹음을 중지합니다. ",Toast.LENGTH_LONG).show();
        recorder.stop();
        recorder.release();
        recorder=null ;
    }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        killplayer();//앱이 종료됬을때도 리소스를 없앤다음에 종료를 하게된다. s
    }
}
