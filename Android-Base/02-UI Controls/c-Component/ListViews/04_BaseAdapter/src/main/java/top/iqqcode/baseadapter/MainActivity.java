package top.iqqcode.baseadapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, IClickButton {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 获取ListView对象
        ListView listview = findViewById(R.id.lv_main);

        //2. 准备数据源
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("logo", R.mipmap.ic2);
        map.put("title", "千千静听");
        map.put("version", "版本: 8.4.0");
        map.put("size", "大小: 32.81M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic3);
        map.put("title", "时空猎人");
        map.put("version", "版本: 2.4.1");
        map.put("size", "大小: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic4);
        map.put("title", "360新闻");
        map.put("version", "版本: 6.2.0");
        map.put("size", "大小: 11.74M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic5);
        map.put("title", "捕鱼达人");
        map.put("version", "版本: 2.3.0");
        map.put("size", "大小: 45.53M");
        list.add(map);

        // 第2次测试数据
        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic6);
        map.put("title", "Twitter");
        map.put("version", "版本: 8.4.0");
        map.put("size", "大小: 32.81M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic7);
        map.put("title", "Facebook");
        map.put("version", "版本: 2.4.1");
        map.put("size", "大小: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic9);
        map.put("title", "腾讯新闻");
        map.put("version", "版本: 6.2.0");
        map.put("size", "大小: 11.74M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic11);
        map.put("title", "录音");
        map.put("version", "版本: 2.3.0");
        map.put("size", "大小: 45.53M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic12);
        map.put("title", "电话");
        map.put("version", "版本: 8.4.0");
        map.put("size", "大小: 32.81M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic13);
        map.put("title", "Phone");
        map.put("version", "版本: 2.4.1");
        map.put("size", "大小: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic14);
        map.put("title", "抖音");
        map.put("version", "版本: 6.2.0");
        map.put("size", "大小: 11.74M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic15);
        map.put("title", "快手");
        map.put("version", "版本: 2.3.0");
        map.put("size", "大小: 45.53M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic7);
        map.put("title", "Facebook");
        map.put("version", "版本: 2.4.1");
        map.put("size", "大小: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic9);
        map.put("title", "腾讯新闻");
        map.put("version", "版本: 6.2.0");
        map.put("size", "大小: 11.74M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic11);
        map.put("title", "录音");
        map.put("version", "版本: 2.3.0");
        map.put("size", "大小: 45.53M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic7);
        map.put("title", "Facebook");
        map.put("version", "版本: 2.4.1");
        map.put("size", "大小: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic9);
        map.put("title", "腾讯新闻");
        map.put("version", "版本: 6.2.0");
        map.put("size", "大小: 11.74M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic11);
        map.put("title", "录音");
        map.put("version", "版本: 2.3.0");
        map.put("size", "大小: 45.53M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic13);
        map.put("title", "Phone");
        map.put("version", "版本: 2.4.1");
        map.put("size", "大小: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic14);
        map.put("title", "抖音");
        map.put("version", "版本: 6.2.0");
        map.put("size", "大小: 11.74M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic15);
        map.put("title", "快手");
        map.put("version", "版本: 2.3.0");
        map.put("size", "大小: 45.53M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic7);
        map.put("title", "Facebook");
        map.put("version", "版本: 2.4.1");
        map.put("size", "大小: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic13);
        map.put("title", "Phone");
        map.put("version", "版本: 2.4.1");
        map.put("size", "大小: 84.24M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic14);
        map.put("title", "抖音");
        map.put("version", "版本: 6.2.0");
        map.put("size", "大小: 11.74M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic15);
        map.put("title", "快手");
        map.put("version", "版本: 2.3.0");
        map.put("size", "大小: 45.53M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.mipmap.ic7);
        map.put("title", "Facebook");
        map.put("version", "版本: 2.4.1");
        map.put("size", "大小: 84.24M");
        list.add(map);

        // 3. 配置适配器
        MyAdapter adapter = new MyAdapter(this);
        adapter.setList(list);
        adapter.setClickButton(this);

        //4. 关联适配器
        listview.setAdapter(adapter);

        //5. 监听item事件
        listview.setOnItemClickListener(this);
        listview.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "点击item" + position, Toast.LENGTH_SHORT).show();

        // 获得所点击行的数据(Map)
        Map<String, Object> itemMap = (HashMap<String, Object>) parent.getItemAtPosition(position);
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("index", " " + position);
        bundle.putString("title", "" + itemMap.get("title"));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "长按item" + position, Toast.LENGTH_SHORT).show();
        return true; // 消化该点击事件
    }


    /**
     * item Button的点击事件
     *
     * @param position
     */
    @Override
    public void clickButton(int position) {
        startActivity(new Intent(MainActivity.this, UninstallActivity.class));
    }
}