package top.iqqcode.a03baseadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, IClickButton {

    public final static String TAG = "TAG";

    private ListView mListView;

    private String[] mDataName = {"Twitter", "Youtube", "Telegram", "Google", "VPN", "Discord", "Steam",
            "Zoom", "SSR", "Twitch", "Tinder", "Instagram", "Adobe", "Via"};

    private int[] mDataImage = {R.mipmap.image_1, R.mipmap.image_2, R.mipmap.image_3, R.mipmap.image_4,
            R.mipmap.image_5, R.mipmap.image_6, R.mipmap.image_7, R.mipmap.image_8, R.mipmap.image_9,
            R.mipmap.image_10, R.mipmap.image_11, R.mipmap.image_12, R.mipmap.image_13, R.mipmap.image_14};

    private List<AppInfo> list;

    private AppInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list_view);

        list = new ArrayList<>();
        for (int i = 0; i < mDataName.length; i++) {
            info = new AppInfo();
            info.setAppIcon(mDataImage[i]);
            info.setAppName(mDataName[i]);
            info.setAppSize("大小: " + randSize(100) + "MB");
            list.add(info);
        }

        Log.i(TAG, "=====>AppInfo<======" + info.toString());

        MyBaseAdapter adapter = new MyBaseAdapter(this, list);
        adapter.setClickButton(this);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }

    public String randSize(int range) {
        Random random = new Random();
        return String.valueOf(random.nextInt(range) + 1);
    }


    /**
     * item点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), mDataName[position], Toast.LENGTH_LONG).show();
        // 获得所点击行的数据
        AppInfo itemInfo = (AppInfo) parent.getItemAtPosition(position);
        itemInfo.setAppName("iqqcode");
        itemInfo.setAppSize(randSize(100));
        Log.i(TAG, "---------onItemClick: itemInfo" + itemInfo.toString());

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("itemInfo", itemInfo);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * item长按点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     * @return
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), mDataName[position], Toast.LENGTH_LONG).show();
        // 获得所点击行的数据
        AppInfo itemInfo = (AppInfo) parent.getItemAtPosition(position);
        itemInfo.setAppName("iqqcode");
        itemInfo.setAppSize(randSize(100));
        Log.i(TAG, "---------onItemClick: itemInfo" + itemInfo.toString());

        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("itemInfo", itemInfo);
        intent.putExtras(bundle);
        startActivity(intent);
        return true; // 消化该点击事件
    }

    @Override
    public void clickButton(int position) {
        startActivity(new Intent(MainActivity.this, UninstallActivity.class));
    }
}