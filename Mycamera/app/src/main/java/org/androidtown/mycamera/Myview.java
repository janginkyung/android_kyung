package org.androidtown.mycamera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by InKyung on 2017-02-01.
 */

public class Myview extends View {
    Bitmap mbitmap,water ;
    Canvas mcanvas ;
    Context mcontext ;
    Paint paint ;


    Camera camera=new Camera();
    public Myview(Context context) {
        super(context);
        init(context) ;
    }

    public Myview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context) ;
    }
    private void init(Context context){
    //이미지를 넣는 과정을 구현해야한다.
        mcontext=context ;
        paint=new Paint() ;
        water=BitmapFactory.decodeResource(mcontext.getResources(),R.drawable.waterdrop) ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//지금까지를 더블버퍼링 방식
        if (mbitmap != null) {
            canvas.drawBitmap(mbitmap,0,0,null);

        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
       if(w>0&&h>0)
       {
           mbitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mcanvas=new Canvas() ;
           mcanvas.setBitmap(mbitmap);

           mcanvas.drawColor(Color.WHITE);
           mcanvas.drawBitmap(water,0,0,paint);

           //카메라의 사용 ,
           camera.save();//이전정보를 담고 그다음부터 또 그린다.
           Matrix matrix=new Matrix() ;//이미지 변환에 사용되는 메트릭스
           camera.rotateY(45.0f);
           camera.translate(0,0,-500.0f);
           camera.getMatrix(matrix);//매트릭스 객체쪽에 변환하고 싶은 값이 들어간다.
           camera.restore();
           Bitmap rotate=Bitmap.createBitmap(water,0,0,water.getWidth(),water.getHeight(),matrix,true) ;
            mcanvas.drawBitmap(rotate,500,300,paint);
       }


        super.onSizeChanged(w, h, oldw, oldh);

    }
}
