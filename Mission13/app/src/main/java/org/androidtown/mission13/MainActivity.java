package org.androidtown.mission13;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    final String TAG="Mainactivity" ;

    CalendarMonthView monthView ;
    CalendarMonthAdapter monthviewAdapter ;
    TextView monthText ;
    int curyear ;
    int curMonth;
    int currentday ;

    int tempday=0 ;
    int tempmonth=0;
    int tempyear =0;
    int today=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    ImageView image ;
    Button button ;
    Handler handler=new Handler( );

    HashMap<Integer,String > hashtable=new HashMap<Integer,String>(32) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image=(ImageView)findViewById(R.id.imageView) ;
        button=(Button)findViewById(R.id.button) ;

        currentday= Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+3 ;


                ;
        curMonth=0 ;
        curyear=0 ;
        monthView=(CalendarMonthView)findViewById(R.id.monthView) ;
        monthviewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthviewAdapter);

        Log.d(TAG,"선택 되기전") ;
        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
            @Override
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                MonthItem curitem=(MonthItem) monthviewAdapter.getItem(position) ;
                Log.d(TAG,position+"가 선택됬습니다. ") ;
                final int day=curitem.getDay() ;
                tempday=curitem.getDay() ;
                String fix=hashtable.get(day) ;
                //Toast.makeText(getApplicationContext(), day+"일이 선택",Toast.LENGTH_LONG).show();


            }
        });

        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthviewAdapter.setPreviousMonth();
                monthviewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthviewAdapter.setNextMonth();
                monthviewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

    }

    private void setMonthText() {
        curyear = monthviewAdapter.getCurYear();
        curMonth = monthviewAdapter.getCurMonth();

        tempmonth=curMonth+1 ;
        tempyear=curyear ;
        monthText.setText(curyear + "년 " + (curMonth+1) + "월");
    }




    public void onButtonClicked(View v){
        if(tempday<currentday){
            Toast.makeText(getApplicationContext(), "날짜가 늦어서 알려줄수 없습니다. ",Toast.LENGTH_LONG).show();
        }
       else{
            Requestthread thread=new Requestthread() ;
            thread.start() ;
        }
    }

    class Requestthread extends Thread{
        public void run(){
            try{
                StringBuilder outputbuilder=new StringBuilder() ;
                String urlstr="http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108";
                URL url=new URL(urlstr) ;
                HttpURLConnection con=(HttpURLConnection)url.openConnection() ;
                con.setDoInput(true);
                con.setDoOutput(true);
                //연결이 안도면 15초 후에 연결을 끊어버린다.
                con.setConnectTimeout(15000);

                int rescode=con.getResponseCode() ;
                if(rescode==HttpURLConnection.HTTP_OK) {
                    InputStream instream= con.getInputStream() ;
                    parseRSS(instream);



                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
    private void parseRSS(InputStream instream){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance() ;
        try {
            final DocumentBuilder builder=factory.newDocumentBuilder() ;
            Document document= builder.parse(instream) ;
            final ArrayList<RSSitem> rsslist=parseDocument(document) ;

            Log.d(TAG, "parserss시작") ;
            final String curent=rsslist.get(0).getDay();
            final String curdate = converterDate(tempday, tempmonth,tempyear) ;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(curent.equals(curdate)==true)
                    {
                    button.setText(rsslist.get(0).getCloud());
                    button.append(" 최고온도는 "+rsslist.get(0).getTempM());
                        button.append(" 최저온도는 "+rsslist.get(0).getTempN());
                    }
                   else if(curent.equals(curdate)==false)
                    {
                        int diff=tempday-today-3 ;
                        button.setText(rsslist.get(diff).getCloud()+" 최고온도는 "+rsslist.get(diff).getTempM()+" 최저온도는 "+rsslist.get(diff).getTempN());
                    }
                }
            }) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String converterDate(int year,int month,int day){

        String cur_date;

        if((month<10)&&(day<10)){	//int���̶� 10���ϸ� 1�ڸ��� �츰 ���ڸ��� �Ȱ� �ʿ��ϴϱ�
            cur_date=String.valueOf(year)+"0"+String.valueOf(month)+"0"+String.valueOf(day);
        }else if((month<10)){
            cur_date=String.valueOf(year)+"0"+String.valueOf(month)+String.valueOf(day);
        }else if((day<10)){
            cur_date=String.valueOf(year)+String.valueOf(month)+"0"+String.valueOf(day);
        }else{
            cur_date=String.valueOf(year)+String.valueOf(month)+String.valueOf(day);
        }
        return cur_date;
    }
    private ArrayList parseDocument(Document document){
        //웹브라우저에서 봤던것 처럼 한기사가 itm이므로 갯수를 세어준다.
        Element ele=document.getDocumentElement() ;//element가 웹페이지의 element와 같다.
        ArrayList<RSSitem> itemlist=new ArrayList<RSSitem>() ;
        //element안에서 node를 또 셀수있다.
        NodeList nodlist= ele.getElementsByTagName("data") ;
        //아이템이라는 태그만 찾아낸 것이다. 그래서 필요한 정보를
        //파싱해서 던져줘야한다.
        if(nodlist!=null){

            for(int i=0 ; i<nodlist.getLength() ; i++){
                RSSitem rssitem=parseItemNode(nodlist, i) ;
                itemlist.add(rssitem) ;}
        }
        return itemlist ;
    }


    private RSSitem parseItemNode(NodeList nodlist, int i){
        Element elem=(Element)nodlist.item(i) ;
        Element modele=(Element)elem.getElementsByTagName("mode").item(0);//title에서 텍스트를 가지고오게된다.
        Element dayele=(Element)elem.getElementsByTagName("tmEf").item(0);//title에서 텍스트를 가지고오게된다.
        Element cloudele=(Element)elem.getElementsByTagName("wf").item(0);//title에서 텍스트를 가지고오게된다.
        Element tempNele=(Element)elem.getElementsByTagName("tmn").item(0);//title에서 텍스트를 가지고오게된다.
        Element tempMele=(Element)elem.getElementsByTagName("tmx").item(0);//title에서 텍스트를 가지고오게된다.
        String  mode="" ;
        String day="";
        String cloud="";
        String tempN="";
        String tempM ="";
        if(modele!=null){
            Node firstchild=modele.getFirstChild() ;
            if(firstchild!=null){
                mode=firstchild.getNodeValue() ;
            }
        }
        if(dayele!=null){
            Node firstchild=dayele.getFirstChild() ;
            if(firstchild!=null){
                day=firstchild.getNodeValue() ;
            }
        }
        if(cloudele!=null){
            Node firstchild=cloudele.getFirstChild() ;
            if(firstchild!=null){
                cloud=firstchild.getNodeValue() ;
            }
        }
        if(tempNele!=null){
            Node firstchild=tempNele.getFirstChild() ;
            if(firstchild!=null){
                tempN= firstchild.getNodeValue() ;
            }
        }
        if(tempMele!=null){
            Node firstchild=tempMele.getFirstChild() ;
            if(firstchild!=null){
                tempM=firstchild.getNodeValue() ;
            }
        }
        RSSitem ritem=new RSSitem(day,cloud,tempN,tempM) ;
        return ritem ;

    }


    public void getweatherImage(String weather){
        if(weather==null){											//���������� ������

        }else{														//������

            image.setVisibility(View.VISIBLE);
            if(weather.toString().equals("맑음")){
                image.setImageResource(R.drawable.clear);
            }else if(weather.toString().equals("구름조금")){
                image.setImageResource(R.drawable.cloudy);
            }else if(weather.toString().equals("구름조금")){
                image.setImageResource(R.drawable.partly_cloudy);
            }else if(weather.toString().equals("구름많음")){
                image.setImageResource(R.drawable.mostly_cloudly);
            }else if(weather.toString().equals("흐리고 비")){
                image.setImageResource(R.drawable.rain);
            }else if(weather.toString().equals("눈")){
                image.setImageResource(R.drawable.snow);
            }else if(weather.toString().equals("눈/비")){
                image.setImageResource(R.drawable.snow_rain);
            }else{

            }
        }


    }

}
