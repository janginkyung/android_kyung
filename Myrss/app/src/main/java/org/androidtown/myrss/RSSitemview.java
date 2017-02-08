package org.androidtown.myrss;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by InKyung on 2017-02-07.
 */

public class RSSitemview extends LinearLayout {

    /**
     * Icon
     */
    private ImageView mIcon;

    /**
     * TextView 01
     */
    private TextView mText01;

    /**
     * TextView 02
     */
    private TextView mText02;

    /**
     * TextView 03
     */
    private TextView mText03;

    /**
     * WebView 04
     */
    private WebView mText04;
    RSSitem item ;
    public RSSitemview(Context context, RSSitem aItem) {
        super(context);
        item=aItem ;
        init(context );
    }

    public RSSitemview(Context context, AttributeSet attrs, RSSitem aItem) {
        super(context, attrs);
        item=aItem ;
        init(context );
    }
    public void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        inflater.inflate(R.layout.rssitem,this,true) ;

        // Set Icon
        mIcon = (ImageView) findViewById(R.id.iconItem);
        mIcon.setImageDrawable(item.getIcon());

        // Set Text 01
        mText01 = (TextView) findViewById(R.id.dataItem01);
        mText01.setText(item.getTitle());

        // Set Text 02
        mText02 = (TextView) findViewById(R.id.dataItem02);
        mText02.setText(item.getDcDate());

        // Set Text 03
        mText03 = (TextView) findViewById(R.id.dataItem03);
        String category = item.getImage();
        if (category != null) {
            mText03.setText(category);
        }

        // Set Text 04
        mText04 = (WebView) findViewById(R.id.dataItem04);

        setTextToWebView(item.getDescription());
        //mText04.setText(aItem.getDescription());
    }

    public void setText(int index, String data) {
        if (index == 0) {
            mText01.setText(data);
        } else if (index == 1) {
            mText02.setText(data);
        } else if (index == 2) {
            mText03.setText(data);
        } else if (index == 3) {
            //mText04.setText(data);

            setTextToWebView(data);

        } else {
            throw new IllegalArgumentException();
        }
    }


    private void setTextToWebView(String msg) {
        Log.d("RSSNewsItemView", "setTextToWebView() called.");

        //String outData = "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><html><body>"
        //		+ msg
        //		+ "</body></html>";

        String outData = msg;

        // 이미지 src 에서 http:// 가 아닌 // 일 경우의 대체
        outData = outData.replace("\"//", "\"http://");

        mText04.loadDataWithBaseURL("http://localhost/", outData, "text/html", "utf-8", null);
    }

    /**
     * set Icon
     *
     * @param icon
     */
    public void setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
    }


}

