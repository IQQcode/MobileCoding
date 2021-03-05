package com.iqqcode.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置是否选中
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(true);
        // 获取是否选中
        boolean isChecked = checkBox.isChecked();
        Log.d(TAG, "onCreate: isChecked " + isChecked);

        // 监听当前CheckBox的状态
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: " + isChecked);
                // 当状态改变时，可以处理很多UI和数据
            }
        });


        // 进度条
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(30);
        seekBar.setMax(100);
        // 自定义内部类
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // 反复拖动会很耗时，导致UI，应在线程中进行监听
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch: " + seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: " + seekBar.getProgress());
            }
        });
    }
}