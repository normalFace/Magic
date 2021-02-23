package com.sssstar.magic;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class JsWebview extends AppCompatActivity {
    private Context mcontext;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_webview);
        mcontext = JsWebview.this;

        webView = new WebView(mcontext);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webView.loadUrl("file:///android_asset/demo1.html");
        webView.addJavascriptInterface(new nativeInterface(mcontext),"demo1");
        setContentView(webView);
    }
}
