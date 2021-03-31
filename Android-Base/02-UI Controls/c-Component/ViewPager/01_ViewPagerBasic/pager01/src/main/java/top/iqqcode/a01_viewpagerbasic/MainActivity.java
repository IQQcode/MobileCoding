package top.iqqcode.a01_viewpagerbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2021/3/30
 * @Description:ViewPager学习
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<View> list;
    private MyPagerAdapter adapter;

    // 引导页点
    private ViewGroup mDotViewGroup;
    private List<ImageView> mDotViews = new ArrayList<>();

    private int[] images_page = {R.drawable.img01, R.drawable.img02,
            R.drawable.img03, R.drawable.img04, R.drawable.img05};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mDotViewGroup = findViewById(R.id.dot_layout);

        // 初始化数据(View)
        list = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        // 填充View
//        list.add(inflater.inflate(R.layout.view_one, null, false));
//        list.add(inflater.inflate(R.layout.view_two, null, false));
//        list.add(inflater.inflate(R.layout.view_three, null, false));

        // 填充image
        for (int i = 0; i < images_page.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images_page[i]);
            list.add(imageView);
            // 设置点
            ImageView dotView = new ImageView(this);
            dotView.setImageResource(R.drawable.design);
            dotView.setMaxWidth(100);
            dotView.setMaxHeight(100);
                // 点间隔
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
            params.leftMargin = 20;
            dotView.setLayoutParams(params);
            dotView.setEnabled(false);

            mDotViewGroup.addView(dotView);
            mDotViews.add(dotView);
        }
        // 设置Adapter
        adapter = new MyPagerAdapter(list);
        mViewPager.setAdapter(adapter);
        // 左右各两个page
        mViewPager.setOffscreenPageLimit(4);
        // 设置点默认为第一页
        mViewPager.setCurrentItem(0);
        setDotViews(0);

        // 每个PageView被滑动时监听的回调
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 被滚动时
             * @param position 当前位置
             * @param positionOffset 发生位移
             * @param positionOffsetPixels 位置偏移像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 刚好处在当前页(当前Page)
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                setDotViews(position);
            }

            /**
             * 滚动状态的变化(由滚动到不滚动或者反之)
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setDotViews(int position) {
        for (int i = 0; i < mDotViews.size(); i++) {
            mDotViews.get(i).setImageResource(position == i ? R.mipmap.ic_launcher_round:R.drawable.design);
        }
    }
}