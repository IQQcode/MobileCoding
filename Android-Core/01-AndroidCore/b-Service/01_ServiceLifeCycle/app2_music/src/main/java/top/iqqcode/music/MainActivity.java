package top.iqqcode.music;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import top.iqqcode.music.service.PlayMusicService;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "TAG";
    private Button btn_start, btn_pause, btn_continue, btn_exit;
    private static SeekBar mSeekBar;
    private static TextView mTextView_left, mTextView_right;
    private ImageView mImageView;

    private ObjectAnimator animator;
    private ServiceConnection connection;
    private PlayMusicService.InnerMusic control;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                control = (PlayMusicService.InnerMusic) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        intent = new Intent(getApplicationContext(), PlayMusicService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private void initView() {
        btn_start = findViewById(R.id.btn_start);
        btn_pause = findViewById(R.id.btn_pause);
        btn_continue = findViewById(R.id.btn_continue);
        btn_exit = findViewById(R.id.btn_exit);
        mSeekBar = findViewById(R.id.music_process);
        mImageView = findViewById(R.id.iv_player);
        mTextView_left = findViewById(R.id.tv_current_time);
        mTextView_right = findViewById(R.id.tv_total_time);

        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_continue.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        // 拖动进度条
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 音乐停止，动画停止播放
                if (progress == seekBar.getMax()) {
                    animator.pause();
                }
                if (fromUser) {
                    control.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                control.pauseMusic();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                control.resume();
            }
        });

        // 初始化动画
        // mImageView,动作是rotation旋转，角度从0到360度，这里用的是浮点数，所以要加个F
        animator = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360.0F);
        // 设置动画时长
        animator.setDuration(10000);
        // 旋转时间函数为线性，匀速旋转
        animator.setInterpolator(new LinearInterpolator());
        // 设置转动圈数，-1为一直转动
        animator.setRepeatCount(-1);
    }

    public static Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // 获取子线程传来的播放进度
            Bundle bundle = msg.getData();
            int duration = bundle.getInt("duration");
            // 当前播放进度
            int currentPos = bundle.getInt("currentPosition");
            mSeekBar.setMax(duration);
            mSeekBar.setProgress(currentPos);
            String totalTime = msToMinSec(duration); // 歌曲总时长
            String currentTime = msToMinSec(currentPos); // 当前时长
            mTextView_left.setText(currentTime);
            mTextView_right.setText(totalTime);
        }
    };

    /**
     * 时间单位转换
     *
     * @param ms
     * @return
     */
    public static String msToMinSec(int ms) {
        int sec = ms / 1000;
        int min = sec / 60;
        sec -= min * 60;
        return String.format("%02d:%02d", min, sec);
    }

    @Override
    public void onClick(View v) {
        intent = new Intent(getApplicationContext(), PlayMusicService.class);
        if (btn_start == v) {
            control.playMusic();
            // 动画开始
            animator.start();
        } else if (btn_pause == v) {
            control.pauseMusic();
            animator.pause();
        } else if (btn_continue == v) {
            control.resume();
            animator.resume();
        } else if (btn_exit == v) {
            // 退出并停止服务
            stopService(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        control.stopMusic();
        unbindService(connection);
    }
}