package org.androidtown.mission11;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView text1,text2;
    TextView Mname,number ;
    ImageView image;

    int count ;
    String[] Pname ;
    String[] Pnum ;
    String[] Ptelnum ;
    Bitmap photo;
    int idCnt=0;
    int [] idIndex;
    Handler handler =new Handler( ) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image=(ImageView)findViewById(R.id.imageView) ;
        text1=(TextView)findViewById(R.id.textView02) ;
        text2=(TextView)findViewById(R.id.textView03) ;

        Mname=(TextView)findViewById(R.id.textView1) ;
        number=(TextView)findViewById(R.id.textView2) ;
        ContentResolver re=getContentResolver() ;
        Cursor cursor = getURI();
        count = cursor.getCount();

        Pname=new String[count+1];
        Pnum=new String[count+1];
        Ptelnum=new String[count+1];
        idIndex=new int[count+1];

        if(cursor.moveToFirst()) {    //Ŀ���� ���Ͼ�����
            for (int i = 1; i <= count; i++) {    //��ȭ��ȣ�� ����ŭ
                Pname[i] = cursor.getString(1);    //ù° �÷����� �̸�
                Pnum[i] = cursor.getString(2);    //��° �÷����� ����
                idIndex[i] = Integer.parseInt(cursor.getString(0));    //�̰� �� �ǹ� ����;;
                Ptelnum[i] = cursor.getString(3);    //��° �÷����� ����� ����
                //Log.d("contact_id", getString(idIndex[i]));
                cursor.moveToNext();    //���� Ŀ����
            }
        }

        Button button=(Button)findViewById(R.id.button) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contactthread thread=new Contactthread() ;
                thread.start();
            }
        });
    }
    private Cursor getURI()
    {

        // �ּҷ� URI
        Uri people = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // �˻��� �÷� ���ϱ�
        String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER};
        String sort=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";//���� ID������ ����


        return managedQuery(people, projection, null, null,sort );
    }

    class Contactthread extends Thread{
        public void run(){
            final String numStr2 = String.valueOf(count);
            출처: http://jhrun.tistory.com/120 [JHRunning]
            for(int i=0 ; i <count ;i++){
                final int pos=i ;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        text2.setText(numStr2);
                        Mname.setText(Pname[pos]);
                        number.setText(Ptelnum[pos]);
                        //number.setText(num);

                        photo=getPhoto(idIndex[pos]);
                        if(photo!=null){	//�������� ���
                            image.setImageBitmap(photo);	//����ǥ��
                        }
                        else{	//�������
                            image.setImageDrawable(getResources().getDrawable(R.drawable.emo_im_happy));//�⺻�̹���
                        }


                    }
                }) ;

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
            }

        }
    }
    public Bitmap getPhoto(long Id){	//������������


        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,Id);
        InputStream data = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), uri);	//���������͸� ����

        if(data != null)	//������ �������

            return BitmapFactory.decodeStream(data);	//��Ʈ���� ��Ʈ������ ��ȯ�Ͽ� ����

        else				//�������
            return null;	//null�� ����


    }

}
