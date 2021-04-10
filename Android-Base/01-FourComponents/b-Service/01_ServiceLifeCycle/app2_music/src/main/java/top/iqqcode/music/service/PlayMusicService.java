package top.iqqcode.music.service;

import android.app.ExpandableListActivity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import top.iqqcode.music.MainActivity;
import top.iqqcode.music.R;

/**
 * @Author: iqqcode
 * @Date: 2021-04-09 10:38
 * @Description:
 */
public class PlayMusicService extends Service {

    private MediaPlayer player;
    private Handler handler;
    private Timer timer;

    private String[] musicPath = new String[]{
            Environment.getExternalStorageDirectory() + "/Music/In the wind.mp3",
            Environment.getExternalStorageDirectory() + "/Music/InLove.mp3",
            Environment.getExternalStorageDirectory() + "/Music/Young.mp3",
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new InnerMusic();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //在死亡之前停止乐
        new InnerMusic().stopMusic();
    }

    public void addTimer() {
        if (timer == null) {
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    // 获取歌曲时长
                    int duration = player.getDuration();
                    // 当前播放进度
                    int currentPos = player.getCurrentPosition();
                    // 创建消息对象
                    Message msg = MainActivity.handler.obtainMessage();
                    // 将音乐总时长和播放进度封装到消息对象中
                    Bundle bundle = new Bundle();
                    bundle.putInt("duration", duration);
                    bundle.putInt("currentPosition", currentPos);
                    msg.setData(bundle);
                    // 将消息发送到主线程的消息队列
                    MainActivity.handler.sendMessage(msg);
                }
            };
            // 开始计时任务后5ms,第一次执行Task任务，以后每500ms执行一次
            timer.schedule(task, 5, 500);
        }
    }

    /**
     * 控制多媒体对象
     */
    public class InnerMusic extends Binder {
        // 播放音乐
        public void playMusic() {
            // 如果还没开始播放，就开始
            if (!player.isPlaying()) {
                player.reset();
                player = MediaPlayer.create(getApplicationContext(), R.raw.audio);
                player.start();
                addTimer();
            }
        }

        // 暂停
        public void pauseMusic() {
            if (player.isPlaying()) {
                player.pause();
            }
        }

        // 继续播放
        public void resume() {
            // 播放，不会重置
            player.start();
        }

        // 停止播放
        public void stopMusic() {
            if (player != null) {
                player.stop(); //停止
                player.reset(); //重置
                player.release(); //释放资源
                player = null; //赋空
            }
            try {
                timer.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 打带
        public void seekTo(int ms) {
            player.seekTo(ms);
        }
    }
}
