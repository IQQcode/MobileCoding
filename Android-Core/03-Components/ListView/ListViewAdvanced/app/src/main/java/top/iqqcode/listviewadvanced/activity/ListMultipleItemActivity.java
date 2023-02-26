package top.iqqcode.listviewadvanced.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import top.iqqcode.listviewadvanced.R;
import top.iqqcode.listviewadvanced.multiply.MultiplyAdapter;
import top.iqqcode.listviewadvanced.multiply.bean.BotData;
import top.iqqcode.listviewadvanced.multiply.bean.FriendData;

/**
 * @author jiazihui
 */
public class ListMultipleItemActivity extends AppCompatActivity {

    private ListView mListView;

    private MultiplyAdapter mAdapter;
    @NonNull
    private List<FriendData> mFriendList = new ArrayList<>();
    @NonNull
    private List<BotData> mBotList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_multiple_item);

        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        mListView = findViewById(R.id.multiplyList);
    }

    private void initData() {
        FriendData friendData01 = new FriendData(R.mipmap.ic_launcher_round, "@我的", R.mipmap.alipay_msp_arrow);
        FriendData friendData02 = new FriendData(R.mipmap.ic_launcher_round, "评论", R.mipmap.alipay_msp_arrow);
        FriendData friendData03 = new FriendData(R.mipmap.ic_launcher_round, "赞", R.mipmap.alipay_msp_arrow);
        mFriendList.add(friendData01);
        mFriendList.add(friendData02);
        mFriendList.add(friendData03);

        for (int i = 0; i < 10; i++) {
            BotData botData = new BotData(R.mipmap.ic_launcher, "小凡", "你好", "09:38");
            mBotList.add(botData);
        }
    }

    private void initAdapter() {
        mAdapter = new MultiplyAdapter(this);
        mAdapter.setData(mBotList, mFriendList);
        mListView.setAdapter(mAdapter);
    }
}