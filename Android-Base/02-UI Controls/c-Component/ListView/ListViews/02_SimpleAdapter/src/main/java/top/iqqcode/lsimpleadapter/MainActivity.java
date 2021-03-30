package top.iqqcode.lsimpleadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.*;

/**
 * @Author: iqqcode
 * @Date: 2021/3/15
 * @Description:SimpleAdapter
 */
public class MainActivity extends Activity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.lv_main);
        //准备集合数据
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        map.put("icon", R.drawable.image1);
        map.put("name", "name--1");
        map.put("content", "content--1");
        data.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.image2);
        map.put("name", "name--2");
        map.put("content", "content--2");
        data.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.image3);
        map.put("name", "name--3");
        map.put("content", "content--3");
        data.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.image5);
        map.put("name", "name--4");
        map.put("content", "content--4");
        data.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.image6);
        map.put("name", "name--5");
        map.put("content", "content--5");
        data.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.image7);
        map.put("name", "name--6");
        map.put("content", "content--6");
        data.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.image8);
        map.put("name", "name--7");
        map.put("content", "content--7");
        data.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.image9);
        map.put("name", "name--8");
        map.put("content", "content--8");
        data.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.image0);
        map.put("name", "name--9");
        map.put("content", "content--9");
        data.add(map);

        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.image10);
        map.put("name", "name--10");
        map.put("content", "content--10");
        data.add(map);

        //map对象中的key的数组, 用于得到对应的value
        String[] from = {"icon", "name", "content"};
        //Item布局文件中的子view的id的数组(顺序必须和String数组的内容对应)
        int[] to = {R.id.iv_item_icon, R.id.tv_item_name, R.id.tv_item_content};

        //准备SimpleAdapter对象
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item_simple_adapter, from, to);
        //设置Adapter显示列表
        mListView.setAdapter(simpleAdapter);
    }
}