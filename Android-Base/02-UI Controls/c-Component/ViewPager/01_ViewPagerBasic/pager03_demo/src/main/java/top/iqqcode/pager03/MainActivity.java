package top.iqqcode.pager03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    private ViewPager mViewPager;
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化总布局
        mTabHost = findViewById(R.id.tab_host);
        mTabHost.setup();

        // 1. init data
        int[] titleIDs = {
                R.string.home,
                R.string.fun,
                R.string.ding,
                R.string.setting
        };

        int[] drawableIDs = {
                R.drawable.bottom_index_shape,
                R.drawable.bottom_college_shape,
                R.drawable.bottom_make_any_shape,
                R.drawable.bottom_my_shape,
        };

        // Tab做处理: data < -- > view
        for (int index = 0; index < titleIDs.length; index++) {
            View view = getLayoutInflater().inflate(R.layout.main_tab_layout, null, false);

            ImageView icon = view.findViewById(R.id.main_tab_icon);
            TextView title = view.findViewById(R.id.main_tab_txt);
            View tab = view.findViewById(R.id.tab_bg);

            icon.setImageResource(drawableIDs[index]);
            title.setText(getString(titleIDs[index]));
            tab.setBackgroundColor(getResources().getColor(R.color.white));

            mTabHost.addTab(
                    mTabHost.newTabSpec(getString(titleIDs[index]))
                            .setIndicator(view)
                            .setContent(this)
            );
        }

        // Fragment组成ViewPager
        final Fragment[] fragments = new Fragment[]{
                BaseFragment.newInstance("Home"),
                BaseFragment.newInstance("Fun"),
                BaseFragment.newInstance("Ding"),
                BaseFragment.newInstance("Setting")
        };

        mViewPager = findViewById(R.id.view_pager_tab);
        // 设置在空闲状态下视图层次结构中应保留到当前页面任一侧的页面数
        mViewPager.setOffscreenPageLimit(fragments.length);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mTabHost != null) {
                    mTabHost.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 切换Tab,ViewPager也动
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (mTabHost != null) {
                    int pos = mTabHost.getCurrentTab();
                    mViewPager.setCurrentItem(pos);
                }
            }
        });
    }

    @Override
    public View createTabContent(String tag) {
        View view = new View(this);
        view.setMinimumHeight(0);
        view.setMinimumWidth(0);
        return view;
    }
}