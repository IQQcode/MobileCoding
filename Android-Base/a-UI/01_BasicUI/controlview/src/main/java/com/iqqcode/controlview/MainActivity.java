package com.iqqcode.controlview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View v) {
        //1.判断用户名、密码是否为空
        EditText userText = findViewById(R.id.name);
        EditText pwdText = findViewById(R.id.pwd);
        ProgressBar pb = findViewById(R.id.pro_bar);
        String name = userText.getText().toString(); // 可编辑文本变为字符串
        String pwd = pwdText.getText().toString();
        if(name.equals("") || name == null || pwd.equals("") || pwd == null) {
            //2.为空给出提示
            // 无焦点提示 (不会影响交互，提示过一会自动消失)
            // [3个参数]: 上下文环境  提示文本  持续时间
            Toast.makeText(this, "name or pwd can't be empty",Toast.LENGTH_SHORT).show();
        } else {
            // 反之出现进度条
            pb.setVisibility(View.VISIBLE);
            // 4.0只有进度条能在线程中操作
            new Thread() {
                @Override
                public void run() {
                    for (int i = 1; i <= 100; i++) {
                        pb.setProgress(i);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
}