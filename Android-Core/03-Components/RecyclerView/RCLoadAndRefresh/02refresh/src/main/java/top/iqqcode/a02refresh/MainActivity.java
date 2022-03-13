package top.iqqcode.a02refresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2022/3/5
 * @Description: RecyclerView上手使用
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private CommonAdapter mAdapter;
    private List<ItemData> dataList = new ArrayList<>();

    private boolean isRefresh = false; // 是否刷新中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CommonAdapter(this);
        mAdapter.setData(dataList); // 用set方法或者构造都可以将dataList传入Adapter中
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 点击item是，将该item的数据传递到跳转的NewActivity处
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("itemName", dataList.get(position).getName());
                startActivity(intent);
            }
        });

        pullRefresh();
    }

    private void pullRefresh() {
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mSwipeLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设定下拉圆圈的背景
        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小

        //设置下拉刷新的监听
        mSwipeLayout.setOnRefreshListener(MainActivity.this);
    }

    /**
     * 当下拉刷新后触发
     */
    @Override
    public void onRefresh() {
        mSwipeLayout.setEnabled(true);
        // 检查是否处于刷新状态
        if (!isRefresh) {
            isRefresh = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 显示或隐藏刷新进度条
                    mSwipeLayout.setRefreshing(false);
                    // 修改adapter的数据
                    dataList.add(0, new ItemData("这是新添加的数据", R.mipmap.ic_launcher));
                    mAdapter.notifyDataSetChanged();
                    mSwipeLayout.setEnabled(false);

                }
            }, 4000);
        }
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mSwipeLayout = findViewById(R.id.swipeLayout);
        // 设置进度条的颜色主题，最多能设置四种 加载颜色是循环播放的，只要没有完成刷新就会一直循环
        mSwipeLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
    }

    private void initData() {
        if (dataList != null) {
            ItemData apple = new ItemData("Apple", R.mipmap.ic_launcher);
            dataList.add(apple);
            ItemData banana = new ItemData("Banana", R.mipmap.ic_launcher);
            dataList.add(banana);
            ItemData orange = new ItemData("Orange", R.mipmap.ic_launcher);
            dataList.add(orange);
        }
    }
}