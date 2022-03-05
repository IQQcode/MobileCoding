package top.iqqcode.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-09 15:51
 * @Description:服务端
 */
public class RemoteService extends Service {

    private final static String TAG = "TAG";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "服务端Service ==> IBinder onBind()");
        return null;
    }

    // 处理Student相关的业务逻辑类
    private class InnerBinder extends IMyAidlInterface.Stub {

        @Override
        public Student getStudentById(int id) throws RemoteException {
            Log.e("TAG", "Service getStudentById() " + id);
            return new Student(id, "Tom", 10000);
        }
    }
}
