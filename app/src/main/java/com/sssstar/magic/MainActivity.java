package com.sssstar.magic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button btn_login,btn_reg,btn_search;
    private EditText actname,pwd;
    private spGo spgo;
    private Context mcontext;
    private TextView showText;

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

            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> data = spgo.read();
                //Log.i("main",data.toString());
                showText.setText(data.get("actname")+":"+data.get("pwd"));

            }
        });



    }

}
