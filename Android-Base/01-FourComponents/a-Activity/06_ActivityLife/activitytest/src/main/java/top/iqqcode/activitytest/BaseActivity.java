package top.iqqcode.activitytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    static int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "[>> BaseActivity] ==> onCreate -- count=" + count);
    }

    @Override
    protected void onStart() {
        count++;
        Log.e(TAG, "[>> BaseActivity] ==> onStart -- count=" + count);
        super.onStart();
    }

    @Override
    protected void onStop() {
        count--;
        super.onStop();
        Log.e(TAG, "[>> BaseActivity] ==> onStop -- count=" + count);
    }
}