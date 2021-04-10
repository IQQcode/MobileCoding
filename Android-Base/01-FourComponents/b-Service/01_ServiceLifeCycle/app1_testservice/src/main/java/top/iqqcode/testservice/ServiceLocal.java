package top.iqqcode.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-09 18:05
 * @Description:
 */
public class ServiceLocal extends Service {

    private final static String TAG = "TAG";

    public ServiceLocal() {
        Log.e(TAG, "ServiceLocal ==> 执行构造，服务被创建啦~");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "ServiceLocal ==> onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "ServiceLocal ==> onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "ServiceLocal ==> onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "ServiceLocal ==> onBind");
        Binder binder = new Binder();
        return binder;
    }
}
