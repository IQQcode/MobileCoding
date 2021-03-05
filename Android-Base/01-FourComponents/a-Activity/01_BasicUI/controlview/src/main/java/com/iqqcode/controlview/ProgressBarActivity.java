package com.iqqcode.controlview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        //设置进度
        ProgressBar pb = findViewById(R.id.progress);
        pb.setProgress(60);
        /**
         * 在Android中，4.0以后是不能直接在线程中操作控件的 (进度条是个特例)
         */
        //开启线程让进度条滚动
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