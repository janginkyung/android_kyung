package org.androidtown.myctivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void onButton1Clicked(View v){
        Intent intent=new Intent(getApplicationContext(), MenuActivity.class) ;
        //정보를 넘겨준다 title이란 이름이 menuactivity로 전달이 된다는거
        intent.putExtra("title","소녀시대") ;
        startActivityForResult(intent,1001);//숫자코드가 오는데 요청코드로 구분
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intent를 extra데이터를 가지고있음



    }

    //Override 응답을 받을수 있다
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK)
        {
            String name=data.getExtras().getString("name") ;
            Toast.makeText(getApplicationContext(),"전달밭은값 : "+name,Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode,resultCode,data);
    }


}
