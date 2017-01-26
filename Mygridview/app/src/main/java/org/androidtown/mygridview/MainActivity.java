package org.androidtown.mygridview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView grid=(GridView)findViewById(R.id.gridview);
        Singerapadter adapter=new Singerapadter() ;
        grid.setAdapter(adapter);
    }

    class Singerapadter extends BaseAdapter{

        String[] names={"소녀시대","티아라","레드벨벳"} ;
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view=new TextView(getApplicationContext()) ;
            view.setText(names[position]) ;
            view.setTextSize(50.0f);
            view.setTextColor(Color.BLACK);

            return view;
        }
    }
}
