package top.iqqcode.listviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.*;

/**
 * @Author: iqqcode
 * @Date: 2021/3/16
 * @Description:ListView练习
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView mListView;
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.获取ListView控件对象
        mListView = findViewById(R.id.lv_main);

        // 2.获取数据源
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            map.put("title", "Title-item");
            map.put("topic", "Topic section");
            map.put("time", "2021-03-16");
            map.put("logo", R.mipmap.ic_launcher);
            list.add(map);
        }

        // 3.配置Adapter
        MyAdapter adapter = new MyAdapter(this);
        adapter.setList(list);

        // 4.关联适配器
        mListView.setAdapter(adapter);

        // 5.监听item事件
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "点击item" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "长按item" + position, Toast.LENGTH_SHORT).show();
        return false;
    }
}