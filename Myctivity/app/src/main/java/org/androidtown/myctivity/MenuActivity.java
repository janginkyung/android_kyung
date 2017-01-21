package org.androidtown.myctivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button backbutton = (Button) findViewById(R.id.button2);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "티아라");
                setResult(RESULT_OK, intent);//정상인지아닌지 응답코드를 넣어준다.
                finish(); //첫화면이 새로운 화면아래있는것이므로 새로운 화면을 없애준다는 의미
            }
        });
    }

}
