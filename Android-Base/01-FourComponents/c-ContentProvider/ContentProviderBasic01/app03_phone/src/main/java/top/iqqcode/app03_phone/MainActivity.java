package top.iqqcode.app03_phone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * @Author: iqqcode
 * @Date: 2021/4/17
 * @Description:读取联系人
 */
public class MainActivity extends AppCompatActivity {

    private final static String URI_PARSE = "content://com.android.contacts/raw_contacts";

    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    private Button mButton_content, mButton_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton_content = findViewById(R.id.btn_get_content);

        listView = findViewById(R.id.lv_main_list);
        list = new ArrayList<>();
        adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, new String[]{"names", "phones"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);

        //申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.
                READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.
                    permission.READ_CONTACTS}, 1);
        } else {
            mButton_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContent();
                }
            });
        }
    }

    public void getContent() {
        //Cursor cursor = getContentResolver().query(Uri.parse(URI_PARSE), null, null, null, null);
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.
                Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<>();
            int id = cursor.getColumnIndex("_id");
            //获取联系人手机号
            String phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.i("TAG", id + "  " + phoneName + "  " + phoneNumber);
            map.put("phoneName", phoneName);
            map.put("phoneNumber", phoneNumber);

            // 根据联系人获取联系人数据
            Cursor cursor2 = getContentResolver().query(Uri.parse("content://com.android.contacts/raw_contacts/" + id + "/data"), null, null, null, null);
            while (cursor2.moveToNext()) {
                String type = cursor2.getString(cursor2.getColumnIndex("mimetype"));
                String data1 = null;
                if ("vnd.android.cursor.item/phone_v2".equals(type)) {
                    data1 = cursor2.getString(cursor2.getColumnIndex("data1"));
                    Log.i("test", "   " + type + " " + data1);
                    map.put("phones", data1);
                }
            }

            list.add(map);
            //通知适配器发生改变
            adapter.notifyDataSetChanged();
        }
    }

    //权限申请的返回处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContent();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}