package top.iqqcode.app_observer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private MyObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mObserver = new MyObserver(mHandler);
        mObserver.registerObserver();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x111) {
                Log.e("xxx", "监听的数据改变了");
            }
        }
    };

    @Override
    public void onDestroy() {
        mObserver.unregisterObserver();
        super.onDestroy();
    }
}