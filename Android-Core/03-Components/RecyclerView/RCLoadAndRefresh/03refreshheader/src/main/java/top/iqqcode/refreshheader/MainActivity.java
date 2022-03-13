package top.iqqcode.refreshheader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import top.iqqcode.refreshheader.adapter.CommonAdapter;
import uk.co.imallan.jellyrefresh.JellyRefreshLayout;
import uk.co.imallan.jellyrefresh.PullToRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private JellyRefreshLayout mJellyLayout;

    private CommonAdapter mAdapter;
    private List<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CommonAdapter(this);
        mAdapter.setData(mDataList); // 用set方法或者构造都可以将dataList传入Adapter中
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 点击item是，将该item的数据传递到跳转的NewActivity处
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("itemName", mDataList.get(position));
                startActivity(intent);
            }
        });

        mJellyLayout.setPullToRefreshListener(new PullToRefreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mJellyLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        View loadingView = LayoutInflater.from(this).inflate(R.layout.view_loading, null);
        mJellyLayout.setLoadingView(loadingView);
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
}