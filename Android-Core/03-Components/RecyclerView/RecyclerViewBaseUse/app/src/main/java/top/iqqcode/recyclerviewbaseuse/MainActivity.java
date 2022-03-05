package top.iqqcode.recyclerviewbaseuse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2022/3/5
 * @Description: RecyclerView上手使用
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CommonAdapter mAdapter;
    private List<ItemData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // 添加分割线(背景色为RecyclerView的背景色，可自定义)
        //mRecyclerView.addItemDecoration(new ItemDecoration());
        mAdapter = new CommonAdapter(this);
        mAdapter.setData(dataList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("itemName", dataList.get(position).getName());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            ItemData apple = new ItemData("Apple", R.mipmap.ic_launcher);
            dataList.add(apple);
            ItemData banana = new ItemData("Banana", R.mipmap.ic_launcher);
            dataList.add(banana);
            ItemData orange = new ItemData("Orange", R.mipmap.ic_launcher);
            dataList.add(orange);
            ItemData watermelon = new ItemData("Watermelon", R.mipmap.ic_launcher);
            dataList.add(watermelon);
            ItemData pear = new ItemData("Pear", R.mipmap.ic_launcher);
            dataList.add(pear);
            ItemData grape = new ItemData("Grape", R.mipmap.ic_launcher);
            dataList.add(grape);
            ItemData pineapple = new ItemData("Pineapple", R.mipmap.ic_launcher);
            dataList.add(pineapple);
            ItemData strawberry = new ItemData("Strawberry", R.mipmap.ic_launcher);
            dataList.add(strawberry);
            ItemData cherry = new ItemData("Cherry", R.mipmap.ic_launcher);
            dataList.add(cherry);
            ItemData mango = new ItemData("Mango", R.mipmap.ic_launcher);
            dataList.add(mango);
        }
    }

    class ItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.itemDivider));
        }
    }
}