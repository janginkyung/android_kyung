package org.androidtown.mission9;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/**
 * Created by InKyung on 2017-02-02.
 */

public class Radio extends LinearLayout {
    RadioButton b1,b2,b3 ;

    public  interface OnRadioChangeListner{
        public void onMultiChanged(boolean f, boolean s, boolean t) ;
    }
    OnRadioChangeListner listner ;
    public void setOnRadioChangeListner(OnRadioChangeListner lsn){
        listner=lsn ;
    }
    public Radio(Context context) {
        super(context);
        init(context) ;
    }

    public Radio(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context) ;
    }
    private void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        inflater.inflate(R.layout.radio,this,true) ;

        b1=(RadioButton)findViewById(R.id.radio) ;
        b2=(RadioButton)findViewById(R.id.radio2) ;
        b3=(RadioButton)findViewById(R.id.radio3) ;

        b1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(listner!=null){
                    listner.onMultiChanged(true,false,false);
                    b2.setChecked(false);
                    b3.setChecked(false);

                }
            }
        });
        b2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(listner!=null){
                    listner.onMultiChanged(false,true,false);
                    b1.setChecked(false);
                    b3.setChecked(false);

                }
            }
        });
        b3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(listner!=null){
                    listner.onMultiChanged(false,false,true);
                    b2.setChecked(false);
                    b1.setChecked(false);

                }
            }
        });
    }
}
