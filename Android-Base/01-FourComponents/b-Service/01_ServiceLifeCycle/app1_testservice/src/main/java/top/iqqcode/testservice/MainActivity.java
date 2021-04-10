package top.iqqcode.testservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection connection;
    private final static String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 启动Service
     *
     * @param view
     */
    public void startServiceTest(View view) {
        // 要注册Service
        Intent intent = new Intent(getApplicationContext(), ServiceLocal.class);
        startService(intent);
        Toast.makeText(this, "启动Service", Toast.LENGTH_SHORT).show();
    }

    public void stopServiceTest(View view) {
        Intent intent = new Intent(getApplicationContext(), ServiceLocal.class);
        stopService(intent);
        Toast.makeText(this, "停止Service", Toast.LENGTH_SHORT).show();
    }

    public void bindServiceTest(View view) {
        Intent intent = new Intent(getApplicationContext(), ServiceLocal.class);
        if (connection == null) {
            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.e(TAG, "MainActivity ==> ServiceConnection$onServiceConnected()");
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
            bindService(intent, connection, BIND_AUTO_CREATE);
            Toast.makeText(this, "绑定Service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经绑定啦~", Toast.LENGTH_SHORT).show();
        }
    }

    public void unbindServiceTest(View view) {
        if (connection != null) {
            unbindService(connection);
            connection = null;
            Toast.makeText(this, "解除绑定啦！", Toast.LENGTH_SHORT).show();
        } else {
            // 还未绑定
            Toast.makeText(this, "还未绑定呢...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            unbindService(connection);
            connection = null;
            Toast.makeText(this, "解除绑定啦！", Toast.LENGTH_SHORT).show();
        } else {
            // 还未绑定
            Toast.makeText(this, "还未绑定呢...", Toast.LENGTH_SHORT).show();
        }
    }
}