package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.javabean.user;

public class SQLite extends SQLiteOpenHelper {
    private static final String DB_NAME="musicsqlite.db";

    private static final String create_users="create table users(name varchar(32),password varchar(32))";

    public SQLite(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_users);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {

    }

    public long register(user u){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",u.getName());
        cv.put("password",u.getPassword());
        long users = db.insert("users",null,cv);
        return users;
    }

    public boolean login(String name,String password){
        SQLiteDatabase db1 = getWritableDatabase();
        boolean result = false;
        Cursor users = db1.query("users",null,"name like ?",new String[]{name},null,null,null);
        if(users != null){
            while (users.moveToNext()){
                String password1 = users.getString(1);
                result = password1.equals(password);
                return result;
            }
        }
        return false;
    }
    public long update(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password",password);
        long i = db.update("users", cv, "name like ?", new String[]{username});
        return i;
    }
    public boolean equals(String password1, String password2){
        boolean i=password1.equals(password2);
        return i;
    }
}

