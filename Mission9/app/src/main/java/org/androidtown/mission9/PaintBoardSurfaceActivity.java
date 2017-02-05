package org.androidtown.mission9;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 서피스뷰를 이용하는 페인트보드를 보여주기 위한 액티비티
 * 
 * @author Mike
 *
 */
public class PaintBoardSurfaceActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        PaintBoardSurface board = new PaintBoardSurface(this);
        setContentView(board);
    }


}
