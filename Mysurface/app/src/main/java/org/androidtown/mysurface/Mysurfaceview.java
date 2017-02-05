package org.androidtown.mysurface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by InKyung on 2017-01-31.
 */

public class Mysurfaceview extends SurfaceView implements  SurfaceHolder.Callback{
    Context mcontext ;
    SurfaceHolder holder ;
    Paint paint ;
    public Mysurfaceview(Context context) {
        super(context);

        init(context) ;
    }

    public Mysurfaceview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context) ;
    }

    private void init(Context context){
        mcontext=context ;

        this.holder=getHolder() ;//홀더 객체 참조
        this.holder.addCallback(this);


        paint=new Paint() ;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        //메모리에서 없어지고 생성하는 것을 직접 관리해야함
        //implement를 해준다ㅣ
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Toast.makeText(mcontext, "ondraw호출",Toast.LENGTH_LONG).show();
        canvas.drawRect(100,100,200,200,paint);
        
    }

    public void dodraw(){
        Toast.makeText(mcontext, "dodraw호출",Toast.LENGTH_LONG).show();
        invalidate();

        }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Toast.makeText(mcontext, "surfaceChanged호출",Toast.LENGTH_LONG).show();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Toast.makeText(mcontext, "surfaceCreated호출",Toast.LENGTH_LONG).show();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Toast.makeText(mcontext, "surfaceDestroyed호출",Toast.LENGTH_LONG).show();

    }
}
