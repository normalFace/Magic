package com.sssstar.magic;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class spGo {
    private Context mcontext;

    public spGo(){

    }

    public spGo(Context mcontext){
        this.mcontext = mcontext;
    }

    public void save(String accname,String pwd) {
        SharedPreferences sp = mcontext.getSharedPreferences("magic1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("actname", accname);
        editor.putString("pwd", pwd);
        editor.commit();
        Toast.makeText(mcontext,"账号密码已经保存",Toast.LENGTH_SHORT).show();
        Log.i("spgo", "commit accout and password success");
    }

    public Map<String,String> read(){
        Map<String,String> data = new HashMap<String,String>();
        SharedPreferences sp = mcontext.getSharedPreferences("magic1", Context.MODE_PRIVATE);
        data.put("actname",sp.getString("actname","null"));
        data.put("pwd",sp.getString("pwd","null"));
        Log.i("spgo", "read accout and password success");
        return data;

    }

}
