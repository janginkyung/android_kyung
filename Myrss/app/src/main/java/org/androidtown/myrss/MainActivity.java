package org.androidtown.myrss;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    EditText edit ;
    TextView text ;
    Handler handler=new Handler( ) ;
    private static final String TAG = "MainActivity";
    ListView listview ;
    RSSAdapter rssadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edit=(EditText)findViewById(R.id.editText);
        text=(TextView)findViewById(R.id.textView) ;
        listview=(ListView)findViewById(R.id.listview) ;

        rssadapter=new RSSAdapter() ;
        listview.setAdapter(rssadapter );
    }

    class RSSAdapter extends BaseAdapter{

        ArrayList<RSSitem> itemlist=new ArrayList<RSSitem>() ;
        public void setIteList(ArrayList<RSSitem> itemlist){
            this.itemlist=itemlist ;
        }
        @Override
        public int getCount() {
            return itemlist.size();
        }

        @Override
        public Object getItem(int position) {
            return itemlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RSSitemview view=null ;
            if(convertView==null){
                view=new RSSitemview(getApplicationContext(),itemlist.get(position)) ;
            }else{
                view=(RSSitemview) convertView ;


                view.setIcon(itemlist.get(position).getIcon());
                view.setText(0, itemlist.get(position).getTitle());
                view.setText(1, itemlist.get(position).getImage());
                view.setText(3, itemlist.get(position).getDescription());
            }
            return view ;
        }
    }
    public void onButtonClicked(View v){
        Requestthread thread=new Requestthread() ;
        thread.start() ;
    }

    class Requestthread extends Thread{
        public void run(){
            try{
                StringBuilder outputbuilder=new StringBuilder() ;
                String urlstr=edit.getText().toString() ;
                URL url=new URL(urlstr) ;
                HttpURLConnection con=(HttpURLConnection)url.openConnection() ;
                con.setDoInput(true);
                con.setDoOutput(true);
                //연결이 안도면 15초 후에 연결을 끊어버린다.
                con.setConnectTimeout(15000);

                int rescode=con.getResponseCode() ;
                if(rescode==HttpURLConnection.HTTP_OK) {
                    /*
                    //reader는 문자열
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                    String line = null;
                    while (true) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        outputbuilder.append(line+"\n") ;
                    }
                    reader.close();
                    con.disconnect();
*/

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
            DocumentBuilder builder=factory.newDocumentBuilder() ;
            Document document= builder.parse(instream) ;
           final ArrayList<RSSitem> rsslist=parseDocument(document) ;
            println("파싱된 아이쳄의 갯수 :"+rsslist.size());

            handler.post(new Runnable() {
                @Override
                public void run() {
                    rssadapter.setIteList(rsslist);
                    rssadapter.notifyDataSetChanged();
                }
            }) ;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private ArrayList parseDocument(Document document){
        //웹브라우저에서 봤던것 처럼 한기사가 itm이므로 갯수를 세어준다.
        Element ele=document.getDocumentElement() ;//element가 웹페이지의 element와 같다.
        ArrayList<RSSitem> itemlist=new ArrayList<RSSitem>() ;
        //element안에서 node를 또 셀수있다.
       NodeList  nodlist= ele.getElementsByTagName("item") ;
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
        Element titleele=(Element)elem.getElementsByTagName("title").item(0);//title에서 텍스트를 가지고오게된다.
        Element linkele=(Element)elem.getElementsByTagName("link").item(0);//title에서 텍스트를 가지고오게된다.
        Element imageele=(Element)elem.getElementsByTagName("image").item(0);//title에서 텍스트를 가지고오게된다.
        Element dcDateele=(Element)elem.getElementsByTagName("dcDate").item(0);//title에서 텍스트를 가지고오게된다.
        Element descriptionele=(Element)elem.getElementsByTagName("description").item(0);//title에서 텍스트를 가지고오게된다.

        String title="";
        String link="";
        String image="";
        String dcDate="";
        String description ="";
        if(titleele!=null){
                Node firstchild=titleele.getFirstChild() ;
                if(firstchild!=null){
                    title= firstchild.getNodeValue() ;
                }
        }
        if(linkele!=null){
            Node firstchild=linkele.getFirstChild() ;
            if(firstchild!=null){
                link=firstchild.getNodeValue() ;
            }
        }
        if(imageele!=null){
            Node firstchild=imageele.getFirstChild() ;
            if(firstchild!=null){
                image=firstchild.getNodeValue() ;
            }
        }
        if(dcDateele!=null){
            Node firstchild=dcDateele.getFirstChild() ;
            if(firstchild!=null){
                dcDate=firstchild.getNodeValue() ;
            }
        }

        if(descriptionele!=null){
            Node firstchild=descriptionele.getFirstChild() ;
            if(firstchild!=null){
                description=firstchild.getNodeValue() ;
            }
        }
        RSSitem ritem=new RSSitem(title, link,image,dcDate,description) ;
return ritem ;

    }
    private void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                text.append(data+"\n");
            }
        });

    }
}
