package top.iqqcode.listviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @Author: iqqcode
 * @Date: 2021/3/15
 * @Description:显示文本列表 (ListView + ArrayAdapter)
 */
public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.lv_main);

        // 准备集合数据
        String[] data = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        // 准备ArrayAdapter对象
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_array_adapter, data);
        // 设置Adapter显示列表
        mListView.setAdapter(adapter);
    }
}