package org.androidtown.mission13;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static org.androidtown.mission13.R.layout.girdviewitem;

/**
 * 일자에 표시하는 텍스트뷰 정의
 * 
 * @author Mike
 */
public class MonthItemView extends LinearLayout {

	private MonthItem item;
	TextView text1 ,text2 ;
	ImageView iamge ;

    LinearLayout layout;


    public MonthItemView(Context context) {
		super(context);
		 
		init(context);
	}
	
	public MonthItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		 
		init(context);
	}

	private void init(Context context) {
        setBackgroundColor(Color.WHITE);
	//	LayoutInflater inflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) ;
        //inflater.inflate(girdviewitem, this, true);
        text1=(TextView)findViewById(R.id.textview1) ;
		text2=(TextView)findViewById(R.id.textview2) ;
		iamge=(ImageView)findViewById(R.id.imageView) ;


	}


    public MonthItem getItem() {
		return item;
	}

	public void setItem(MonthItem item) {
        this.item = item;
        String saveWeather = item.getWeather();
        int day = item.getDay();
        if (day != 0) {
            text1.setText(String.valueOf(day));
            getweatherImage(saveWeather);
            text2.setText(saveWeather);
        } else {
            text1.setText("");
        }
    }
        public void getweatherImage(String weather){



            if(weather==null){
                iamge.setVisibility(View.INVISIBLE);
            }else{
                iamge.setVisibility(View.VISIBLE);
                if(weather.toString().equals("맑음")){
                    iamge.setImageResource(R.drawable.clear);
                }else if(weather.toString().equals("구름조금")){
                    iamge.setImageResource(R.drawable.cloudy);
                }else if(weather.toString().equals("구름조금")){
                    iamge.setImageResource(R.drawable.partly_cloudy);
                }else if(weather.toString().equals("구름많음")){
                    iamge.setImageResource(R.drawable.mostly_cloudly);
                }else if(weather.toString().equals("흐리고 비")){
                    iamge.setImageResource(R.drawable.rain);
                }else if(weather.toString().equals("눈")){
                    iamge.setImageResource(R.drawable.snow);
                }else if(weather.toString().equals("눈/비")){
                    iamge.setImageResource(R.drawable.snow_rain);
                }else{
                    text2.setText("���������� ����");
                }
            }


        }


    public void setTextColor(int color){
        text1.setTextColor(color);

    }
	}
