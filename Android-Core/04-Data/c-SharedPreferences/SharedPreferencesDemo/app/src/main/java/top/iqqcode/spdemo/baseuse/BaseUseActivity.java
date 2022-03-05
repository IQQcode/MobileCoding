package top.iqqcode.spdemo.baseuse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import top.iqqcode.spdemo.R;

public class BaseUseActivity extends AppCompatActivity {

    private EditText mEditTextNote, mEditTextPhone;
    private final static String SPNOTEKEY = "spnotekey";
    private final static String SPPHONRKEY = "spphonekey";

    private SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseuse);

        initView();
        // 实例化SharedPreferences对象
        sharedPreferences = getApplicationContext().getSharedPreferences("shared_test", Context.MODE_PRIVATE);
    }

    private void initView() {
        mEditTextNote = findViewById(R.id.edit_text_notes);
        mEditTextPhone = findViewById(R.id.edit_text_phone);
    }

    // 还原数据
    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferences == null) {
            sharedPreferences = getApplicationContext().getSharedPreferences("shared_test", Context.MODE_PRIVATE);
            mEditTextNote.setText(sharedPreferences.getString(SPNOTEKEY, "null"));
            mEditTextPhone.setText(sharedPreferences.getString(SPPHONRKEY, "00000000000"));
        }
    }

    // 存储数据
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG", "onCheckedChanged: 点击状态发生改变");
        // 实例化编辑者对象
        SharedPreferences.Editor edit = sharedPreferences.edit();
        // 存储数据
        String note = mEditTextNote.getText().toString();
        String phoneNumber = mEditTextPhone.getText().toString();
        if (TextUtils.isEmpty(note) && TextUtils.isEmpty(phoneNumber)) {
            return;
        }
        edit.putString(SPNOTEKEY, note); // MD5加密
        edit.putString(SPPHONRKEY, phoneNumber);
        edit.commit();
    }
}