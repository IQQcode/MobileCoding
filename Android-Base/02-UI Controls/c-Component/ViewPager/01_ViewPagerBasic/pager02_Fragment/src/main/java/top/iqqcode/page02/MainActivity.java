package top.iqqcode.page02;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    private ViewPager mViewPager;
    private TabHost mTabHost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化总布局
        mTabHost = findViewById(R.id.tab_host);
        mTabHost.setup();

        // 1. init data
        int[] titleIDs = {
                R.string.home,
                R.string.message,
                R.string.me
        };

        int[] drawableIDs = {
                R.drawable.main_tab_icon_home,
                R.drawable.main_tab_icon_message,
                R.drawable.main_tab_icon_me
        };

        // 三个Tab做处理: data < -- > view
        for (int i = 0; i < titleIDs.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.main_tab_layout, null, false);

            ImageView icon = view.findViewById(R.id.main_tab_icon);
            TextView title = view.findViewById(R.id.main_tab_txt);
            View tab = view.findViewById(R.id.tab_bg);

            icon.setImageResource(drawableIDs[i]);
            title.setText(getString(titleIDs[i]));
            tab.setBackgroundColor(getResources().getColor(R.color.white));

            mTabHost.addTab(
                    mTabHost.newTabSpec(getString(titleIDs[i]))
                            .setIndicator(view)
                            .setContent(this)
            );
        }

        // Fragment组成ViewPager
        final Fragment[] fragments = new Fragment[]{
                BaseFragment.newInstance("Home"),
                BaseFragment.newInstance("Message"),
                BaseFragment.newInstance("Me")
        };

        mViewPager = findViewById(R.id.view_pager_tab);
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
            public void onTabChanged(String s) {
                if (mTabHost != null) {
                    int position = mTabHost.getCurrentTab();
                    mViewPager.setCurrentItem(position);
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