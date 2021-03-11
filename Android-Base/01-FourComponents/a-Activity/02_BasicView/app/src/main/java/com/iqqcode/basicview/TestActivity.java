package com.iqqcode.basicview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author: iqqcode
 * @Date: 2020-12-14 14:41
 * @Description:
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final TextView tv = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        // 设置点击事件
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 为TextView动态设置文本
                tv.setText(getString(R.string.app_name));

                // Activity间的跳转，点击按钮跳转新的Activity

                //从当前TestActivity跳转到新NewActivity中去
                Intent intent = new Intent(TestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
