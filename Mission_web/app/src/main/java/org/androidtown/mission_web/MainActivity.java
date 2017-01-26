package org.androidtown.mission_web;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout sliding ;
    Button button1 ;
    Animation first ;
    Animation second ;
    EditText urlInput ;
    private Handler mHandler = new Handler();
    Boolean click ;
    WebView webview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click=false ;
        urlInput = (EditText) findViewById(R.id.editText);
        sliding=(LinearLayout)findViewById(R.id.container) ;
        button1=(Button)findViewById(R.id.button) ;


        webview=(WebView)findViewById(R.id.web) ;
        webview.setWebChromeClient(new WebBrowserClicent());


        first=AnimationUtils.loadAnimation(this, R.anim.translate);
        second=AnimationUtils.loadAnimation(this, R.anim.translate_back) ;

        SlidingPageAnimationListner animation=new SlidingPageAnimationListner() ;
       first.setAnimationListener(animation);
       second.setAnimationListener(animation);
    }


public void onButton2Clicked(View v){
    webview.loadUrl(urlInput.getText().toString());
}
    public void onButton1Clicked(View v){
        if(click==true)
        {
            sliding.startAnimation(second);
            }
        else
        {
            sliding.setVisibility(View.VISIBLE);
            sliding.startAnimation(first);

        }

    }

    private class SlidingPageAnimationListner implements Animation.AnimationListener{


        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(click==true)
            {
                sliding.setVisibility(View.INVISIBLE);
                button1.setBackgroundResource(R.drawable.im2);
                click=false ;
            }
            else{
                button1.setBackgroundResource(R.drawable.im1);
                click=true ;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

final class WebBrowserClicent extends WebChromeClient{
    public boolean onJsAlert(WebView view, String url, String message, JsResult result){
        result.confirm();
        return true ;
    }
}
}
