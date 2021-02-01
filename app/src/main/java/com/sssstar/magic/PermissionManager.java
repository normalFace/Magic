package com.sssstar.magic;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionManager {

    public static void getSYSwindowPermission(Context context,Activity activity,int REQUEST_CODE){
        if(ContextCompat.checkSelfPermission(context,Manifest.permission.SYSTEM_ALERT_WINDOW)
                !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},REQUEST_CODE);
            Log.i("PermissionManager","add SYSTEM_ALERT_WINDOW permission");
        }else {
            Log.i("PermissionManager","already have SYSTEM_ALERT_WINDOW permission");
        }
    }

    public static void getReadSMSPermission(Context context,Activity activity,int REQUEST_CODE){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_SMS},REQUEST_CODE);
            Log.i("PermissionManager","add READ_SMS permission");
        }else {
            Log.i("PermissionManager","already have READ_SMS permission");
        }
    }

    /**
     * 获取相机权限
     * @param context
     * @param activity
     * @param REQUEST_CODE
     */
    public static void getCameraPermission(Context context,Activity activity,int REQUEST_CODE){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},REQUEST_CODE);
            Log.i("PermissionManager","add camera permission");
        }else {
            Log.i("PermissionManager","already have camera permission");
        }
    }

    /**
     * 获取相机和存储权限
     *
     * @param context
     * @param activity
     * @param REQUEST_CAMERA
     * @return
     */
    public static void getCameraAndStoragePermission(Context context, Activity activity, int REQUEST_CAMERA) {
        getCameraPermission(context,activity,REQUEST_CAMERA);
        getWtStoragePermission(context,activity,REQUEST_CAMERA);
        getRdStoragePermission(context,activity,REQUEST_CAMERA);
    }

    /**
     * 获取写存储权限:根据官方文档，写入权限包含了读取
     *
     * @param context
     * @param activity
     * @param REQUEST_CHOOSE_PHOTO
     * @return
     */
    public static void getWtStoragePermission(Context context, Activity activity, int REQUEST_CHOOSE_PHOTO) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CHOOSE_PHOTO);
            Log.i("PermissionManager","add write_storage permission");
        } else {
            Log.i("PermissionManager","already have write_storage permission");
        }

    }

    /**
     * 获取读存储权限
     *
     * @param context
     * @param activity
     * @param REQUEST_CHOOSE_PHOTO
     * @return
     */
    public static void getRdStoragePermission(Context context, Activity activity, int REQUEST_CHOOSE_PHOTO) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CHOOSE_PHOTO);
            Log.i("PermissionManager","add read_storage permission");
        } else {
            Log.i("PermissionManager","already have read_storage permission");
        }

    }

}
