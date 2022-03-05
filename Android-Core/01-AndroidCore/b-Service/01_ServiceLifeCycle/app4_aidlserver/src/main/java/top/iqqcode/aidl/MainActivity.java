package top.iqqcode.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 启动服务
     *
     * @param view
     */
    public void startMyService(View view) {
        startService(new Intent(getApplicationContext(), RemoteService.class));
        Toast.makeText(this, "启动服务", Toast.LENGTH_SHORT).show();
    }

    /**
     * 停止服务
     *
     * @param view
     */
    public void stopMyService(View view) {
        stopService(new Intent(getApplicationContext(), RemoteService.class));
        Toast.makeText(this, "停止服务", Toast.LENGTH_SHORT).show();
    }

    /**
     * 绑定服务
     *
     * @param view
     */
    private ServiceConnection connection;

    public void bindMyService(View view) {
        Intent intent = new Intent(getApplicationContext(), RemoteService.class);
        // 创建连接对象
        if (connection == null) {
            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.e("TAG", "onServiceConnected()");
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.e("TAG", "onServiceDisconnected()");
                }
            };
            //绑定Service
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
            Toast.makeText(this, "绑定Service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经绑定Service", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 解绑服务
     *
     * @param view
     */
    public void unbindMyService(View view) {
        if (connection != null) {
            unbindService(connection);
            connection = null;
            Toast.makeText(this, "unbind service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还没有绑定Service", Toast.LENGTH_SHORT).show();
        }
    }

    //在Activity死亡之前调用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            unbindService(connection);
            connection = null;
        }
    }

}