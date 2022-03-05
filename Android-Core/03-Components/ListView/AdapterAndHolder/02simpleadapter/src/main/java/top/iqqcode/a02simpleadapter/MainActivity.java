package top.iqqcode.a02simpleadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: iqqcode
 * @Date: 2021/4/29
 * @Description:SimpleAdapter
 */
public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] mDataName = {"image01", "image02", "image03", "image04", "image05", "image06", "image07",
            "image08", "image09", "image10", "image11", "image12", "image13", "image14"};
    private int[] mDataImage = {R.mipmap.image_1, R.mipmap.image_2, R.mipmap.image_3, R.mipmap.image_4,
            R.mipmap.image_5, R.mipmap.image_6, R.mipmap.image_7, R.mipmap.image_8, R.mipmap.image_9,
            R.mipmap.image_10, R.mipmap.image_11, R.mipmap.image_12, R.mipmap.image_13, R.mipmap.image_14};

    private List<Map<String, String>> list; // 乘方数据源的容器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list_view);

        // 将水果图片和水果名称整合到一个map当中，最后将所有的水果都存放到ArrayList
        list = new ArrayList<>();
        Map<String, String> map = null;
        for (int i = 0; i < mDataName.length; i++) {
            map = new HashMap<>();
            map.put("name", mDataName[i]);
            map.put("image", mDataImage[i] + "");
            list.add(map);
        }
        String[] from = {"name", "image"};
        int[] to = {R.id.item_text, R.id.item_image};

        // 初始化Adapter
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item_view, from, to);
        mListView.setAdapter(adapter);

        // item点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), mDataName[i], Toast.LENGTH_LONG).show();
            }
        });
    }
}