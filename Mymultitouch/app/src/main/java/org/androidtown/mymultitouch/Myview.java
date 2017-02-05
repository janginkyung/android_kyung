package org.androidtown.mymultitouch;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by InKyung on 2017-01-26.
 */

public class Myview extends View {
    private static final String TAG="Myview" ;
    float x1;
    float y1 ;
    float x2;
    float y2 ;

    float oldx1 ;
    float oldy1 ;
    float oldx2 ;
    float oldy2 ;

    float diffx1 ;
    float diffy1 ;
    public Myview(Context context) {
        super(context);

        init(context) ;
    }

    public Myview(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context) ;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action =event.getAction() ;
        if(action==MotionEvent.ACTION_DOWN)//손가락이 눌렸을 때
        {
            int finger=event.getPointerCount() ;

            x1=event.getX(0) ;
            y1=event.getY(0) ;
            if(finger>1){
            x2=event.getX(1) ;
            y2=event.getY(1) ;}
            Log.d(TAG, "손가락이 눌렸습니다.: "+finger+","+x1+","+y1+","+x2+","+y2) ;
        }
        else if(action==MotionEvent.ACTION_MOVE){
            int finger=event.getPointerCount() ;
            x1=event.getX(0) ;
            y1=event.getY(0) ;
            if(finger>1){
                x2=event.getX(1) ;
                y2=event.getY(1) ;}
            Log.d(TAG, "손가락이 움직였습다.: "+finger+","+x1+","+y1+","+x2+","+y2) ;

           //diff 를 이용해서 이미지를 그리도록 한다.

            diffx1=x1 ;
            diffy1=y1 ;

            redraw();//ondraw가 자동으로 호출
        }
        else if(action==MotionEvent.ACTION_UP){
            int finger=event.getPointerCount() ;
            x1=event.getX(0) ;
            y1=event.getY(0) ;
            if(finger>1){
                x2=event.getX(1) ;
                y2=event.getY(1) ;}
            Log.d(TAG, "손가락이 떼졌습니다.: "+finger+","+x1+","+y1+","+x2+","+y2) ;
        }

        oldx1=x1 ;
        oldy1=y1 ;
        oldx2=x2 ;
        oldy2=y2 ;
        return true ;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(w>0&&h>0){
            newImage(w,h) ;
            //그린이미지를 보여주는 단계
            redraw() ;
        }
    }

    private void newImage(int a, int b){
        //일반적으로 사용할 수 있는 이미지 객체가 만들어지고 메모리 상에 만들어지는 이미지 객체: bitmap
        mbitmap=Bitmap.createBitmap(a,b, Bitmap.Config.ARGB_8888) ;
        mcanvas=new Canvas() ;//그리고 싶을 떄 사용
mcanvas.setBitmap(mbitmap);//bitmap에 뭔가 그려주는게 가능하다.


    }
    private void redraw(){
        //하얀색으로 그려주고 싶다고 하면 색상을 설정이 가능하다.
        mcanvas.drawColor(Color.WHITE);
        //그림을 그리기 위해서는 paint의 값이 필요/ 손에 따라 그림이 움직이기 위해 차이만큼을 넣어준다.
        mcanvas.drawBitmap(bitmap, diffx1,diffy1,paint);
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {//화면상에 그래픽이 그려지는 단계
        if (mbitmap!=null){
            canvas.drawBitmap(mbitmap,0,0,null);
        }
        super.onDraw(canvas);
    }
    Bitmap mbitmap ;//메모리상의 이미지
    Paint paint ;
    Canvas mcanvas ;//메모리상에서 처리하는 캔버스
    Bitmap bitmap ;
    private void init(Context context){
        //resource폴더에 접근할수 있도록 만들어줌
        Resources res=context.getResources() ;
        //메모리에서 사용할 수 있는 비트맵객체가나온다
        bitmap=BitmapFactory.decodeResource(res,R.drawable.beach) ;
        //그리는건 onsizechanged일때 그려준다 , 메모리에 화면과 똑같은 이미지로 만든후에 그려줘야 속도가 빨라진다.

        paint=new Paint() ;//객체 생성



    }
}
