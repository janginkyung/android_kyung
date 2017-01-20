package org.androidtown.myintent;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent=getIntent() ;
        if(intent!=null){
            String title=intent.getStringExtra("title") ;
            Person person01=(Person)intent.getSerializableExtra("person") ;
            Toast.makeText(getApplicationContext(),"person: "+person01.getName(),Toast.LENGTH_LONG).show();
        }
    }

}
