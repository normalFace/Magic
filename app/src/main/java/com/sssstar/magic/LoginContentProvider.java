package com.sssstar.magic;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class LoginContentProvider extends ContentProvider {
    private Context mcontext;
    databaseGO databaseGO = null;
    SQLiteDatabase sqLiteDatabase = null;
    private final String TAG = "LoginContentProvider";

    private static final UriMatcher matcher;
    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI("com.sssstar.magic.LoginContentProvider","path",1);
    }

    public LoginContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG,"delete() event");
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        Log.i(TAG,"getType() event");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)){
            case 1:
                Log.i(TAG,uri.toString());
                long rowId = sqLiteDatabase.insert("account",null,values);
                if(rowId>0){
                    Uri nameUri = ContentUris.withAppendedId(uri,rowId);
                    Log.i(TAG,nameUri.toString());
                    mcontext.getContentResolver().notifyChange(uri,null);
                    return nameUri;
                }
            default:
                break;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        mcontext = getContext();
        databaseGO = new databaseGO(mcontext);
        sqLiteDatabase = databaseGO.getWritableDatabase();
//        sqLiteDatabase.execSQL("delete from account");
//        Log.i(TAG,"oncreate() event,delete from account");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        return sqLiteDatabase.query("account",projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

}
