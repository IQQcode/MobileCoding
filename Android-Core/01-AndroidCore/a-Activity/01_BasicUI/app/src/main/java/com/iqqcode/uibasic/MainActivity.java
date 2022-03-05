package com.iqqcode.uibasic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    // Activity-可视化界面
    // public class XxxActivity extends Activity { }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置内容视图
        // 通过R.layout索引将 activity_main.xml 布局通过setContentView()设置到 MainActivity上

        // 1. 根布局为线性布局
        LinearLayout linearLayout = new LinearLayout(this);
        // 2. 设置宽高
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        // 3. 背景为红色
        linearLayout.setBackgroundColor(Color.RED);
        // 4. 指定此Activity的内容视图为该线性布局
        setContentView(linearLayout);
    }
}