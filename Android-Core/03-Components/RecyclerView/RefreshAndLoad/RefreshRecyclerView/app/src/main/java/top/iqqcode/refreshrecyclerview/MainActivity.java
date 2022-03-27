package top.iqqcode.refreshrecyclerview;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import top.iqqcode.refreshrecyclerview.adapter.CommonAdapter;
import top.iqqcode.refreshrecyclerview.interfaces.OnRefreshListener;
import top.iqqcode.refreshrecyclerview.view.RefreshHeaderRecyclerView;

/**
 * @Author: iqqcode
 * @Date: 2022/3/13
 * @Description:【问题】: 第一个Item会丢失
 * @Link:https://juejin.cn/post/6844903518428479496
 */
public class MainActivity extends AppCompatActivity {

    private RefreshHeaderRecyclerView mRefreshRecyclerView;
    private CommonAdapter mAdapter;
    private List<String> mDataList = new ArrayList<>();
    private static final int FINISH = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FINISH) {
                Toast.makeText(MainActivity.this, "刷新完成！", Toast.LENGTH_SHORT).show();
                mRefreshRecyclerView.refreshComplete();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRefreshRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CommonAdapter(this);
        mAdapter.setData(mDataList); // 用set方法或者构造都可以将dataList传入Adapter中
        mRefreshRecyclerView.setAdapter(mAdapter);
        mRefreshRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();//模拟数据的请求
            }
        });
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
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
        mRefreshRecyclerView = findViewById(R.id.recycler_view);
    }

    private void requestData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.toString();
                try {
                    Thread.sleep(1500);
                    Message message = Message.obtain();
                    message.what = FINISH;
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void initData() {
        char letter = 'A';
        for (int i = 0; i < 26; i++) {
            mDataList.add(String.valueOf(letter));
            letter++;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(null);
    }
}