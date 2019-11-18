package com.example.a30259.word_note;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.a30259.word_note.MyDatabaseHelper;

import java.net.URI;

public class DatabaseProvider extends ContentProvider {
    public static final  int WORD_DIR = 0;
    public static  final  int WORD_ITEM = 1;
    public static final String AUTHORITY = "com.example.a30259.word_note.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"word",WORD_DIR);
        uriMatcher.addURI(AUTHORITY,"word/*",WORD_ITEM);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        Log.d("DatabaseProvider","hahahahahahah");
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        int deletedRow = 0;
        switch (uriMatcher.match(uri)){
            case WORD_DIR:
                deletedRow = db.delete("word",selection,selectionArgs);
                break;
            case WORD_ITEM:
                String yw = uri.getPathSegments().get(1);
                deletedRow = db.delete("word","yw = ?",new String[]{yw});
                break;
            default:
                break;
        }
        return deletedRow;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case WORD_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.a30259.word_note.provider.word";
            case WORD_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.a30259.word_note.provider.word";
        }
        return null;

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
       SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case WORD_DIR:
            case WORD_ITEM:
                db.insert("word",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/word/"+values.get("yw"));
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new MyDatabaseHelper(getContext(),"person8.db",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d("DatabaseProvider","xixixici");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case WORD_DIR:
                cursor = db.query("word",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case WORD_ITEM:
                String yw = uri.getPathSegments().get(1);
                cursor = db.query("word",projection,"yw = ?",new String[]{yw},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        Log.d("DatabaseProvider","555555555");
      SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)){
            case WORD_DIR:
                updateRows = db.update("word",values,selection,selectionArgs);
                break;
            case WORD_ITEM:
                String yw = uri.getPathSegments().get(1);
                updateRows = db.update("word",values,"yw = ?",new String[]{yw});
                break;
            default:
                break;
        }
        return updateRows;
    }
}
