package orgm.androidtown.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map ;
    LocationManager location ;
    Mylocationlistner listner ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프래그먼트는 액티비티위의 부분화면이므로 액티비티가 관리하고
        //프래그먼트매니저는 액티비티안에서 프래그먼트를 관리할수있게한다.
        SupportMapFragment frag = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        frag.getMapAsync(this);
        location=(LocationManager)getSystemService(Context.LOCATION_SERVICE) ;
        listner=new Mylocationlistner() ;



        requestmylocation();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap ;
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(location!=null){
            location.removeUpdates(listner);//위치정보가 갱신되지 않는다
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestmylocation() ;

    }

    public void requestmylocation(){
        //내위치 확인하기
        int mIntime=10000;//몇분간격으로 업데이트할지
        float mindistance=0 ;//몇미터움직였을때 업데이트를 한다

        //위치정보를 받을수 있게 해주는 listner



        location.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                mIntime, mindistance, listner);//gps를 이용해서 위치를 알아낸다.
        location.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                mIntime, mindistance, listner);//기지국을 바탕으로 위치를 알아낸다.
        Location lastlocation=location.getLastKnownLocation(LocationManager.GPS_PROVIDER);//알고있는 가장 최근의 위치를 알려준다.

        if(lastlocation!=null){
            Double latitude=lastlocation.getLatitude() ;
            Double longtitude=lastlocation.getLongitude() ;
            Log.d("Mainactivity","가장 최근의 내 위치 정보: "+latitude+", "+longtitude);
        }

    }

    class Mylocationlistner implements LocationListener {
        private void showCurrentmap(Double latitude, Double longtitude){
            //위치가 변화할때 지도에 표시하는 것
            LatLng curpoint=new LatLng(latitude,longtitude) ;
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(curpoint, 15) );
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);// 위성지도 등등 지도의 유형을 지정할 수 있다.

        }
        @Override
        public void onLocationChanged(Location location) {

            //로케이션 매니저가 위치정보알려줄때 사용하는 함수
            Double latitude=location.getLatitude() ;
            Double longtitude=location.getLongitude() ;
            Log.d("Mainactivity","내 위치 정보: "+latitude+", "+longtitude);

            showCurrentmap(latitude, longtitude);
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
