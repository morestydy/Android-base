package com.example.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
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
//        加载网络URL
        mWvMain.getSettings().setJavaScriptEnabled(true);
        mWvMain.setWebViewClient(new MyWebViewClient());
        mWvMain.setWebChromeClient(new MyWebChormClient());
//        mWvMain.addJavascriptInterface();
        mWvMain.loadUrl("https://www.cuit.edu.cn/");
//        加载assets下的html文件
//        mWvMain.loadUrl("file:///android_asset/index.html");
//        加载HTML代码
//        mWvMain.loadDataWithBaseURL();
    }

//    返回键处理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWvMain.canGoBack()) {
            mWvMain.goBack();
            return true;  //事件处理完毕,不需要再传递
        }
        return super.onKeyDown(keyCode, event);
    }
    class MyWebViewClient extends WebViewClient{
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            view.loadUrl(request.getUrl().toString());
//            return true;
//        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("http:") || url.startsWith("https:")) {
                view.loadUrl(url);
                return false;
            }else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("webview","onPageStarted...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("webview","onPageFinished...");

            //弹出对话框
//            mWvMain.loadUrl("javascript:alert('hello')");
//            mWvMain.evaluateJavascript("javascript:alert('hello')",null);
        }
    }
    class MyWebChormClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {//监听进度
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {//获取标题
            setTitle(title);
            super.onReceivedTitle(view, title);
        }
    }
}
