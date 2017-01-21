package org.androidtown.mydialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity {

    public void onButtonClicked(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this) ;
        builder.setTitle("안내") ;
        builder.setMessage("종료하시겠습니까?") ;
        builder.setIcon(android.R.drawable.ic_dialog_alert) ;

        //버튼지정,버튼눌렀을 때 동작을 설정가능
        builder.setPositiveButton("예",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "예 버튼이 눌렸습니다. ", Toast.LENGTH_LONG).show();

            }
        });
       AlertDialog dialog= builder.create();//대화상자의 객체가 만들어진다.
        dialog.show() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
