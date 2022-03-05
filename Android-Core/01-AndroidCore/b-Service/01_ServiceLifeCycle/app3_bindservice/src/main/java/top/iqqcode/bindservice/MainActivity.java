package top.iqqcode.bindservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "TAG";
    private InnerService connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = new InnerService();
    }

    public void bingServiceTest(View view) {
        Intent intent = new Intent();
        // 配置Intent,指定被绑定激活的Service
        intent.setClass(getApplicationContext(), ServiceTest.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void unbingServiceTest(View view) {
        unbindService(connection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private class InnerService implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 回调获取到Service返回的Binder对象
            Log.e(TAG, "MainActivity onServiceConnected: ==> IBinder Service " + service.hashCode());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO
        }
    }
}