package com.example.helloworld.denglu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * com.example.helloworld.denglu.db
 * HelloWorld
 *
 * @author:Tom 2020/8/20
 * 描述:
 **/

public class UserOpenHelper extends SQLiteOpenHelper {
    static final String CREATE_DB = "create table user(" +
            "id integer primary key autoincrement," +
            " name text," +
            "psw text)";
    public UserOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
