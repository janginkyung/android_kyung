package org.androidtown.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private  static final String TAG ="Myserive" ;
    public MyService() {
    }

    @Override
    public void onCreate() {
        //Log.d디버깅 메세지를 찍어준다,
        Log.d(TAG, "oncreate()호출됨") ;
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()호출됨") ;

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()호출됨") ;
        if(intent!=null){
            String command=intent.getStringExtra("command") ;
            if(command!=null){
                if(command.equals("start")){
                    PrintThread thread=new PrintThread() ;
                    thread.start() ;//run을 호출한다는것과 start사용같음
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
class PrintThread extends Thread{
    public void run(){
        for(int i=0 ; i<100 ; i++){
            Log.d(TAG,"#"+i+"서비스에서 반복됨 " ) ;
            try {
                Thread.sleep(500);
            }
            catch(Exception e){e.printStackTrace(); }
        }
    }
}
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
