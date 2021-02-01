package com.sssstar.magic;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    private final String TAG = "MyIntentService";


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getExtras().getString("param");
        if(action.equals("intentService1")) Log.i(TAG,"启动intentservice1");
        else if(action.equals("intentService2")) Log.i(TAG,"启动intentservice2");
        else if(action.equals("intentService3")) Log.i(TAG,"启动intentservice3");

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind() event");
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate() event");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand() event");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        Log.i(TAG,"setIntentRedelivery() event");
        super.setIntentRedelivery(enabled);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy() event");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG,"onRebind() event");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"boolean onUnbind() event");
        return super.onUnbind(intent);
    }
}
