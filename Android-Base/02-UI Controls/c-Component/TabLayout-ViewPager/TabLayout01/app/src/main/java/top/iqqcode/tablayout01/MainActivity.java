package top.iqqcode.tablayout01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<DemoFragment> mContainer;
    private DemoAdapter mAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mContainer = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            mContainer.add(new DemoFragment("标题" + i, "内容 " + i));
        }
        mAdapter = new DemoAdapter(getSupportFragmentManager(), mContainer);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        // mTabLayout.setTabMode(TabLayout.MODE_FIXED); // 全部显示
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);
    }
}