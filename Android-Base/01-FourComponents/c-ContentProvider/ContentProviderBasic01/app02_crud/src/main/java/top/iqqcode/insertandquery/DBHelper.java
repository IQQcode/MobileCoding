package top.iqqcode.insertandquery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-15 12:58
 * @Description:
 */
public class DBHelper extends SQLiteOpenHelper {

    // 数据库名
    private final static String DATABASE_NAME = "finch.db";

    // 表名
    public final static String PERSON_TABLE_NAME = "person";

    //数据库版本号
    private static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + PERSON_TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
