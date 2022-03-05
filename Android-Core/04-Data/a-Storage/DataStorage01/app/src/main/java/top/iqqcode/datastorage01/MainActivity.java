package top.iqqcode.datastorage01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 测试sp存储
     * @param view
     */
    public void onClickSharedPreference(View view) {
        startActivity(new Intent(this, SpActivity.class));
    }

    /**
     * 测试手机内部文件存储
     * @param view
     */
    public void onClickInnerFile(View view) {
        startActivity(new Intent(this, InnerFileActivity.class));
    }

    /**
     * 测试手机外部文件存储
     * @param view
     */
    public void onClickOutFile(View view) {
        startActivity(new Intent(this, OutFileActivity.class));
    }

    /**
     * 测试SQLite数据库存储
     * @param view
     */
    public void onClickDataBase(View view) {
        startActivity(new Intent(this, DataBaseActivity.class));
    }

    /**
     * 测试网络获取
     * @param view
     */
    public void onClickNetWork(View view) {
        startActivity(new Intent(this, NetWorkActivity.class));
    }
}