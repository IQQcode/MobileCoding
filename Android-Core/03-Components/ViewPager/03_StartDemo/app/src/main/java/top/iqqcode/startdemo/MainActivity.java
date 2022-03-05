package top.iqqcode.startdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int[] data = {R.mipmap.img01, R.mipmap.img02, R.mipmap.img03, R.mipmap.img04, R.mipmap.img05, R.mipmap.img06};

    private ViewPager mViewPager;
    private LinearLayout layout;

    List<ImageView> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        setListener();
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    /**
     * 初始化视图，将图片添加到视图中
     */
    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        layout = findViewById(R.id.layout);
        for (int index : data) {
            ImageView imageView = new ImageView(this);
            // setBackgroundResource 会自动填充满容器
            imageView.setImageResource(index);
            // 添加到视图中
            list.add(imageView);
        }
    }

    /**
     * 加载导航点
     */
    private void initData() {

        // 0.新建ViewPager
        // 1.创建适配器
        // 2.设置数据适配器
        mViewPager.setAdapter(new MyPagerAdapter());

        // 加载导航点
        for (int i = 0; i < data.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.page_off);
            LinearLayout.LayoutParams layoutParams = new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置小圆点的间距
            layoutParams.setMargins(8, 0, 8, 0);

            layout.addView(imageView, layoutParams);
        }
    }

    /**
     * 页面切换事件（添加下标点）
     */
    private void setListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * 当页面滚动了触发
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < data.length; i++) {
                    ImageView imageView = (ImageView) layout.getChildAt(i);
                    if (i == position) {
                        // 点亮
                        imageView.setImageResource(R.drawable.page_on);
                    } else {
                        imageView.setImageResource(R.drawable.page_off);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 配置当手动滑动的时候，停止自动滑动
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    handler.removeCallbacksAndMessages(null);
                }


                // 当空闲时，会再次开始自动滑动
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    // 清空消息队列
                    handler.removeCallbacksAndMessages(null);
                    handler.sendEmptyMessageDelayed(1, 2000);
                }
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // 添加处理消息通知的代码
            if (msg.what == 1) {
                // 如何到了最后一页，就跳转回第一页
                if (mViewPager.getCurrentItem() == data.length - 1) {
                    mViewPager.setCurrentItem(0);
                } else {
                    // 跳转到下一页内容
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                }

            }
            handler.sendEmptyMessageDelayed(1, 2000);
        }
    };

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            // 1. 将数据添加到布局文件中
            container.addView(list.get(position));
            // 2. 数据返回
            return list.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            // 移除划过的的视图
            container.removeView(list.get(position));
        }

    }
}