package top.iqqcode.activitylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @Author: iqqcode
 * @Date: 2021/3/6
 * @Description:Activity生命周期测试
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TAG";
    private Button mbtn_toSecond;
    private Button mbtn_small;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtn_toSecond = findViewById(R.id.btn_toSecond);
        mbtn_small = findViewById(R.id.btn_small);

        mbtn_toSecond.setOnClickListener(this);
        mbtn_small.setOnClickListener(this);

        Log.e(TAG, "MainActivity => onCreate()");
    }


    @Override
    public void onClick(View v) {
        if (v == mbtn_toSecond) {
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        } else if (v == mbtn_small) {
            startActivity(new Intent(MainActivity.this, SmallActivity.class));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG,  "MainActivity => onNewIntent()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "MainActivity => onStart(): ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "MainActivity => onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "MainActivity => onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "MainActivity => onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "MainActivity => onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "MainActivity => onDestroy()");
    }
}