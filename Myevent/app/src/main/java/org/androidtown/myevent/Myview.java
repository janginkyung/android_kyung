package org.androidtown.myevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by InKyung on 2017-01-23.
 */

public class Myview extends View {
    public Myview(Context context) {
        super(context);
    }

    public Myview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            Log.d("Myview","손가락이 눌렸습니다. ");
        }
        else if(event.getAction()==MotionEvent.ACTION_UP){
            Log.d("Myview","손가락이 떼졌습니다. ");
        }
        else if(event.getAction()==MotionEvent.ACTION_MOVE){
            Log.d("Myview","손가락이 움직입니다. . ");
        }
        return true ;
    }
}
