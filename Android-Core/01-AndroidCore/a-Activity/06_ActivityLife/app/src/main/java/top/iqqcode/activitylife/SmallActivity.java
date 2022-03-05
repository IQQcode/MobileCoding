package top.iqqcode.activitylife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SmallActivity extends Activity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small);

        Log.d(TAG, "SmallActivity => onCreate()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "SmallActivity => onNewIntent()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SmallActivity => onStart(): ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "SmallActivity => onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SmallActivity => onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SmallActivity => onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SmallActivity => onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SmallActivity => onDestroy()");
    }
}