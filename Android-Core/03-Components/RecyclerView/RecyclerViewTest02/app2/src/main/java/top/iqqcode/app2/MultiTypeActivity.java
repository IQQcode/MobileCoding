package top.iqqcode.app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import top.iqqcode.app2.R;
import top.iqqcode.app2.adapter.MoreTypeAdapter;
import top.iqqcode.app2.beans.Datas;
import top.iqqcode.app2.beans.MoreTypeBean;

/**
 * @Author: iqqcode
 * @Date: 2021/4/22
 * @Description:item的多种条目
 */
public class MultiTypeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MoreTypeBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);

        initView();

        initData();

        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // 创建适配器
        MoreTypeAdapter adapter = new MoreTypeAdapter(list);
        // 设置适配器
        mRecyclerView.setAdapter(adapter);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_multi_item);
    }

    private void initData() {
        list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < Datas.icons.length; i++) {
            MoreTypeBean data = new MoreTypeBean();
            data.imageId = Datas.icons[i];
            data.type = random.nextInt(3);
            Log.i("TAG", "initData: type == " + data.type);
            list.add(data);
        }
    }

}