package org.androidtown.mypaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.CpuUsageInfo;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by InKyung on 2017-01-27.
 */

public class Paintboard extends View {
    Paint paint ;
    Bitmap mbitmpa ;
    Canvas mcanvas;
    Context mcontext ;

    Path path ;

    //좌표를 저장하는 변수
    float oldx ,oldy ;
    float x, y ;
    public Paintboard(Context context) {
        super(context);
        init(context) ;
    }

    public Paintboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context) ;
    }

    private void init(Context context){
        paint=new Paint( ) ;
        path=new Path() ;
        mcontext=context ;
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20.0f);//글자 크기를 크게 해준다
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mbitmpa!=null){
            canvas.drawBitmap(mbitmpa,0,0,null);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //bitmapfactory:외부에있는 사진이미지를 비트맵으로 사용하는 경우
        //bitmap은 객체를 만들때
        if(w>0&&h>0) {
            mbitmpa = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mcanvas=new Canvas() ;
            mcanvas.setBitmap(mbitmpa);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction() ;
        x=event.getX() ;
        y=event.getY() ;


        if(action==MotionEvent.ACTION_DOWN){
            path.moveTo(x,y);//눌러진 곳으로 이동한다

            //이동할 때는 이동위치까지 그려준다
        }
        else if(action==MotionEvent.ACTION_MOVE){
            //뷰위에있는 캔버스가 아니라 메모리 위에있는 캔버스이다
           // mcanvas.drawLine(oldx,oldy,x,y,paint);


            float cx=(x+oldx)/2 ;
            float cy=(y+oldy)/2 ;
            //부드러운곡선을 그려주는 메서드로 전좌표부터 지금좌표까지
                path.quadTo(oldx,oldy,cx,cy);

            mcanvas.drawPath(path,paint);
            //다시그려주기 위해서 invalidate() ;
        }
        else  if(action==MotionEvent.ACTION_UP){

        }
        invalidate() ;//영역이 유효하지 않으므로 ondraw를 다시 호출한다.
        oldx=x ;
        oldy=y ;
        return  true ;
    }
}
