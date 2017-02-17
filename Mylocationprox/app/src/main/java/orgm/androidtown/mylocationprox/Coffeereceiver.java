package orgm.androidtown.mylocationprox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Coffeereceiver extends BroadcastReceiver {
    public Coffeereceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //intent객체를 받을 때 처리하는 과정을 진행
        if (intent!=null){
            int id=intent.getIntExtra("id",0) ;
            double latitude=intent.getDoubleExtra("latitude" ,0.0D) ;
            double longtitude=intent.getDoubleExtra("longtitude" ,0.0D) ;

            Toast.makeText(context, "근접한 커피숍: "+id+","+latitude+" , "+longtitude,Toast.LENGTH_LONG).show();

        }
    }
}
