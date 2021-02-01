package com.sssstar.magic;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class service1 extends AppCompatActivity {
    private Button start_service,stop_service,start_intentService,
            send_broadcast,reg_broadcast;
    MyReceiver myReceiver;
    private String TAG= "service1";
    private LocalBroadcastManager localBroadcastManager;
    OutLoginReceiver outLoginReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service1);
        start_service = (Button) findViewById(R.id.start_service);
        stop_service = (Button) findViewById(R.id.stop_service);
        start_intentService = (Button) findViewById(R.id.start_intentservice);
        send_broadcast = (Button) findViewById(R.id.send_broadcast);
        reg_broadcast = (Button) findViewById(R.id.reg_broadcast);

        activityManager.addActivity(this);

        final Intent intent = new Intent();
        intent.setAction("com.sssstar.magic.MYSERVICE");
        intent.setPackage("com.sssstar.magic");

        final Intent intentservice1 = new Intent("com.sssstar.magic.MYINTENTSERVICE");
        intentservice1.setPackage("com.sssstar.magic");
        Bundle bundle1 = new Bundle();
        bundle1.putString("param","intentService1");
        intentservice1.putExtras(bundle1);

        final Intent intentservice2 = new Intent("com.sssstar.magic.MYINTENTSERVICE");
        intentservice2.setPackage("com.sssstar.magic");
        Bundle bundle2 = new Bundle();
        bundle2.putString("param","intentService2");
        intentservice2.putExtras(bundle2);

        final Intent intentservice3 = new Intent("com.sssstar.magic.MYINTENTSERVICE");
        intentservice3.setPackage("com.sssstar.magic");
        Bundle bundle3 = new Bundle();
        bundle3.putString("param","intentService3");
        intentservice3.putExtras(bundle3);


        send_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_send = new Intent();
                intent_send.setAction("com.sssstar.toolman.RECEIVE_BROADCAST");
                intent_send.setPackage("com.sssstar.toolman");
                sendBroadcast(intent_send);
            }
        });

        reg_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myReceiver = new MyReceiver();
                IntentFilter intentFilter= new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                registerReceiver(myReceiver,intentFilter);
            }
        });

        start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
            }
        });

        stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });

        start_intentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intentservice1);
                startService(intentservice2);
                startService(intentservice3);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        localBroadcastManager.unregisterReceiver(outLoginReceiver);
        activityManager.removeActivity(this);
    }
}