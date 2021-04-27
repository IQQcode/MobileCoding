package top.iqqcode.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import top.iqqcode.app2.adapter.GridViewAdapter;
import top.iqqcode.app2.adapter.ListViewAdapter;
import top.iqqcode.app2.adapter.RecyclerBaseAdapter;
import top.iqqcode.app2.adapter.StaggeredGridAdapter;
import top.iqqcode.app2.beans.Datas;
import top.iqqcode.app2.beans.ItemBean;

/**
 * @Author: iqqcode
 * @Date: 2021/4/21
 * @Description: TODO: item点击事件
 * TODO: 优化
 */
public class MainActivity extends AppCompatActivity {

    private final static String TAG = "TAG";

    private RecyclerView mRecyclerView;
    private List<ItemBean> list_data;

    private RecyclerBaseAdapter adapter;
    private SwipeRefreshLayout mRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        // 设置默认显示的效果(垂直-正向)
        showStagger(true, false);

        // 处理下拉刷新
        handlerDownPullUpdate();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.swipe_refresh);
    }

    private void initData() {
        list_data = new ArrayList<>();
        for (int i = 0; i < Datas.icons.length; i++) {
            ItemBean data = new ItemBean();
            data.imageId = Datas.icons[i];
            data.title = "我是第" + i + "个item";
            list_data.add(data);
        }
    }

    private void initListener() {
        // ListView、GridView、StaggerView 的item 点击事件
        adapter.setOnItemClickListener(new RecyclerBaseAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Log.e("TAG", "MainActivity click");
                Toast.makeText(MainActivity.this, "点击了第" + position + "个条目", Toast.LENGTH_SHORT).show();
            }
        });

        // ListView处理上拉加载数据
        if (adapter instanceof ListViewAdapter) {
            ((ListViewAdapter) adapter).setOnRefreshListener(new ListViewAdapter.OnRefreshListener() {
                @Override
                public void onUpPullRefresh(final ListViewAdapter.LoadMoreHolder loadMoreHolder) {
                    // 加载数据,需要在子线程中完成
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Random random = new Random();
                            if (random.nextInt() % 2 == 0) {
                                ItemBean data = new ItemBean();
                                data.title = "我是新添加的数据...";
                                data.imageId = refreshRandomItem(Datas.icons);
                                list_data.add(data);

                                // 这里要做两件事: 一是停止刷新; 而是更新列表
                                adapter.notifyDataSetChanged();
                                loadMoreHolder.updateLoadingBottom(ListViewAdapter.LoadMoreHolder.LOAD_STATE_NORMAL);
                            } else {
                                loadMoreHolder.updateLoadingBottom(ListViewAdapter.LoadMoreHolder.LOAD_STATE_RELOAD);
                            }
                        }
                    }, 3000);
                }
            });
        }
    }

    private void handlerDownPullUpdate() {
        // 转态可用
        mRefreshLayout.setEnabled(true);
        // 下拉圈颜色
        mRefreshLayout.setColorSchemeResources(R.color.color_one, R.color.color_two,
                R.color.color_three, R.color.color_four, R.color.color_five);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 此处执行属性数据的操作(顶部下拉时，此方法被触发)
                /**
                 * 这个方法是MainThread是主线程，不可以执行耗时操
                 * 一般来说，我们去请求数据在开一个线程去获取
                 * 这里面演示的话，我直接添加一条数据
                 */
                // 添加数据
                ItemBean data = new ItemBean();
                data.title = "我是新添加的数据...";
                data.imageId = refreshRandomItem(Datas.icons);
                list_data.add(0, data);
                // 更新UI
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 更新列表
                        adapter.notifyDataSetChanged();
                        // 让刷新停止
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    /**
     * Menu菜单条目
     *
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
     *
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
                showList(true, false);
                break;
            case R.id.list_view_vertical_reverse:
                showList(true, true);
                break;
            case R.id.list_view_horizontal_standard:
                showList(false, false);
                break;
            case R.id.list_view_horizontal_reverse:
                showList(false, true);
                break;

            // GridView部分
            case R.id.grid_view_vertical_standard:
                showGrid(true, false);
                Log.i(TAG, "点击了GridView的垂直标准");
                break;
            case R.id.grid_view_vertical_reverse:
                showGrid(true, true);
                break;
            case R.id.grid_view_horizontal_standard:
                showGrid(false, false);
                break;
            case R.id.grid_view_horizontal_reverse:
                showGrid(false, true);
                break;

            // 瀑布流部分
            case R.id.stagger_view_vertical_standard:
                showStagger(true, false);
                Log.i(TAG, "点击了StaggeredView的垂直标准");
                break;
            case R.id.stagger_view_vertical_reverse:
                showStagger(true, true);
                break;
            case R.id.stagger_view_horizontal_standard:
                showStagger(false, false);
                break;
            case R.id.stagger_view_horizontal_reverse:
                showStagger(false, true);
                break;

            // 多种条目类型被点击了
            case R.id.multi_type:
                Log.i(TAG, "点击了item的多种条目");
                startActivity(new Intent(MainActivity.this, MultiTypeActivity.class));

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * ListView样式控制
     *
     * @param isVertical 布局管理器来控制水平还是垂直
     * @param isReverse  是否反转
     */
    private void showList(boolean isVertical, boolean isReverse) {
        // RecyclerView需要设置样式，设置布局管理器来控制
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        // 设置水平还是垂直( HORIZONTAL | VERTICAL )
        linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

        // 设置标准还是反向
        linearLayoutManager.setReverseLayout(isReverse);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        // 创建适配器(处理数据，将数据恰当的显示在View上)
        adapter = new ListViewAdapter(list_data);
        mRecyclerView.setAdapter(adapter);

        // 初始化事件
        initListener();
    }

    /**
     * GridView样式控制
     *
     * @param isVertical 布局管理器来控制水平还是垂直
     * @param isReverse  是否反转
     */
    private void showGrid(boolean isVertical, boolean isReverse) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
        gridLayoutManager.setReverseLayout(isReverse);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        adapter = new GridViewAdapter(list_data);
        mRecyclerView.setAdapter(adapter);

        // 初始化事件
        initListener();
    }


    /**
     * StaggeredView样式控制
     *
     * @param isVertical 布局管理器来控制水平还是垂直
     * @param isReverse  是否反转
     */
    private void showStagger(boolean isVertical, boolean isReverse) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new StaggeredGridAdapter(list_data);
        mRecyclerView.setAdapter(adapter);

        // 初始化事件
        initListener();
    }

    /**
     * 随机item数组
     *
     * @param icons
     * @return
     */
    private int refreshRandomItem(int[] icons) {
        Random random = new Random();
        return icons[random.nextInt(60)];
    }

}