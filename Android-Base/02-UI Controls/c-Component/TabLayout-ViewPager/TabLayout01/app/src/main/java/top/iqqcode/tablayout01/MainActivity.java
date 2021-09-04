package top.iqqcode.tablayout01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<TabFragment> mContainer;
    private TabPagerAdapter mAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mContainer = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            mContainer.add(new TabFragment("标题" + i, "内容 " + i));
        }
        mAdapter = new TabPagerAdapter(getSupportFragmentManager(), mContainer);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager); //与ViewPage建立关系
        // mTabLayout.setTabMode(TabLayout.MODE_FIXED); // 全部显示
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);
    }
}