package top.iqqcode.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-09 13:04
 * @Description:
 */
public class ServiceTest extends Service {

    private final static String TAG = "TAG";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Binder binder = new Binder();
        Log.e(TAG, "ServiceTest onBind: Binder " + binder.hashCode());
        return binder;
    }
}
