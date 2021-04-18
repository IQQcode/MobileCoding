package top.iqqcode.insertandquery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String URI_STRING_ALL = "content://top.iqqcode.insertandquery.myprovider/person/";
    private final static String URI_STRING_ID = "content://top.iqqcode.insertandquery.myprovider/person/2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 通过ContentResolver调用ContentProvider插入一条记录
     * @param view
     */
    public void insert(View view) {
        //1. 得到ContentResolver对象
        ContentResolver resolver = getContentResolver();
        //2. 调用其insert
        Uri uri = Uri.parse(URI_STRING_ALL);
        ContentValues values = new ContentValues();
        values.put("name", "flutter");
        uri = resolver.insert(uri, values);
        System.out.println("TABLE person => " + uri.toString());
    }

    /**
     * 通过ContentResolver调用ContentProvider删除一条记录
     * @param view
     */
    public void delete(View view) {
        // 得到ContentResolver對象
        ContentResolver resolver = getContentResolver();
        // 调用delete()
        Uri uri = Uri.parse(URI_STRING_ID);
        int deleteCount = resolver.delete(uri, null, null);
        System.out.println("TABLE person => " + uri.toString() + " 删除了" + deleteCount + "条数据");
    }

    /**
     * 通过ContentResolver调用ContentProvider更新一条记录
     * @param view
     */
    public void update(View view) {
        // 1. 得到ContentResolver对象
        ContentResolver resolver = getContentResolver();
        // 2. 执行update
        Uri uri = Uri.parse(URI_STRING_ALL);
        ContentValues values = new ContentValues();
        values.put("name", "JACK2");
        int updateCount = resolver.update(uri, values, null, null);
        System.out.println("TABLE person => " + uri.toString() + " 更新了" + updateCount + "条数据");
    }

    /**
     * 通过ContentResolver调用ContentProvider查询所有记录
     *
     * @param view
     */
    public void query(View view) {
        //1. 得到ContentResolver对象
        ContentResolver resolver = getContentResolver();
        //2. 调用其query, 得到cursor
        // Uri uri = Uri.parse("content://top.iqqcode.insertandquery.myprovider/person/1"); // 单行记录
        // 查询所有
        Uri uri = Uri.parse(URI_STRING_ALL);
        Cursor cursor = resolver.query(uri, null, null, null, null);
        //3. 取出cursor中的数据, 并显示
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            System.out.println("TABLE person => id-" + id + " name-" + name);
        }
        // 关闭连接
        cursor.close();
    }
}