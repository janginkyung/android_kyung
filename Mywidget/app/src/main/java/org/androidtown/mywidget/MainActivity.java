package org.androidtown.mywidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    multicheck check ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check=(multicheck)findViewById(R.id.multicheck) ;
        check.setOnMultiChangeListner(new multicheck.OnMultiChangeListner() {
            @Override
            public void onMultiChanged(boolean isFirst, boolean isSecond) {
                Toast.makeText(getApplicationContext(),"첫번째 체크박스 선택 "+isFirst+" 두번째 체크박스 선택 "+isSecond,Toast.LENGTH_LONG).show();

            }
        });
    }
}
