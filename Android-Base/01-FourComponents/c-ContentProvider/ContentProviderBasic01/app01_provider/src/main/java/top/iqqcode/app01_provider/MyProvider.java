package top.iqqcode.app01_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-16 20:54
 * @Description:
 */
public class MyProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;

    public static final String AUTHORITY = "top.iqqcode.provider.myprovider";
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    static {
        // 访问book表中的所有数据
        matcher.addURI(AUTHORITY, "book", BOOK_DIR);
        // 访问book表中的单条数据
        matcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        database = dbHelper.getWritableDatabase();

        database.execSQL("insert into book values(1,'郭神','78.0','998','第一行代码');");
        database.execSQL("insert into book values(2,'李洋','99.0','1024','Android疯狂讲义');");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (matcher.match(uri)) {
            case BOOK_DIR:
                cursor = database.query("book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = database.query("book", projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
                break;
            default:
                throw new RuntimeException("uri匹配错误奥~" + uri.toString());
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (matcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.top.iqqcode.provider.myprovider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.top.iqqcode.provider.myprovider.book";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        database = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (matcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = database.insert("book", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" +
                        newBookId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        // 删除数据
        database = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (matcher.match(uri)) {
            case BOOK_DIR:
                deletedRows = database.delete("book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = database.delete("book", "id = ?", new String[]{bookId});
                break;
            default:
                break;
        }
        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String
            selection, @Nullable String[] selectionArgs) {
        // 更新数据
        database = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (matcher.match(uri)) {
            case BOOK_DIR:
                updatedRows = database.update("book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = database.update("book", values, "id = ?", new String[]{bookId});
                break;
            default:
                break;
        }
        return updatedRows;
    }
}
