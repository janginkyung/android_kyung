package org.androidtown.mission10;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;

import static org.androidtown.mission10.R.id.imageView;

public class MainActivity extends AppCompatActivity {
    public static int spacing = -45;
    CoverFlow coverFlow ;
    ImageView image;
    TextView name, date, info ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
image=(ImageView)findViewById(R.id.imageView) ;
        name=(TextView)findViewById(R.id.editText2) ;
        date=(TextView)findViewById(R.id.editText3) ;
        info=(TextView)findViewById(R.id.editText4) ;

        // 커버플로우에 어댑터 설정
        coverFlow = (CoverFlow) findViewById(R.id.coverflow);
        ImageAdapter coverImageAdapter = new ImageAdapter(this);
        coverFlow.setAdapter(coverImageAdapter);

        // 커버플로우에 속성 설정
        coverFlow.setSpacing(spacing);
        coverFlow.setSelection(2, true);
        coverFlow.setAnimationDuration(3000);

        coverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"item selected "+position ,Toast.LENGTH_LONG).show();
                showItem(position) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

private void showItem(int position){
    switch (position){
        case 0:
            image.setImageResource(R.drawable.item01);
            name.setText("브루클린의 소녀");
            date.setText("2005년");
            info.setText("수상한 여자아이들의 모험이 시작된다. ");
            break ;
        case 1 :
            image.setImageResource(R.drawable.item02);
            name.setText("당신의 완벽한 1년");
            date.setText("1994");
            info.setText("당신의 인생에 만족하시나요? 인생을 찾아 떠나는 주인공의 1년간의 여행이야기. ");
            break;
        case 2:
            image.setImageResource(R.drawable.item03);
            name.setText("당신 거기 있어줄래요?");
            date.setText("3015");
            info.setText("기욤 뮈소의 로맨스 . 상처많은 남자와 여자의 사랑이야기 ");
            break ;
        case 3:
            image.setImageResource(R.drawable.item04);
            name.setText("나미야 잡화점의 기적");
            date.setText("1876");
            info.setText("상처받은 이들이 우연히 들리게 되는 잡화점 .주인인 나미야는 이들을 치유하게 되는데..  ");
            break ;
    }
}
    /**
     * 어댑터 클래스 정의
     */
    public class ImageAdapter extends BaseAdapter {

        int itemBackground;
        private Context mContext;
        private FileInputStream outstream;

        private Integer[] mImageIds = { R.drawable.item01, R.drawable.item02,
                R.drawable.item03, R.drawable.item04, R.drawable.item05 };

        private ImageView[] mImages;

        public ImageAdapter(Context c) {
            mContext = c;
            mImages = new ImageView[mImageIds.length];
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView img = new ImageView(mContext);
            img.setImageResource(mImageIds[position]);
            img.setLayoutParams(new CoverFlow.LayoutParams(300, 140));
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);

            BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
            drawable.setAntiAlias(true);

            return img;
        }

        public float getScale(boolean focused, int offset) {
            return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
        }

    }

}
