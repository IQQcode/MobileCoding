package top.iqqcode.baseadapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.*;

/**
 * @Author: iqqcode
 * @Date: 2021/3/15
 * @Description:BaseAdapter
 */
public class MainActivity extends Activity {

    private ListView mListView;
    private List<ShopInfo> data;
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.lv_main);
        //准备集合数据
        data = new ArrayList<>();
        data.add(new ShopInfo(R.drawable.image1, "shop01", "content02"));
        data.add(new ShopInfo(R.drawable.image2, "shop02", "content03"));
        data.add(new ShopInfo(R.drawable.image3, "shop03", "content04"));
        data.add(new ShopInfo(R.drawable.image4, "shop04", "content05"));
        data.add(new ShopInfo(R.drawable.image5, "shop05", "content06"));
        data.add(new ShopInfo(R.drawable.image6, "shop06", "content07"));
        data.add(new ShopInfo(R.drawable.image7, "shop07", "content08"));
        data.add(new ShopInfo(R.drawable.image8, "shop08", "content09"));
        data.add(new ShopInfo(R.drawable.image9, "shop09", "content10"));
        data.add(new ShopInfo(R.drawable.image10, "shop10", "content10"));
        data.add(new ShopInfo(R.drawable.image11, "shop011", "content11"));
        data.add(new ShopInfo(R.mipmap.ic_launcher, "shop12", "content12"));
        data.add(new ShopInfo(R.mipmap.ic_launcher, "shop13", "content10"));
        data.add(new ShopInfo(R.mipmap.ic_launcher, "shop14", "content10"));
        data.add(new ShopInfo(R.mipmap.ic_launcher, "shop15", "content10"));


        //准备SimpleAdapter对象
        MyAdapter adapter = new MyAdapter();
        //设置Adapter显示列表
        mListView.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter {

        /**
         * 返回集合数据的count
         *
         * @return
         */
        @Override
        public int getCount() {
            Log.e(TAG, " ==> getCount() : size : " + data.size());
            return data.size();
        }

        /**
         * 返回指定下标对应的数据对象
         *
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        /**
         * 返回指定下标所对应的item的View对象
         * position : 下标
         * convertView : 可复用的缓存Item视图对象, 初始前 n+1 个为null，后面的开始复用
         * parent : ListView对象
         *
         * @param position
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            Log.e("TAG", "getView() *** position = " + position + " convertView = " + convertView);

            // content如果没有复用
            if (convertView == null) {
                Log.e("TAG", "getView() *** position = " + position + " convertView = " + convertView);
                // 加载item的布局, 得到View对象
                // 此处传递this是Adapter的对象
                convertView = View.inflate(MainActivity.this, R.layout.item_base_adapter, null);
            } else {
                // 根据position设置对应的数据
                // 得到当前行数据对象
                ShopInfo shopInfo = data.get(position);
                // 得到子View对象
                ImageView imageView = convertView.findViewById(R.id.iv_item_icon);
                TextView nameTV = convertView.findViewById(R.id.tv_item_name);
                TextView contentTV = convertView.findViewById(R.id.tv_item_content);
                //设置数据
                imageView.setImageResource(shopInfo.getIcon());
                nameTV.setText(shopInfo.getName());
                contentTV.setText(shopInfo.getContent());
            }
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}