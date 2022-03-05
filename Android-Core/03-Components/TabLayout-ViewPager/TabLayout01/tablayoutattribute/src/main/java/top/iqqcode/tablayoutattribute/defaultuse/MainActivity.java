package top.iqqcode.tablayoutattribute.defaultuse;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import top.iqqcode.tablayoutattribute.R;
import top.iqqcode.tablayoutattribute.defaultuse.TabPagerAdapter;
import top.iqqcode.tablayoutattribute.defaultuse.TabFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[] {"最新", "热门", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        for (int i = 0; i < titles.length; i++) {
            // 添加Fragment
            fragments.add(new TabFragment());
            // 添加Tab标题
            tabLayout.addTab(tabLayout.newTab());
        }

        // ViewPager与TabLayout关联
        tabLayout.setupWithViewPager(viewPager, false);
        pagerAdapter = new TabPagerAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        for (int i = 0; i < titles.length; i++) {
            tabLayout.getTabAt(i).setText(titles[i]);
        }
    }

}
