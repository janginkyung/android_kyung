package orgm.androidtown.mission17;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by InKyung on 2017-02-17.
 */

public class secind extends AppCompatActivity {
    VideoView video ;
    String VIDEO_PATH="/storage/extSdCard/DCIM/Camera/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplay);
        video=(VideoView)findViewById(R.id.videoView) ;

        Intent intent=getIntent() ;
        String url=intent.getExtras().getString("url" ) ;
        MediaController controller = new MediaController(this);
        video.setMediaController(controller);//동영상플레이할때 일시정지나 하는 것들이 바로 위에 표시된다.
        //비디오가 어떤 파일을 재생할지 지정하는 방법
        video.setVideoURI(Uri.parse(VIDEO_PATH+url));
        video.requestFocus();//비디오뷰쪽의 활동을 우선적으로 한다.

        Log.d("secind",url) ;
        try {

            video.seekTo(0);	//�������� ù�κ�����
            video.start();		//�������
        } catch (Exception e) {

            e.printStackTrace();
        }
        Log.d("secind","secind시작") ;
    }

}
