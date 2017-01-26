package org.androidtown.mywidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

/**
 * Created by InKyung on 2017-01-25.
 */

public class multicheck extends LinearLayout {
    public interface OnMultiChangeListner {
        public void onMultiChanged(boolean isFirst, boolean isSecond) ;
    }
    OnMultiChangeListner listner ;
    public void setOnMultiChangeListner(OnMultiChangeListner lsn){
        listner=lsn ;
    }


    CheckBox box1 ;
    CheckBox box2 ;
    public multicheck(Context context, AttributeSet attrs) {
        super(context, attrs);

        intit(context) ;
    }

    public multicheck(Context context) {
        super(context);
        intit(context) ;
    }
    private void intit(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        inflater.inflate(R.layout.multicheck,this,true) ;

        box1=(CheckBox)findViewById(R.id.checkBox);
        box2=(CheckBox)findViewById(R.id.checkBox2);
        //체크박스에서 체크를 하였는지 아닌지의 여부를 알수 있다.
        //체크박스가 위젯이라면 액티비티가 이벤트를 처리하게 되어있다.
        //안에서발생한 이벤트를 밖에서 처리할 수 있도록 도와줘야한다.
        box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(listner!=null){
                    listner.onMultiChanged(isChecked,box2.isChecked());
                }
            }
        });


    box2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(listner!=null){
                listner.onMultiChanged(box1.isChecked(),isChecked);
            }
        }
    });

}
}
