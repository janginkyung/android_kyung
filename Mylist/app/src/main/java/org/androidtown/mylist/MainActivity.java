package org.androidtown.mylist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView list ;
    Singeradapter adapter;
    String[] names={"소녀시대", "걸스데이","씨스타"} ;
    String[] ages={"22", "23","24"} ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=(ListView)findViewById(R.id.listview) ;

        adapter=new Singeradapter() ;
        adapter.additem(new Singeritem(names[0],ages[0]));
        adapter.additem(new Singeritem(names[1],ages[1]));
        adapter.additem(new Singeritem(names[2],ages[2]));

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Singeritem item=(Singeritem)adapter.getItem(position) ;
                String name=item.getName() ;
                Toast.makeText(getApplicationContext(),name+"을 선택하셨습니다. ",Toast.LENGTH_LONG).show() ;
            }
        });
    }

    class Singeradapter extends BaseAdapter{
        ArrayList<Singeritem> items=new ArrayList<Singeritem>() ;
        //아래 4개 함수는 자동으로 불리운다..
        @Override
        public int getCount() {
            return items.size();
        }
        public  void additem(Singeritem item){
            items.add(item);
        }
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        //아이템->뷰가 이루어진다./convertView는 용량이 너무 많아지는 경우 리스트뷰
        //차원에서 아이콘이나 이미지를 재사용한다는 의미
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            /* 1 리스트뷰 시에 사용
            TextView view=new TextView(getApplicationContext()) ;
            view.setText(names[position]);
            view.setTextSize(50.0f);
            return view;
            */
            singeriemview view=null ;
            if(convertView==null)
                view=new singeriemview(getApplicationContext()) ;
            else{
                view=(singeriemview)convertView ;//이 경우 재사용하기에 메모리의 효율적 사용이 가능하다.
            }

            Singeritem item=items.get(position) ;


            view.setname(item.getName());
            view.setage(item.getAge());
            return view ;
        }
    }
}
