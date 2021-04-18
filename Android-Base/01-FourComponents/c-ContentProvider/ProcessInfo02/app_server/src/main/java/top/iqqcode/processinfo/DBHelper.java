package top.iqqcode.processinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-16 18:35
 * @Description:
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static final String DATABASE = "test.db";
    private static final String TABLE = "user";

    public DBHelper(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "create table " + TABLE + "(id int primary key, name varchar not null)";
        db.execSQL(create_table);
        db.execSQL("insert into " + TABLE + " values(1001, '张三'),(1002, '李四')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor query() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public long insert(ContentValues cv) {
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TABLE, null, cv);
        db.close();
        return result;
    }
}
