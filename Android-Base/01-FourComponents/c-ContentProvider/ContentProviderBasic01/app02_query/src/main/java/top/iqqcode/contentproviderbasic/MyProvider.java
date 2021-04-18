package top.iqqcode.contentproviderbasic;

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
 * @Date: 2021-04-15 08:31
 * @Description:操作person表的provider类
 */
public class MyProvider extends ContentProvider {

    private DBHelper dbHelper;
    SQLiteDatabase database = null;

    // 设置ContentProvider的唯一标识
    public static final String AUTOHORITY = "top.iqqcode.contentproviderbasic.myprovider";

    public static final int User_Code = 1;
    public static final int Job_Code = 2;

    // 存放合法的Uri容器
    private final static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
        matcher.addURI(AUTOHORITY,"user", User_Code);
        // 若URI资源路径 = content://cn.scu.myprovider/job ，则返回注册码Job_Code
        matcher.addURI(AUTOHORITY, "/job", Job_Code);
        matcher.addURI(AUTOHORITY, "/job/#", Job_Code);
    }


    /**
     * 初始化ContentProvider
     * @return
     */
    @Override
    public boolean onCreate() {
        Log.e("TAG", "PersonProvider onCreate()");
        // 在ContentProvider创建时对数据库进行初始化
        // 运行在主线程，故不能做耗时操作, 此处仅作展示
        dbHelper = new DBHelper(getContext());
        database = dbHelper.getWritableDatabase();

        // 初始化两个表的数据(先清空两个表,再各加入一个记录)
        database.execSQL("delete from user");
        database.execSQL("insert into user values(1,'iqqcode');");
        database.execSQL("insert into user values(2,'Kobe');");

        database.execSQL("delete from job");
        database.execSQL("insert into job values(1,'Android');");
        database.execSQL("insert into job values(2,'iOS');");

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.e("TAG", "PersonProvider query()");

        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        String table = getTableName(uri);

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);

        // 查询数据
        return database.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        String table = getTableName(uri);

        // 向该表添加数据
        database.insert(table, null, values);

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        getContext().getContentResolver().notifyChange(uri, null);

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     * @param uri
     * @return
     */
    private String getTableName(Uri uri){
        String tableName = null;
        switch (matcher.match(uri)) {
            case User_Code:
                tableName = DBHelper.USER_TABLE_NAME;
                break;
            case Job_Code:
                tableName = DBHelper.JOB_TABLE_NAME;
                break;
            default:
                throw  new RuntimeException("查询的uri不合法" + uri.toString());
        }
        return tableName;
    }
}
