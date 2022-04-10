package top.iqqcode.servicelifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-09 09:41
 * @Description:
 */
public class DemoService extends Service {

    private static final String TAG = "TAG";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, " ==> onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String str = null;
        if (intent == null) {
            str = "Intent为null";
        } else {
            str = intent.getStringExtra("study");
        }
        Log.e(TAG, " ===> onStartCommand " + str);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, " ===> onDestroy ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }
}
