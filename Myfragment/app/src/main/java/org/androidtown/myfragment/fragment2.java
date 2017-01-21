package org.androidtown.myfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by InKyung on 2017-01-20.
 */

public class fragment2 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //인플레이션을 시켜준다
         ViewGroup viewgroup=(ViewGroup)inflater.inflate(R.layout.fragment2,container,false) ;
        //viewgoup이 가장 최상위 레이아웃
        return viewgroup ;

    }
}
