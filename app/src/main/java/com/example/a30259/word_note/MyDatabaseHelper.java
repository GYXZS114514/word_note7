package com.example.a30259.word_note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 30259 on 2019/11/13.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String create_table = "create table word ("
            +"yw text primary key,"
            +"zw text,"
            +"lj text)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(create_table);
        //Toast.makeText(mContext,"success",Toast.LENGTH_SHORT).show();

    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
