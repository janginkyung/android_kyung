package org.androidtown.mydatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit,edit2 ;
    TextView text ;
    CustomerDatabaseHelper databasehelper ;
    String databasename,tablename;
    SQLiteDatabase database;
    ListView listview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit=(EditText)findViewById(R.id.editText) ;
        edit2=(EditText)findViewById(R.id.editText2) ;

        text=(TextView)findViewById(R.id.textView) ;
        listview=(ListView)findViewById(R.id.listview) ;
    }
    public void onButton1Clicked(View v){
        databasename=edit.getText().toString()  ;

        try {
            //데이터 베이스를 오픈하는 방법
           // database = this.openOrCreateDatabase(databasename, Context.MODE_PRIVATE, null);
            databasehelper=new CustomerDatabaseHelper(getApplicationContext(), databasename, null, 2);//1은 버젼으로 처음 만드는 버젼
            database=databasehelper.getWritableDatabase() ;
            println("데이터 베이스를 열었습니다. "+databasename);

        }catch (Exception e){e.printStackTrace();}


    }

    public void onButton2Clicked(View v){
        creatTable() ;
       /* tablename=edit2.getText().toString() ;
        try {
            if(database!=null){
                println("테이블 "+tablename);
                //테이블을 만드는 규칙
                database.execSQL("CREATE TABLE if not exists " +tablename+"("
                +"_id integer PRIMARY KEY autoincrement, "
                +"name text, "
                +"age integer, "
                +"mobile text"
                +")");//id라는 열을 넣어주는 것이 좋다.
                println("테이블을 만들었습니다. "+tablename);

            }
            else
            {
                println("데이터 베이스를 먼저 열어야 합니다 .");
            }




        }catch (Exception e){e.printStackTrace();}
*/
    }

    public void creatTable(){
        tablename=edit2.getText().toString() ;
        try {
            if(database!=null){
                println("테이블 "+tablename);
                //테이블을 만드는 규칙
                database.execSQL("CREATE TABLE if not exists " +tablename+"("
                        +"_id integer PRIMARY KEY autoincrement, "
                        +"name text, "
                        +"age integer, "
                        +"mobile text"
                        +")");//id라는 열을 넣어주는 것이 좋다.
                println("테이블을 만들었습니다. "+tablename);

            }
            else
            {
                println("데이터 베이스를 먼저 열어야 합니다 .");
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void changeTable(){
        try {
            if(database!=null){
                //테이블을 만드는 규칙
                database.execSQL("ALTER TABLE if not exists " +"PRODUCT"+"("
                        +"_id integer PRIMARY KEY autoincrement, "
                        +"name text, "
                        +"price integer"
                        +")");//id라는 열을 넣어주는 것이 좋다.
                println("테이블을 추가로 만들었습니다. "+"PRODUCT");

            }
            else
            {
                println("데이터 베이스를 먼저 열어야 합니다 .");
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void onButton3Clicked(View v){
        try {

            if(tablename==null){
                tablename=edit2.getText().toString() ;
            }
            if(database!=null){
                //테이블을 만드는 규칙
                database.execSQL("INSERT INTO " +tablename+"(name, age, mobile) VALUES "
                        +"('소녀시대', 20, '010-1000-1000')");
                println("데이터를 추가했습니다.  "+tablename);

            }
            else
            {
                println("데이터 베이스를 먼저 열어야 합니다 .");
            }
            database = this.openOrCreateDatabase(databasename, Context.MODE_PRIVATE, null);



        }catch (Exception e){e.printStackTrace();}


    }

    public void onButton4Clicked(View v){
        try {

            if(tablename==null){
                tablename=edit2.getText().toString() ;
            }
            if(database!=null){
                //테이블을 만드는 규칙
                Cursor cursor=database.rawQuery("SELECT _id, name, age, mobile FROM "+tablename,null);

                startManagingCursor(cursor);
                String[] columns = new String[] {"name", "age", "mobile"};
                int[] to = new int[] { R.id.textView2, R.id.textView3, R.id.textView4 };
                SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.customeritem, cursor, columns, to);
                //어뎁터를 그냥 넣는 경우 역할을 해준다. 커서에서 조회한 데이터를 넣어준다.
                listview.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

               int count=cursor.getCount() ;
               println("결과 레코드의 갯수: "+count);

               for(int i=0 ; i <count ; i++){
                   cursor.moveToNext() ;
                   String id=cursor.getString(0);
                   String name=cursor.getString(1);//인덱스값을 준다.
                   int age=cursor.getInt(2) ;
                   String mobile=cursor.getString(3) ;
                   println("레코드# "+i+":"+name+","+age+","+mobile);
               }
                cursor.close();
                println("데이터를 조회했습니다. ");

            }
            else
            {
                println("데이터 베이스를 먼저 열어야 합니다 .");
            }
            database = this.openOrCreateDatabase(databasename, Context.MODE_PRIVATE, null);
        }catch (Exception e){e.printStackTrace();}


    }
    private void println(String data){
        text.append(data+"\n");
    }

    class CustomerDatabaseHelper extends SQLiteOpenHelper{
        public CustomerDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            Toast.makeText(getApplicationContext(), "HELPER의 onCreate 호출됨 ",Toast.LENGTH_LONG).show();
            database=db ;
            creatTable() ;
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Toast.makeText(getApplicationContext(), "HELPER의 onUpgrade 호출됨 :"+oldVersion+"-> "+newVersion,Toast.LENGTH_LONG).show();
            database=db ;
            changeTable() ;
        }



        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            Toast.makeText(getApplicationContext(), "HELPER의 on open 호출됨 ",Toast.LENGTH_LONG).show();
            database=db ;
        }
    }
}
