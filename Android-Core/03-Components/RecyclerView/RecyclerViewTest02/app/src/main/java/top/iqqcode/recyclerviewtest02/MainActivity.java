package top.iqqcode.recyclerviewtest02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import top.iqqcode.recyclerviewtest02.adapter.ListViewAdapter;
import top.iqqcode.recyclerviewtest02.beans.Datas;
import top.iqqcode.recyclerviewtest02.beans.ItemBean;

/**
 * @Author: iqqcode
 * @Date: 2021/4/20
 * @Description:RecyclerView使用步骤【标准写法】
 * 1. 通过findViewById找到控件
 * 2. 准备好数据
 * 3. 设置布局管理器
 * 4. 创建适配器
 * 5. 设置适配码
 * 6. 数据就可显示出来了
 */
public class MainActivity extends AppCompatActivity {

    private final static String TAG = "TAG";

    private RecyclerView mRecyclerView;
    private List<ItemBean> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // ListView<DataBean> ---> Adapter ---> setAdapter ---> 显示数据
        list_data = new ArrayList<>();
        // 模拟数据
        for (int i = 0; i < Datas.icons.length; i++) {
            // 创建数据对象
            ItemBean data = new ItemBean();
            data.imageId = Datas.icons[i];
            data.title = "我是第" + i + "个item";
            list_data.add(data);
        }

        // RecyclerView需要设置样式，设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);

        // 创建适配器(处理数据，将数据恰当的显示在View上)
        ListViewAdapter adapter = new ListViewAdapter(this, list_data);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * Menu菜单条目
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Menu菜单被选中
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            // ListView部分
            case R.id.list_view_vertical_standard:
                Log.i(TAG, "点击了ListView的垂直标准");
                break;
            case R.id.list_view_vertical_reverse:
                break;
            case R.id.list_view_horizontal_standard:
                break;
            case R.id.list_view_horizontal_reverse:
                break;

            // GridView部分
            case R.id.grid_view_vertical_standard:
                break;
            case R.id.grid_view_vertical_reverse:
                break;
            case R.id.grid_view_horizontal_standard:
                break;
            case R.id.grid_view_horizontal_reverse:
                break;

            // 瀑布流部分
            case R.id.stagger_view_vertical_standard:
                break;
            case R.id.stagger_view_vertical_reverse:
                break;
            case R.id.stagger_view_horizontal_standard:
                break;
            case R.id.stagger_view_horizontal_reverse:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}