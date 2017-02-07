package org.androidtown.mission10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by InKyung on 2017-02-02.
 */

public class Info extends View {
    Context mcontext ;
    Paint paint ;
    Canvas canvas ;
    Bitmap mbitmap ;
    public Info(Context context) {
        super(context);
        init(context) ;
    }

    public Info(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context) ;
    }
    private void init(Context context){
        paint=new Paint() ;
        paint.setAntiAlias(true);
        mcontext=context ;
    }
}
