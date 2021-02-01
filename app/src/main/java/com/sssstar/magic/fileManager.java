package com.sssstar.magic;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class fileManager {
    private Context context;

    public fileManager() {
    }

    public fileManager(Context context) {
        super();
        this.context = context;
    }

    /**
     * 判断文件是否存在
     * @param file 待判断的文件
     * @return 文件是否存在的布尔值
     */
    public boolean exist_file(File file){
        if(!file.exists()){
            Log.i("fileManager","No such file or directory:"+file);
            return false;
        }else{
            Log.i("fileManager","file or directory existed:"+file);
        }
        return true;
    }

    public void create_filepath(File file){
        file.mkdirs();
        if(!exist_file(file)){
            Log.i("fileManager","mkDir failed:"+file);
        }else{
            Log.i("fileManager","mkDir success:"+file);
        }
    }

    //往SD卡写入文件的方法
    public void savaFileToSD(File filename, String filecontent) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i("fileManager",filename.toString());
            //这里就不要用openFileOutput了,那个是往手机内存中写数据的
            FileOutputStream output = new FileOutputStream(filename);
            output.write(filecontent.getBytes());
            //将String字符串以字节流的形式写入到输出流中
            output.close();
            //关闭输出流
        } else Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
    }

    //读取SD卡中文件的方法
    //定义读取文件的方法:
    public String readFromSD(File filename) throws IOException {
        StringBuilder sb = new StringBuilder("");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //打开文件输入流
            FileInputStream input = new FileInputStream(filename);
            byte[] temp = new byte[1024];

            int len = 0;
            //读取文件内容:
            while ((len = input.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
            //关闭输入流
            input.close();
        }
        return sb.toString();
    }

}

