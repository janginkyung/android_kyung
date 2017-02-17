package orgm.androidtown.mission17;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView videolist ;
    VideoListAdapter listadpater ;

    int[] mId;
    String[] mName;
    long[] mDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Cursor mCur=getMoviewInfo();
        if(mCur!=null){
            int mCnt=mCur.getCount();
            mId=new int[mCnt];				//�迭 �ʱ�ȭ ���ְ�
            mName=new String[mCnt];			//
            mDate=new long[mCnt];			//
            mCur.moveToFirst();				//Ŀ���� ó������ ��������
            for(int i=0;i<mCnt;i++) {
                mId[i] = mCur.getInt(0);        //������ ������ ��������
                mName[i] = mCur.getString(1);    //������ �־�����
                mDate[i] = mCur.getLong(2);    //
                mCur.moveToNext();            //
            }}



        videolist=(ListView)findViewById(R.id.list)  ;
        listadpater=new VideoListAdapter() ;

        for(int i=0 ; i<mId.length ; i++){
        listadpater.additem(new videoitem(mDate[i],mId[i]));}
        //어뎁터에 더해주는 역할해야한다.

        videolist.setAdapter(listadpater);

        videolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplication(),mName[position].toString(), Toast.LENGTH_LONG ).show();
                Intent intent=new Intent(getApplicationContext(), secind.class) ;
                intent.putExtra("url",mName[position]) ;
                startActivityForResult(intent,1001);
                //  videoitem item=(videoitem)listadpater.getItem(position) ;
            }
        }) ;




    }

    String[] proj;
    String sort;
    Uri mUri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    private Cursor getMoviewInfo() {

        proj=new String[]{MediaStore.Video.Media._ID,		//ID
                MediaStore.Video.Media.DISPLAY_NAME,//FILENAME
                MediaStore.Video.Media.DATE_TAKEN	//DATE
        };
        sort=MediaStore.Video.Media._ID+" ASC";				//������ ���̵� �������� ��������

        return managedQuery(mUri, proj, null, null,sort );	//�����Ѱ� ������
    }


    class VideoListAdapter extends BaseAdapter{
        ArrayList<videoitem>videolist=new ArrayList<videoitem>() ;

public void additem(videoitem item){
    videolist.add(item);
}
        @Override
        public int getCount() {
            return videolist.size();
        }

        @Override
        public Object getItem(int position) {
            return videolist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            videoitemview view=null ;
            if(convertView==null){
                view=new videoitemview(getApplicationContext()) ;
            }
            else
            {
                view=(videoitemview)convertView ;

            }

            videoitem item=videolist.get(position) ;
            view.setname("내 동영상 #"+item.getId()+"");

            return view ;
        }
    }

}
