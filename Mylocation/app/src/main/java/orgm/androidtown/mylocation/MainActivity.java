package orgm.androidtown.mylocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text ;
    LocationManager location ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.textView) ;

    }
    public void onButton1Clicked(View v){
        //내위치 확인하기
        location=(LocationManager)getSystemService(Context.LOCATION_SERVICE) ;
        int mIntime=10000;//몇분간격으로 업데이트할지
        float mindistance=0 ;//몇미터움직였을때 업데이트를 한다

        Mylocationlistner listner=new Mylocationlistner() ;
        //위치정보를 받을수 있게 해주는 listner

        location.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                mIntime, mindistance, listner);//gps를 이용해서 위치를 알아낸다.
        location.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                mIntime, mindistance, listner);//기지국을 바탕으로 위치를 알아낸다.
        Location lastlocation=location.getLastKnownLocation(LocationManager.GPS_PROVIDER);//알고있는 가장 최근의 위치를 알려준다.

        if(lastlocation!=null){
            Double latitude=lastlocation.getLatitude() ;
            Double longtitude=lastlocation.getLongitude() ;
            text.setText("가장 최근의 내 위치 정보: "+latitude+", "+longtitude);
            text.invalidate();//text를 다시 쓰라는 함수
        }

     }

    class Mylocationlistner implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {

            //로케이션 매니저가 위치정보알려줄때 사용하는 함수
            Double latitude=location.getLatitude() ;
            Double longtitude=location.getLongitude() ;
            text.setText("내 위치 정보: "+latitude+", "+longtitude);
            text.invalidate();//text를 다시 쓰라는 함수
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
