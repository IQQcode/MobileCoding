package top.iqqcode.refreshloaddemo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.imallan.jellyrefresh.JellyRefreshLayout;
import uk.co.imallan.jellyrefresh.PullToRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private JellyRefreshLayout mJellyLayout;

    private LoadMoreAdapter mAdapter;
    private final List<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new LoadMoreAdapter(mDataList, this);
        mAdapter.setData(mDataList); // 用set方法或者构造都可以将dataList传入Adapter中
        mRecyclerView.setAdapter(mAdapter);

        itemClick();
        pullRefresh();
        loadMore();
    }

    private void pullRefresh() {
        View loadingView = LayoutInflater.from(this).inflate(R.layout.view_loading, null);
        mJellyLayout.setLoadingView(loadingView);
        mJellyLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                // 刷新数据
                // mDataList.clear();
                pullToRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mJellyLayout != null) {
                            mDataList.add(0, "add new data...");
                            mAdapter.notifyDataSetChanged();
                            mJellyLayout.setRefreshing(false);
                        }
                    }
                }, 1000);
            }
        });
    }

    private void loadMore() {
        // 设置加载更多监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            // 用来标记是否正在向上滑动
            boolean isSlidingUpward = false;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (manager != null) {
                    // 当不滑动时
                    switch (newState) {
                        //静止状态
                        case RecyclerView.SCROLL_STATE_IDLE:
                            // 获取最后一个完全显示的itemPosition
                            int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                            int itemCount = manager.getItemCount();
                            // 判断是否滑动到了最后一个item，并且是向上滑动
                            if (lastItemPosition == (itemCount - 1) && isSlidingUpward) {
                                // 加载更多
                                onLoadMore();
                            }
                            break;
                        // 拖动状态，手指按在屏幕上拖动时的状态
                        case RecyclerView.SCROLL_STATE_DRAGGING:
                            break;
                        // 惯性滑动
                        case RecyclerView.SCROLL_STATE_SETTLING:
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
                /**
                 * 当向上滑动的时候dy > 0
                 * 向左滑动的时候dx > 0的; 反方向滑动则小于0 适用于横向滑动列表的监听
                 */
                isSlidingUpward = dy > 0;
            }
        });
    }

    private void onLoadMore() {
        mAdapter.setLoadState(LoadMoreAdapter.LOADING);
        if (mDataList.size() < 45) {
            // 模拟获取网络数据，延时1s
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getLoadMoreData();
                            mAdapter.setLoadState(LoadMoreAdapter.LOADING_COMPLETE);
                        }
                    });
                }
            }, 1000);
        } else {
            // 显示加载到底的提示
            mAdapter.setLoadState(LoadMoreAdapter.LOADING_END);
        }
    }

    private void itemClick() {
        mAdapter.setOnItemClickListener(new LoadMoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 点击item是，将该item的数据传递到跳转的NewActivity处
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("itemName", mDataList.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mJellyLayout = findViewById(R.id.refresh_view);
    }

    private void initData() {
        char letter = 'A';
        for (int i = 0; i < 26; i++) {
            mDataList.add(String.valueOf(letter));
            letter++;
        }
    }

    private void getLoadMoreData() {
        char letter = 'a';
        for (int i = 0; i < 5; i++) {
            mDataList.add(String.valueOf(letter));
            letter++;
        }
    }
}
