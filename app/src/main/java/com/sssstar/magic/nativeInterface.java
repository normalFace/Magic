package com.sssstar.magic;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class nativeInterface {

    private Context mcontext;

    public nativeInterface(Context context){
        mcontext = context;
    }

    @JavascriptInterface
    public void hello(){
        Toast.makeText(mcontext,"hello",Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void hello(String params){
        Toast.makeText(mcontext,params,Toast.LENGTH_SHORT).show();
    }

}
