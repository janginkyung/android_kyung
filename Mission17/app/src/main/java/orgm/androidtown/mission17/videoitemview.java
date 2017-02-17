package orgm.androidtown.mission17;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by InKyung on 2017-02-17.
 */

public class videoitemview extends LinearLayout {
    ImageView image;
    TextView text1, text2;

    public videoitemview(Context context) {
        super(context);
        init(context ) ;
    }

    public videoitemview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context ) ;
    }

    private void init(Context context ){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        inflater.inflate(R.layout.listitem,this, true) ;

        image=(ImageView)findViewById(R.id.imageView) ;
        text1=(TextView)findViewById(R.id.textView) ;
        text2=(TextView)findViewById(R.id.textView2) ;
    }
    public void setname(String name){
        text1.setText(name);
    }
    public void setdate(String date){
        text2.setText(date);
    }

    public TextView getT2() {
        return text2;
    }

    public TextView getT1() {
        return text1;
    }

}
