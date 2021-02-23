package com.sssstar.magic;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button btn_login,btn_reg,btn_search;
    private EditText actname,pwd;
    private spGo spgo;
    private Context mcontext;
    private TextView showText;
    private Cursor cursor;
    private String TAG="MainActivity";
    private ContentResolver contentResolver;
    private ContentValues values;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = (Button) findViewById(R.id.login);
        btn_reg = (Button) findViewById(R.id.register);
        btn_search = (Button) findViewById(R.id.search);
        actname = (EditText) findViewById(R.id.actname);
        pwd = (EditText) findViewById(R.id.pwd);
        mcontext = getApplicationContext();
        spgo = new spGo(mcontext);
        showText = (TextView) findViewById(R.id.showText);

        uri = Uri.parse("content://com.sssstar.magic.LoginContentProvider/path");
        contentResolver = mcontext.getContentResolver();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = actname.getText().toString();
                String password = pwd.getText().toString();
                Map<String,String> data = spgo.read();
                if(phone.equals(data.get("actname")) && password.equals(data.get("pwd"))){
                    Log.i("main","登录成功");
                    Intent suc = new Intent(MainActivity.this, activity1.class);
                    suc.putExtra("actname",phone);
                    startActivity(suc);
                }else {
                    Log.i("main","登录失败");
                }

            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = actname.getText().toString();
                String password = pwd.getText().toString();
                //Log.i("main",phone+","+password);
                spgo.save(phone,password);
                if(!phone.equals("") && !password.equals("")){
                    values = new ContentValues();
                    values.put("account",phone);
                    values.put("pwd",password);
                    contentResolver.insert(uri,values);
                }else {
                    Toast.makeText(mcontext,"注册信息不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> data = spgo.read();
                //Log.i("main",data.toString());
//                showText.setText(data.get("actname")+":"+data.get("pwd"));

                cursor = contentResolver.query(uri,null,null,null,null);
                HashMap<Integer,String> account_map = new HashMap<>();
                String info = new String();
                if(cursor.getCount()>0){
                    while(cursor.moveToNext()){
                        int id = cursor.getInt(0);
                        String account = cursor.getString(1);
                        String pwd = cursor.getString(2);
                        info = account+":"+pwd;
                        Log.i(TAG,info);
                        account_map.put(id, info);
                        Log.i(TAG,account_map.toString());
                    }
                    showText.setText(account_map.toString());

                }else{
                    Log.i(TAG,"cursor.getCount()==0");
                }
            }
        });



    }

}
