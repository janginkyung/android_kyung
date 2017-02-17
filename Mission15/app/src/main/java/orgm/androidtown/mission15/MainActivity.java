package orgm.androidtown.mission15;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;



public class MainActivity extends AppCompatActivity {
    public static int spacing = -45;
    CoverFlow coverFlow ;
    ImageView image;
    TextView Tname, Tdate, Tinfo ;


String TAG="Mainactivity" ;
    String databasename,tablename;
    SQLiteDatabase database;
    BookDatabaseHelper helper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView)findViewById(R.id.imageView) ;
        Tname=(TextView)findViewById(R.id.editText2) ;
        Tdate=(TextView)findViewById(R.id.editText3) ;
        Tinfo=(TextView)findViewById(R.id.editText4) ;


        // 커버플로우에 어댑터 설정
        coverFlow = (CoverFlow) findViewById(R.id.coverflow);
        ImageAdapter coverImageAdapter = new ImageAdapter(this);
        coverFlow.setAdapter(coverImageAdapter);

        // 커버플로우에 속성 설정
        coverFlow.setSpacing(spacing);
        coverFlow.setSelection(2, true);
        coverFlow.setAnimationDuration(3000);


        databasename="book"  ;
        tablename="what" ;

        helper=new BookDatabaseHelper(getApplicationContext(), databasename, null,2) ;
        database=helper.getWritableDatabase() ;

        creatTable() ;
        insertInfo() ;
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

    public void creatTable(){
        try {
            if(database!=null){
                Log.d("Mainactivity", "creatTable");
                //테이블을 만드는 규칙
                database.execSQL("CREATE TABLE if not exists " +tablename+"("
                        +"_id integer PRIMARY KEY autoincrement, "
                        +"name text, "
                        +"age integer, "
                        +"description text"
                        +")");//id라는 열을 넣어주는 것이 좋다.
            }
            else
            {
                Log.d("Mainactivity", "creatTable의 else");
                Toast.makeText(getApplicationContext(),"데이터 베이스를 먼저 열어야 합니다 .",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void insertInfo(){
        try {
            if (database!=null) {
                database.execSQL("INSERT INTO " + tablename + "(name, age, description) VALUES "
                        + "('브루클린의 소녀', 2005, '수상한 여자아이들의 모험이 시작된다. ')");
                database.execSQL("INSERT INTO " + tablename + "(name, age, description) VALUES " + "('당신의 완벽한 1년', 1994, '당신의 인생에 만족하시나요? 인생을 찾아 떠나는 주인공의 1년간의 여행이야기.')");
                database.execSQL("INSERT INTO " + tablename + "(name, age, description) VALUES " + "('당신 거기 있어줄래요?', 3015, '기욤 뮈소의 로맨스 . 상처많은 남자와 여자의 사랑이야기 ')");
                database.execSQL("INSERT INTO " + tablename + "(name, age, description) VALUES " + "('나미야 잡화점의 기적', 1876, '상처받은 이들이 우연히 들리게 되는 잡화점 .주인인 나미야는 이들을 치유하게 되는데..  ')");
                Log.d("Mainactivity", "insertInfo 자료 집어넣음 ");
            }
        } catch (Exception e) {
            Log.d("Mainactivity", "insertInfo else");
            e.printStackTrace();
        }
        database = this.openOrCreateDatabase(databasename, Context.MODE_PRIVATE, null);
    }

    class BookDatabaseHelper extends SQLiteOpenHelper {
        public BookDatabaseHelper(Context context) {
            super(context, databasename, null, 2);
        }

        public BookDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.d("Mainactivity", "HELPER의 onCreate 호출됨 ");
            database = db;
            creatTable() ;
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Toast.makeText(getApplicationContext(), "HELPER의 onUpgrade 호출됨 :" + oldVersion + "-> " + newVersion, Toast.LENGTH_LONG).show();
            database = db;
        }


        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            Log.d("Mainactivity", "onOpen ");

        }


    }




private void showItem(int position) {
    if (database != null) {
        //테이블을 만드는 규칙
        String SGL="SELECT _id, name, age, description FROM " + tablename+" where _id like ? " ;
       String[] args={Integer.toString(position)} ;

        Cursor cursor = database.rawQuery(SGL,args);
        String id="" ,name="",description="" ;
        int age=0 ;
        startManagingCursor(cursor);
        int count=cursor.getCount() ;
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();
            id = cursor.getString(0);
            name = cursor.getString(1);//인덱스값을 준다.
            age = cursor.getInt(2);
            description = cursor.getString(3);
        }
        Log.d("Mainactivity", id+"/"+name+"/"+age+"/"+description) ;
        Tinfo.setText(description);
        Tdate.setText(Integer.toString(age));
        Tname.setText(name);
        cursor.close();
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
