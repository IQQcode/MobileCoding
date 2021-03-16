package top.iqqcode.sprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

/**
 * @Author: iqqcode
 * @Date: 2021/3/8
 * @Description:ProgressBar
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "TAG";
    private ProgressBar mProgressBar01;
    private ProgressBar mProgressBar02;
    private ProgressBar mProgressBar03;
    private SeekBar msb_progress_loading;
    private Button mButton01;
    private Button mButton02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mButton01 = findViewById(R.id.btn01);
        mButton02 = findViewById(R.id.btn02);
        mProgressBar01 = findViewById(R.id.progress_bar01);
        mProgressBar02 = findViewById(R.id.progress_bar02);
        mProgressBar03 = findViewById(R.id.progress_bar03);
        msb_progress_loading = findViewById(R.id.sb_progress_loading);

        mButton01.setOnClickListener(this);
        mButton02.setOnClickListener(this);
        msb_progress_loading.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        /**
         * 移动滑杆
         * @param seekBar
         * @param progress
         * @param fromUser
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.d(TAG, "onProgressChanged => 移动滑杆");
        }

        /**
         * 按下滑杆
         * @param seekBar
         */
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "onStartTrackingTouch => 按下滑杆");
        }

        /**
         * 离开滑杆
         * @param seekBar
         */
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "onStopTrackingTouch => 离开滑杆");
            // 1. 得到SeekBar进度
            int progress = msb_progress_loading.getProgress();
            // 2. 设置ProgressBar进度
            mProgressBar03.setProgress(progress);
            // 3. 判断SeekBar进度是否达到了最大值
            if(progress == msb_progress_loading.getMax()) {
                // 达到最大值，隐藏
                mProgressBar02.setVisibility(View.GONE);
            } else {
                mProgressBar03.setVisibility(View.VISIBLE);
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                if (mProgressBar01.getVisibility() == View.GONE) {
                    mProgressBar01.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar01.setVisibility(View.GONE);
                }
                break;

            case R.id.btn02:
                int progress = mProgressBar02.getProgress();
                progress = progress + 10;
                mProgressBar02.setProgress(progress);
                break;
            default:
                break;
        }
    }
}