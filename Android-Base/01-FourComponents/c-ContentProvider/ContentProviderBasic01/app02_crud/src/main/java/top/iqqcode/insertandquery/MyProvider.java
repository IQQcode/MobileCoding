package top.iqqcode.insertandquery;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-15 12:58
 * @Description:
 */
public class MyProvider extends ContentProvider {

    //用来存放所有合法的Uri的容器
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    // 设置ContentProvider的唯一标识
    public static final int PERSON_CODE_ONE = 1;
    public static final int PERSON_CODE_TWO = 2;

    public static final String AUTOHORITY = "top.iqqcode.insertandquery.myprovider";
    private DBHelper mDbHelper = null;
    private SQLiteDatabase database;


    // 保存合法的uri
    // content://top.iqqcode.contentproviderbasic.myprovider/user    不根据id操作
    // content://top.iqqcode.contentproviderbasic.myprovider/user/#  根据id操作

    static {
        // 若URI资源路径 = content://top.iqqcode.insertandquery.myprovider/user ，则返回注册码User_Code
        matcher.addURI(AUTOHORITY, "/person", PERSON_CODE_ONE); // 不根据id操作
        matcher.addURI(AUTOHORITY, "/person/#", PERSON_CODE_TWO);  // 根据id操作，#匹配任意数字
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DBHelper(getContext());
        database = mDbHelper.getReadableDatabase();
        // 初始化两个表的数据(先清空两个表,再各加入一个记录)
        database.execSQL("delete from person");
        database.execSQL("insert into person values(1,'iqqcode');");
        database.execSQL("insert into person values(2,'Kobe');");
        database.execSQL("insert into person values(3,'Jiazihui');");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.e("TAG", "MyProvider query()");

        // 1. 匹配uri,返回code
        int code = matcher.match(uri);
        String table = getTableName(uri);
        // 如果合法，进行查询
        if (code == PERSON_CODE_ONE) {
            // 不根据id查询
            Cursor cursor = database.query(table, projection, selection, selectionArgs, null, null, null);
            return cursor;
        } else if (code == PERSON_CODE_TWO) { // 根据id查询
            // 得到id
            long id = ContentUris.parseId(uri);
            // 查询
            return database.query(table, projection, "_id = ?", new String[]{id + " "}, null, null, null);
        } else {
            throw new RuntimeException("查询的uri不合法");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.e("TAG", "MyProvider insert()");

        database = mDbHelper.getWritableDatabase();
        int code = matcher.match(uri);
        String table = getTableName(uri);
        if (code == PERSON_CODE_ONE) {
            long id = database.insert(table, null, values);
            // 将id添加到uri中
            uri = ContentUris.withAppendedId(uri, id);
            database.close();
            return uri;
        } else {
            // 如果不合法, 抛出异常
            database.close();
            throw new RuntimeException("插入的uri不合法");
        }
    }

    /**
     * content://top.iqqcode.insertandquery.myprovider/person    不根据id删除
     * content://top.iqqcode.insertandquery.myprovider/person/3  根据id删除
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.e("TAG", "PersonProvider delete()");

        database = mDbHelper.getReadableDatabase();
        //匹配uri, 返回code
        int code = matcher.match(uri);
        String table = getTableName(uri);
        int deleteCount= -1;
        //如果合法, 进行删除
        if(code == PERSON_CODE_ONE) { // 不根据id
            deleteCount = database.delete(table, selection, selectionArgs);
        } else if( code ==PERSON_CODE_TWO) {
            long id = ContentUris.parseId(uri);
            deleteCount = database.delete(table, "_id=" + id, null);
        } else {
            //如果不合法, 抛出异常
            database.close();
            throw new RuntimeException("删除的uri不合法");
        }
        database.close();
        return deleteCount;
    }


    /**
     * content://top.iqqcode.insertandquery.myprovider/person    不根据id更新
     * content://top.iqqcode.insertandquery.myprovider/person/3  根据id更新
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.e("TAG", "MyProvider update()");

        //得到连接对象
        database = mDbHelper.getReadableDatabase();
        //匹配uri, 返回code
        int code = matcher.match(uri);
        String table = getTableName(uri);
        int updateCount = -1;
        //如果合法, 进行更新
        if (code == PERSON_CODE_ONE) {
            updateCount = database.update(table, values, selection, selectionArgs);
        } else if (code == PERSON_CODE_TWO) {
            long id = ContentUris.parseId(uri);
            updateCount = database.update(table, values, "_id=" + id, null);
        } else {
            //如果不合法, 抛出异常
            database.close();
            throw new RuntimeException("更新的uri不合法");
        }

        database.close();
        return updateCount;
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     *
     * @param uri
     * @return
     */
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (matcher.match(uri)) {
            case PERSON_CODE_ONE:
                tableName = DBHelper.PERSON_TABLE_NAME;
                break;
            case PERSON_CODE_TWO:
                tableName = DBHelper.PERSON_TABLE_NAME;
                break;
        }
        return tableName;
    }

}
