package org.androidtown.myfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by InKyung on 2017-01-20.
 */

public class fragment1 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //인플레이션을 시켜준다
         ViewGroup viewgroup=(ViewGroup)inflater.inflate(R.layout.fragment1,container,false) ;
        //viewgoup이 가장 최상위 레이아웃

        //버튼객체를 참조한다.
        Button button=(Button)viewgroup.findViewById(R.id.button) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //프래그먼트로 전환을한다., getactivity:프래그먼트를 포함하는 액티비티를 알고싶다.
                //프래그먼트는 메소드를 만들고 호출하게되어있다.
                MainActivity activity=(MainActivity)getActivity() ;
                activity.onFragmentchanged(1);
            }
        });
        return viewgroup ;

    }
}
