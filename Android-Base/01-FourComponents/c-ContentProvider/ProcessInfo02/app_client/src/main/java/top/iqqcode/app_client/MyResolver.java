package top.iqqcode.app_client;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

/**
 * @Author: iqqcode
 * @Date: 2021-04-16 18:55
 * @Description:
 */
public class MyResolver {
    private ContentResolver mContentResolver;
    private static final String AUTHORITES = "top.iqqcode.processinfo.myprovider";

    public MyResolver(Context context) {
        mContentResolver = context.getContentResolver();
    }

    public ArrayList<User> query() {
        ArrayList<User> list = new ArrayList<>();
        Uri uri = Uri.parse("content://" + AUTHORITES + "/query");
        Cursor cursor = mContentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            list.add(user);
        }
        cursor.close();
        return list;
    }

    public void insert(User user) {
        Uri uri = Uri.parse("content://" + AUTHORITES + "/insert");
        ContentValues cv = new ContentValues();
        cv.put("id", user.getId());
        cv.put("name", user.getName());
        mContentResolver.insert(uri, cv);
    }
}
