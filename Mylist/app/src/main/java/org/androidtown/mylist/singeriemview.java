package org.androidtown.mylist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by InKyung on 2017-01-25.
 */

public class singeriemview extends LinearLayout{
    TextView t1 ;
    TextView t2 ;
    public singeriemview(Context context) {
        super(context);
        init(context) ;
    }

    public singeriemview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context) ;
    }
    //xml레이아웃을 인플레이션 해서 붙여준다.
    private void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        inflater.inflate(R.layout.singer_item,this,true) ;//내가만든(this)레이아웃에 singer.xml을 바로 갖다붙인다(TRUE)
        t1=(TextView)findViewById(R.id.text1) ;
        t2=(TextView)findViewById(R.id.text2) ;

    }

    public void setname(String name){
        t1.setText(name);
    }
    public void setage(String age){
        t2.setText(age);
    }

    public TextView getT2() {
        return t2;
    }

    public TextView getT1() {
        return t1;
    }
}
