package org.androidtown.mycustomview;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by InKyung on 2017-01-26.
 */

public class myview extends View {
        Paint paint ;
    Bitmap mbitmap ;
    Canvas mcanvas ;
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
        paint.setAntiAlias(true) ;//그림을 부드럽게 그려준다.

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(w>0&&h>0){
            mbitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888) ;
            mcanvas =new Canvas() ;
            mcanvas.setBitmap(mbitmap);
            draw1();
        }

        super.onSizeChanged(w, h, oldw, oldh);
    }
private void draw1(){
    //ondraw에서 쓰던 함수를 그대로 쓰되 canvas->mcanvas
    //이유: 화면에 그리는 것이 아닌 메모리에서 그린다.
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.RED);
    mcanvas.drawRect(100,100,200,200,paint);//빨간색 사각형을 그리게 된다.


    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.MAGENTA);
    paint.setARGB(128,0,255,0);
    paint.setStrokeWidth(10.0f);
    mcanvas.drawRect(100,100,200,200,paint);//빨간색 사각형을 그리게 된다.

    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.BLUE);
    DashPathEffect effect=new DashPathEffect(new float[]{5,5}, 1) ;
    paint.setPathEffect(effect) ;
    //canvas.drawLine(100,300,500,300,paint);//페인트속성을 이용해서 라인을 그린다.
    mcanvas.drawLine(100,300,500,500,paint) ;//경사진 직선을 그린다

    //drawablw은 좌표를 저장한다.
    ShapeDrawable draw1=new ShapeDrawable() ;
    RectShape sh1=new RectShape();//사각형 객체를 선언한것
    sh1.resize(200,200);//가로 세로를 정의
    draw1.setShape(sh1);//사각형의 좌표값이 보관된 객체 draw1
    draw1.setBounds(300,100,500,300);//위치 지정
    draw1.draw(mcanvas);//캔버스객체를 이용해서 알아서 그려준다.

    //점차색깔이 변한다
    LinearGradient gra1=new LinearGradient(0,0,0,200,Color.RED,Color.YELLOW, Shader.TileMode.CLAMP) ;
    paint.setShader(gra1) ;

    sh1.resize(300,300);//가로 세로를 정의
    draw1.setShape(sh1);//사각형의 좌표값이 보관된 객체 draw1
    draw1.setBounds(400,300,700,600);//위치 지정
    draw1.draw(mcanvas);

}
    //화면에 그려지는 부분을 알수 있다.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mbitmap!=null){
            //뷰의 크기에 맞게 만들었으므로 바로 실행이 가능하다.
            canvas.drawBitmap(mbitmap,0,0,null);
        }
        /*
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(100,100,200,200,paint);//빨간색 사각형을 그리게 된다.


        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.MAGENTA);
        paint.setARGB(128,0,255,0);
        paint.setStrokeWidth(10.0f);
        canvas.drawRect(100,100,200,200,paint);//빨간색 사각형을 그리게 된다.

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        DashPathEffect effect=new DashPathEffect(new float[]{5,5}, 1) ;
        paint.setPathEffect(effect) ;
        //canvas.drawLine(100,300,500,300,paint);//페인트속성을 이용해서 라인을 그린다.
        canvas.drawLine(100,300,500,500,paint) ;//경사진 직선을 그린다

        //drawablw은 좌표를 저장한다.
        ShapeDrawable draw1=new ShapeDrawable() ;
        RectShape sh1=new RectShape();//사각형 객체를 선언한것
        sh1.resize(200,200);//가로 세로를 정의
        draw1.setShape(sh1);//사각형의 좌표값이 보관된 객체 draw1
        draw1.setBounds(300,100,500,300);//위치 지정
        draw1.draw(canvas);//캔버스객체를 이용해서 알아서 그려준다.

        //점차색깔이 변한다
        LinearGradient gra1=new LinearGradient(0,0,0,200,Color.RED,Color.YELLOW, Shader.TileMode.CLAMP) ;
        paint.setShader(gra1) ;

        sh1.resize(300,300);//가로 세로를 정의
        draw1.setShape(sh1);//사각형의 좌표값이 보관된 객체 draw1
        draw1.setBounds(400,300,700,600);//위치 지정
        draw1.draw(canvas);
*/
    }
}
