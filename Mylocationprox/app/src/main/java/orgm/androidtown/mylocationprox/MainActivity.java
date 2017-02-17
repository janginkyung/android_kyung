package orgm.androidtown.mylocationprox;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    TextView text2;

    LocationManager manger;
    ArrayList<PendingIntent> pendinglist = new ArrayList<PendingIntent>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);

        manger = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


    }

    public void onButton1Clicked(View v) {
        pendinglist.clear();//이전거를 없애준다.

        PendingIntent pintent = register(1001, 36.222222, 126.222222, 200, -1);
        if (pintent != null) {
            pendinglist.add(pintent);
        }
        pintent = register(1002, 38.222222, 128.222222, 200, -1);
        if (pintent != null) {
            pendinglist.add(pintent);
        }

        text1.setText("1001번 : 36.222222,126.222222,200");
        text2.setText("1002번 : 38.222222,128.222222,200");

        Toast.makeText(this, "두개의 지점을 등록", Toast.LENGTH_LONG).show();
    }

    public void onButton2Clicked(View v) {

        //해제하는 경우
        unregister();
        Toast.makeText(this, "등록해제됨", Toast.LENGTH_LONG).show();
    }

    private PendingIntent register(int id, double latitude, double longtitude, float radius, long expiration) {
        //근접정보를 등록받는다.
        Intent intent = new Intent("coffeeProximity");
        intent.putExtra("id", id);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longtitude", longtitude);

        //조건이 맞을 때 대기하고 있는 intent가 시스템에서 실행된다.
        //브로드캐스트수신자가 intent를 받게된다
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //내가 locationmanger에 정보를 보내는 메소드
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return pendingIntent;
        }
        manger.addProximityAlert(latitude, longtitude, radius, expiration, pendingIntent);
        return pendingIntent;
    }

    private void unregister() {
        //등록했던 위치를 해제해준다.
        for (int i = 0; i < pendinglist.size(); i++) {
            PendingIntent pintent = pendinglist.get(i);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            manger.removeProximityAlert(pintent);
        }
    }
}
