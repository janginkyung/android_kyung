package org.androidtown.mypager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.LineBackgroundSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ViewPager pager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager=(ViewPager)findViewById(R.id.pager) ;
        Adapter adapter=new Adapter(this) ;
        pager.setAdapter(adapter);

    }
    public void onButtonClicked(View v){
        pager.setCurrentItem(1);
    }
    //페이지 어뎁터를 상속해서 새로운 어뎁터를 생성한다
    class Adapter extends PagerAdapter{
        private Context mContext;
        public Adapter( Context context ) {
            mContext = context;
        }
        String[] names={"소녀시대","걸스데이","씨스타"} ;
        @Override
        public int getCount() {
            return names.length;//화면의 갯수
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //수에 맞는 화면을 생성한다.
            LinearLayout layout=new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            TextView view=new TextView(getApplicationContext()) ;
            view.setText(names[position]);//position은 0,1,2로 호출된다.저 함수 호출시에 배열의 인덱스값을 넘겨준다 .
            view.setTextSize(40.0f);
            layout.addView(view);
            container.addView(layout);//우리가 만든 레이아웃을 추가시킨다.
            return layout ;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(0);
        }
    }
}
