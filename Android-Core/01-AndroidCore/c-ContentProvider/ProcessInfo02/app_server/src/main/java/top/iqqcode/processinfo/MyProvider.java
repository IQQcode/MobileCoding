package top.iqqcode.processinfo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * @Author: iqqcode
 * @Date: 2021-04-16 20:06
 * @Description:
 */
public class MyProvider extends ContentProvider {
    private DBHelper mUserDBHelper;
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH); //用于匹配URI，并返回对应的操作编码
    private static final String AUTHORITES = "com.zhyan8.content.MyProvider";
    private static final int QUERY = 1; //查询操作编码
    private static final int INSERT = 2; //插入操作编码

    static { //添加有效的 URI 及其编码
        sUriMatcher.addURI(AUTHORITES, "/query", QUERY);
        sUriMatcher.addURI(AUTHORITES, "/insert", INSERT);
    }

    @Override
    public boolean onCreate() {
        mUserDBHelper = new DBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.e("xxx-Provider", "query: ");
        int code = sUriMatcher.match(uri);
        if (code == QUERY) {
            return mUserDBHelper.query();
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.e("xxx-Provider", "insert: ");
        int code = sUriMatcher.match(uri);
        if (code == INSERT) {
            mUserDBHelper.insert(values);
        }
        getContext().getContentResolver().notifyChange(uri, null); //通知外界，数据发生变化
        return null;
    }

    @Override
    public String getType(Uri uri) { //获取资源 MIME
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
