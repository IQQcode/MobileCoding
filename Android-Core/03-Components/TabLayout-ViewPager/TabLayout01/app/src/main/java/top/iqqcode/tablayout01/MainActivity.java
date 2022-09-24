package top.iqqcode.tablayout01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiazihui
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<TabFragment> mContainerList;
    private TabPagerAdapter mAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mContainerList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            mContainerList.add(new TabFragment("标题" + i, "内容 " + i));
        }
        mAdapter = new TabPagerAdapter(getSupportFragmentManager(), mContainerList);
        mViewPager.setAdapter(mAdapter);
        // TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);
        // mTabLayout.setTabMode(TabLayout.MODE_FIXED); // 全部显示
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置小红点
//        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
//            View tabNumView = getTabView(i);
//            /**在这里判断每个TabLayout的内容是否有更新，来设置小红点是否显示**/
//            mTabLayout.getTabAt(i).setCustomView(tabNumView);
//        }
        mTabLayout.getTabAt(0).setCustomView(R.layout.item_tab_layout);

        //添加tabLayout选中监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置选中时的文字颜色
                if (tab.getCustomView() != null) {
                    getTabView(0).setTextColor(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //设置未选中时的文字颜色
                if (tab.getCustomView() != null) {
                    getTabView(0).setTextColor(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);
    }

    public TextView getTabView(int position) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.item_tab_layout, null);
        TextView tabTitle = (TextView) tabView.findViewById(R.id.tvTabTitle);
        return tabTitle;
    }
}