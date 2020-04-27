package com.example.helloworld;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWvMain = findViewById(R.id.wv);
//        加载本地URL
//        mWvMain.loadUrl("file:////android_asset/index.html");

//        加载网络URL
        mWvMain.setWebViewClient(new MyWebViewClient());
        mWvMain.getSettings().setJavaScriptEnabled(true);
        mWvMain.setWebChromeClient(new MyWebChormClient());
//        mWvMain.evaluateJavascript();
//        mWvMain.loadUrl();
        mWvMain.loadUrl("https://m.baidu.com");
    }

    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("WebView","onPageStarted...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("WebView","onPageFinished");
//            调用js
//            mWvMain.loadUrl("javascript:alert('hello')");
            mWvMain.evaluateJavascript("javascript:alert('hello')",null);
        }
    }
//     进度条
    class MyWebChormClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
//      获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWvMain.canGoBack()) {
            mWvMain.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
