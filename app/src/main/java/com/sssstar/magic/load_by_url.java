package com.sssstar.magic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class load_by_url extends AppCompatActivity {
    private WebView webView;
    private long exitTime = 0;
    private Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_by_url);
        mcontext = load_by_url.this;
        webView = new WebView(mcontext);
        webView.setWebViewClient(new WebViewClient(){
            //设置webview打开的网页在当前页面显示而不是跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView,String url){
                try{
                    if(url.startsWith("http:") || url.startsWith("https:")){
                        webView.loadUrl(url);
                    }else{
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);//设置webview属性，运行js
        webView.loadUrl("http://www.baidu.com/");
        setContentView(webView);                         //显示webview
    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            if((System.currentTimeMillis() - exitTime) > 2000){
                Toast.makeText(mcontext,"再按一次退出webview",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                super.onBackPressed();
            }
        }
    }

}
