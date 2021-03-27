package top.iqqcode.callback04;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2021/3/27
 * @Description:Fragment与其所附着的Activity之间（或其他Fragment)的通信最佳方式
 * 1. 将一个应用拆分成两个Fragment来构建UI
 * 2. 两个Fragment之间的通信
 * 3. ListFragment
 */
public class MainActivity extends AppCompatActivity implements NewItemFragment.OnNewItemAddListener {

    private List<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {
        list = new ArrayList<>();

        // 构建适配器
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        // Activity中获得其管辖的Fragment
        ToDoListFragment fragment = (ToDoListFragment) getFragmentManager().findFragmentById(R.id.fg_todo_list);
        fragment.setListAdapter(adapter);
    }

    @Override
    public void onNewItemAdd(String content) {
        // 加入数据源
        list.add(content);
        // 更新UI
        adapter.notifyDataSetChanged();
    }
}