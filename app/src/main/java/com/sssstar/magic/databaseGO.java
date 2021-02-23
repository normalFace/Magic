package com.sssstar.magic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class databaseGO extends SQLiteOpenHelper {
    //数据库名
    private static final  String DATABASE_NAME = "login.db";

    //表名
    private static final String LOGIN_TABLE_NAME = "account";

    //数据库版本号
    private static final int DATABASE_VERSION = 1;

    private final String TAG = "databaseGO";

    public databaseGO(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    public databaseGO(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_sql = "CREATE TABLE IF NOT EXISTS "+LOGIN_TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT,account varchar(255),pwd varchar(255))";
        sqLiteDatabase.execSQL(create_sql);
        Log.i(TAG,create_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
