package com.iqqcode.controlview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        //1.自定义内部类
        Button button1 = findViewById(R.id.btn1);
        //点击事件: 被点击时触发的事件
        MyClickListener myClickListener = new MyClickListener();
        // 为按钮注册点击事件监听器(自定义内部类)
        button1.setOnClickListener(myClickListener);

        //2.匿名内部类
        Button button2 = findViewById(R.id.btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "匿名内部类");
            }
        });

        //3.当前Activity实现OnClickListener事件接口
        Button button3 = findViewById(R.id.btn3);
        button3.setOnClickListener(this);

        //4.在XML布局文件中添加点击事件属性
    }

    //1.自定义内部类
    class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.e("TAG", "刚刚点击的按钮注册了内部类监听器");
        }
    }

    // 3.当前Activity实现OnClickListener事件接口
    @Override
    public void onClick(View v) {
        Log.e("TAG", "当前Activity实现OnClickListener事件接口");
    }

    // 4.在XML布局文件中添加点击事件属性
    //被点击的控件对象
    public void myClick(View v) {
        switch (v.getId()) {
            case R.id.btn4:
                Log.e("TAG", "btn4通过XML绑定的点击事件...");
                break;
            case R.id.btn5:
                Log.e("TAG", ">>>>>btn5-------");
                break;
            default:
                break;
        }
    }
}