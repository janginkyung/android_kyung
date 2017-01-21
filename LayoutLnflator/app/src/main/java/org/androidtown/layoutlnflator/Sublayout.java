package org.androidtown.layoutlnflator;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
//리니어 레이아웃을 상속한 서브레이아웃을 만들수있고
//리니어레이아웃을 최상위레이아웃으로 가지고있는 sublayout와 Sublauot이 한쌍이 된다
public class Sublayout extends LinearLayout {



    //뷰를 상속해서 받는 경우에는 생성자를 2개는 기본으로 가져야한다
    //코드상에서 버튼을 추가하는 경우
    public Sublayout(Context context) {
        super(context);
        init(context) ;
    }


    //xml에서 버튼을 추가하는 경우, attributeset이 xml의 속성을 이용한것
    public Sublayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context) ;
    }
    private void init(Context context){
        //초기화하는 과정 , 인플레이션을 하는 작업
       LayoutInflater inflater= context.getSystemService(context.LAYOUT_INFLATER_SERVICE)
       //sublayout을 여기 java소스에 붙인다는 의미이다. true이면 항상 붙음
        inflater.inflate( R.layout.sublayout,this,true ) ;
    }


    private GoogleApiClient client;

    public void onButtonClicked(View v) {
        //자바소스와 xml을 하나로 묶었으므로 객체만드는거 가능하다.
      Sublayout layout1=new Sublayout(this) ;
        LinearLayout container =(LinearLayout) findViewById(R.id.container) ;
        container.addView(layout1);//레이아웃을 집어넣어준다는 의미
    }


}
