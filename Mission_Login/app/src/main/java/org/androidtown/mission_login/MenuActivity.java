package org.androidtown.mission_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        Button back1=(Button) findViewById(R.id.button2) ;
        Button back2=(Button) findViewById(R.id.button3) ;
        Button back3=(Button) findViewById(R.id.button4) ;

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result=new Intent( ) ;
                result.putExtra("message","고객관리") ;
                setResult(RESULT_OK,result);
                finish();
            }
        });
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result=new Intent( ) ;
                result.putExtra("message","매출관리") ;
                setResult(RESULT_OK,result);
                finish();
            }
        });
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result=new Intent( ) ;
                result.putExtra("message","매장관리") ;
                setResult(RESULT_OK,result);
                finish();
            }
        });

    }
}
