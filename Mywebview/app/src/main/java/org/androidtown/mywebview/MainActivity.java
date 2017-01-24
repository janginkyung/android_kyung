package org.androidtown.mywebview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WebView web ;
    private Button loadButton;
    private Handler mHandler = new Handler();
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //웹뷰를 객체화시킨다.
        web=(WebView)findViewById(R.id.webview) ;
        WebSettings settings=web.getSettings() ;
        settings.setJavaScriptEnabled(true);

        web.setWebChromeClient(new WebBrowserClient());
        web.addJavascriptInterface(new JavaScriptMethods(), "sample");

        // assets 폴더에 있는 메인 페이지 로딩
        web.loadUrl("file:///android_asset/sample.html");

        final EditText urlInput = (EditText) findViewById(R.id.editText);

        // 버튼 이벤트 처리
        loadButton = (Button) findViewById(R.id.button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 입력한 URL의 페이지 로딩
                web.loadUrl(urlInput.getText().toString());
            }
        });

    }

    public class JavaScriptMethods {

        JavaScriptMethods() {

        }
        @android.webkit.JavascriptInterface
        public void clickOnFace() {
            mHandler.post(new Runnable() {
                public void run() {
                    // 버튼의 텍스트 변경
                    loadButton.setText("클릭후열기");
                    // 자바스크립트 함수 호출
                    web.loadUrl("javascript:changeFace()");
                }
            });

        }
    }



    final class WebBrowserClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(TAG, message);
            result.confirm();

            return true;
        }
    }
}
