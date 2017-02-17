package orgm.androidtown.mygeocoding;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText text ;
    Geocoder corder ;

    TextView textview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(EditText)findViewById(R.id.textView ) ;
        corder=new Geocoder(this, Locale.KOREAN) ;
        textview=(TextView)findViewById(R.id.textView2) ;
    }
    public void onButtonClicked(View v){
        String  address=text.getText().toString() ;

        //주소를 이용해 좌표를 얻는 방법, 동일 주소 3개까지 얻는다.
        try {
            List<Address> addresslist=corder.getFromLocationName(address, 3);
            if(addresslist!=null){
                for(int i=0 ; i<addresslist.size(); i++) {
                    Address curaddress = addresslist.get(i);
                    StringBuffer buffer=new StringBuffer() ;
                    for(int k=0 ; k<=curaddress.getMaxAddressLineIndex() ; k++){
                        buffer.append(curaddress.getAddressLine(k)) ;
                    }
                    buffer.append("\n\tlatitude: "+curaddress.getLatitude()) ;
                    buffer.append("\n\tlongtituud: "+curaddress.getLongitude()) ;

                    textview.append("\nAddress #:"+i+": "+buffer.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
