package top.iqqcode.app04_phone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);

        //申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.
                READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.
                    permission.READ_CONTACTS}, 1);
        } else {
            Button mButton_get = findViewById(R.id.btn_get_content);
            mButton_get.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContacts();
                }
            });
        }
    }


    /**
     * 获取通讯录联系人
     */
    private void getContacts() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.
                Phone.CONTENT_URI, null, null, null, null);
        try {
            // 查询联系人数据
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    // 拼接数据
                    list.add(phoneName + "\n" + phoneNumber);
                }
                // 刷新UI
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    //权限申请的返回处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}