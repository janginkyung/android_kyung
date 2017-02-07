package org.androidtown.myimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by InKyung on 2017-01-27.
 */

public class myview extends View {
    Paint paint;
    Bitmap mbitmap ;
    Canvas mcanvas ;
    Context mcontext ;
    public myview(Context context) {
        super(context);
        init(context) ;
    }

    public myview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context) ;
    }

    private void init(Context context){
        paint=new Paint() ;
paint.setAntiAlias(true);
mcontext=context ;

    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
       if(w>0&&h>0) {
           mbitmap =Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888) ;
           mcanvas=new Canvas() ;
           mcanvas.setBitmap(mbitmap);
            draw1();

       }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void draw1(){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        mcanvas.drawRect(100,100,200,200,paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.MAGENTA);
        DashPathEffect effect=new DashPathEffect(new float[]{5,5},1) ;
        paint.setPathEffect(effect) ;
        mcanvas.drawLine(100,300,500,500,paint);

        ShapeDrawable drawa=new ShapeDrawable() ;
        RectShape sh1=new RectShape() ;
        sh1.resize(200,200);
        drawa.setShape(sh1);
        drawa.setBounds(300,100,500,300);
        drawa.draw(mcanvas);

        LinearGradient gra=new LinearGradient(0,0,0,200,Color.RED,Color.YELLOW, Shader.TileMode.CLAMP) ;


        sh1.resize(300,300);
        drawa.setShape(sh1);
        drawa.setBounds(400,300,700,600);
        drawa.draw(mcanvas);

        Bitmap face=BitmapFactory.decodeResource(mcontext.getResources(),R.drawable.face) ;
        mcanvas.drawBitmap(face,500,500,paint);

        //매트릭스 방법 사용해서 그리는 방법
        Matrix matrix=new Matrix() ;
        matrix.setScale(1,-1);//확대 축소가 가능하다, 세로방향으로 반대가 된다

        //원래있던 비트맵을 이용해서 다시 그리는 경우
        //매트릭스 연산을 하게된다
        Bitmap nface=Bitmap.createBitmap(face,0,0,face.getWidth(),face.getHeight(),matrix,false);
        mcanvas.drawBitmap(nface,500,200,paint);


    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mbitmap!=null){
            //뷰의 크기에 맞게 만들었으므로 바로 실행이 가능하다.
            canvas.drawBitmap(mbitmap,0,0,null);
        }}
}
