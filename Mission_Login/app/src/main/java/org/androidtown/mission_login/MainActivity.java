package org.androidtown.mission_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ANOTHER=1001 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v){
        Intent intent=new Intent(getApplicationContext(), MenuActivity.class) ;
        startActivityForResult(intent, REQUEST_CODE_ANOTHER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_ANOTHER){
            Toast toast=Toast.makeText(getApplicationContext(),"on activity result method가 호출됨"+requestCode+"결과코드: "+resultCode,Toast.LENGTH_LONG) ;
            toast.show();

            if(resultCode==RESULT_OK){
                String message=data.getExtras().getString("message" ) ;
                toast=Toast.makeText(getApplicationContext(), "응답"+message, Toast.LENGTH_LONG) ;
                toast.show();
            }
        }

    }
}
