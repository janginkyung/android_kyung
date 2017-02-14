package org.androidtown.myvideoplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    String url="http://sites.google.com/site/ubiaccessmobile/sample_video.mp4" ;
    VideoView video ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        video=(VideoView)findViewById(R.id.videoView) ;

        MediaController controller=new MediaController(this) ;
        video.setMediaController(controller);//동영상플레이할때 일시정지나 하는 것들이 바로 위에 표시된다.
       //비디오가 어떤 파일을 재생할지 지정하는 방법
        video.setVideoURI(Uri.parse(url));
        video.requestFocus();//비디오뷰쪽의 활동을 우선적으로 한다.
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "동영상 재생이 준비되었습니다. ", Toast.LENGTH_LONG).show();
            }
        });
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "동영상 재생이 완료되었습니다. ", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void onButtonClicked(View v){
        //비디오 재생하는 방법
        video.seekTo(0);
        video.start();
    }
}
