package com.sssstar.magic;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class activity1 extends AppCompatActivity {
    private TextView lgname;
    private Button btn_tkphoto,btn_listview1,btn_call,btn_bd,fileOperation,getSMS,login_page;
    private Button service_page,btn_webview,btn_okhttp;
    private static int REQUEST_CAMERA= 1;
    private static int CREATE_FILE= 2;
    fileManager fileManager = new fileManager(activity1.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        lgname = (TextView) findViewById(R.id.lgname);
        Intent itt = getIntent();
        lgname.setText("尊贵的VIP "+itt.getStringExtra("actname"));
        btn_listview1 = (Button) findViewById(R.id.btn_listview1);
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_bd = (Button) findViewById(R.id.btn_browser);
        btn_tkphoto = (Button) findViewById(R.id.btn_tkphoto);
        fileOperation = (Button) findViewById(R.id.btn_fileOperation);
        getSMS = (Button) findViewById(R.id.btn_getSMS);
        login_page = (Button) findViewById(R.id.login_page);
        service_page = (Button) findViewById(R.id.service_page);
        btn_webview = (Button) findViewById(R.id.btn_webview);
        btn_okhttp = (Button) findViewById(R.id.btn_okhttp);

        activityManager.addActivity(this);

        btn_okhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity1.this, okhttpActivity.class);
                startActivity(intent);
            }
        });

        btn_webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_httpclient = new Intent(activity1.this, webviewActivity.class);
                startActivity(intent_httpclient);
            }
        });

        getSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionManager.getReadSMSPermission(activity1.this,activity1.this,REQUEST_CAMERA);
                Uri uri = Uri.parse("content://sms/");
                ContentResolver resolver = getContentResolver();
                Cursor cursor = resolver.query(uri,new String[]{"address","date","type","body"},null,null,null);
                while(cursor.moveToNext()){
                    String address = cursor.getString(0);
                    String date = cursor.getString(1);
                    String type = cursor.getString(2);
                    String body = cursor.getString(3);
                    Log.i("activity1", "address:"+address);
                    Log.i("activity1", "date:"+date);
                    Log.i("activity1", "type:"+type);
                    Log.i("activity1", "body:"+body);
                }
                cursor.close();
            }
        });

        fileOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionManager.getWtStoragePermission(activity1.this,activity1.this,CREATE_FILE);
                String fileName = "sssstar.txt";
                String filecontent = "test file content ";
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/sssstar";
                File filepath = new File(filePath);
                File filename = new File(filePath,fileName);
                fileManager.create_filepath(filepath);
                try {
                    fileManager.savaFileToSD(filename,filecontent);
                    Log.i("activity1",fileManager.readFromSD(filename));
                    Toast.makeText(getApplicationContext(),"write data success",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"write data failed",Toast.LENGTH_SHORT).show();
                }
                //以下是数据共享流程
                Uri contentUri = FileProvider.getUriForFile(activity1.this,
                        "com.sssstar.magic.fileprovider",//这里与manifest文件里的fileprovider的设置对应
                        filename);//文件的真实访问路径
                Log.i("activity1",contentUri.toString());
                //这里的action，type与其它调用该intent的app的manifest文件里的intentfilter的设置对应，uri就是上面fileprovider生成的uri
                Intent intent = new Intent("com.sssstar.magic.SHARE");
                intent.setDataAndType(contentUri,"share/txt");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent);

            }
        });


        btn_tkphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionManager.getCameraAndStoragePermission(activity1.this,
                        activity1.this,REQUEST_CAMERA);
                long time = Calendar.getInstance().getTimeInMillis();
                String imgName =time+".jpg";
                String imgPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/sssstar";
                fileManager.create_filepath(new File(imgPath));
                File imgContentFile = new File(imgPath,imgName);
                Log.i("activity1","IMG_uri:"+imgContentFile);
                Log.i("activity1","IMG_file_uri:"+Uri.fromFile(imgContentFile).toString());
                Uri uri = FileProvider.getUriForFile(activity1.this,"com.sssstar.magic.fileprovider",imgContentFile);
                Log.i("activity1","IMG_Content_uri:"+uri);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                startActivityForResult(intent,REQUEST_CAMERA);

//                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                mediaScanIntent.setData(uri);
//                sendBroadcast(mediaScanIntent);
            }
        });

        btn_bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.baidu.com");
                Intent bd_intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(bd_intent);
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:10086");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        btn_listview1 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_open = new Intent("com.sssstar.toolman.MAINACTIVITY2",
                        Uri.parse("sssstar://toolman:8888/eeee?token=zzzz&id=2021"));
                Bundle bundle = new Bundle();
                bundle.putString("key1","hahahaha");
                bundle.putString("key1","????????");
                intent_open.putExtras(bundle);
                startActivity(intent_open);
            }
        });

        login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity1.this,MainActivity.class);
                startActivity(intent);
            }
        });

        service_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity1.this,service1.class);
                Bundle bundle = new Bundle();
                bundle.putString("key1","hahahaha");
                bundle.putString("key1","????????");
                intent.putExtras(bundle);
                intent.putExtra("key3",true);
                intent.putExtra("key4",2021);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManager.removeActivity(this);
    }
}
