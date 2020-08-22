package com.example.helloworld.denglu.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.helloworld.denglu.db.UserOpenHelper;
import com.example.helloworld.denglu.db.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * com.example.helloworld.denglu.db.dao
 * HelloWorld
 *
 * @author:Tom 2020/8/20
 * 描述:
 **/

public class UserDao {

    private final UserOpenHelper userOpenHelper;

    private static UserDao userDao=null;
    private UserDao(Context context){
        userOpenHelper = new UserOpenHelper(context, "user.db", null, 1);
    }

    public static UserDao getInstance(Context context){
        if (userDao == null){
            userDao = new UserDao(context);
        }
        return userDao;
    }

    public void insert(User user){
        SQLiteDatabase db = userOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",user.getName());
        contentValues.put("psw",user.getPsw());
        db.insert("user",null,contentValues);
        db.close();
    }

    public List<User> findall(){
        SQLiteDatabase db = userOpenHelper.getWritableDatabase();
        Cursor cursor=db.query("user",null,null,null,null,null,null);
        List<User> usersList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setPsw(cursor.getString(cursor.getColumnIndex("psw")));
                usersList.add(user);
            }while (cursor.moveToNext());
        }

        db.close();
        return usersList;
    }
}
