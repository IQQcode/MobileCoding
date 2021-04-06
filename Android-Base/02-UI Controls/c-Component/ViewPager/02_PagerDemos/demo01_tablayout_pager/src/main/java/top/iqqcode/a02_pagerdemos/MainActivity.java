package top.iqqcode.a02_pagerdemos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2021/3/31
 * @Description: ViewPager + TabLayout 结合使用
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TextView mTextView;
    private TabLayout mTabLayout;

    private List<BaseFragment> fragments;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mTextView = findViewById(R.id.tv_title);
        mTabLayout = findViewById(R.id.tab_layout);

        // 初始化数据
        fragments = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            fragments.add(new BaseFragment("标题" + index, "内容" + index));
        }

        // 设置FragmentAdapter
        adapter = new MyAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);

        // 关联ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}