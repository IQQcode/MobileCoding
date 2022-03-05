package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-18 16:26
 * @Description:
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    // 数据库名
    private static final String DATABASE_NAME = "bookstore.db";

    // 表名
    public static final String BOOK_TABLE_NAME = "book";

    private String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + BOOK_TABLE_NAME +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";
    // 建表
    /**
     * create table Book (
     *     id integer primary key autoincrement,
     *     author text, // 文本类型
     *     price real, // 浮点型
     *     pages integer,
     *     name text)
     */


    //数据库版本号
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TAG", "onCreate: 创建数据库");
        // 建表
        db.execSQL(CREATE_TABLE_SQL);
        // db.execSQL("CREATE TABLE IF NOT EXISTS " + BOOK_TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + " author text," + " price real," + " pages integer," + " name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
