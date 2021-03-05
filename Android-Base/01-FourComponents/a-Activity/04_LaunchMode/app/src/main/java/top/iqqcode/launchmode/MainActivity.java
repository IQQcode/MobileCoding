package top.iqqcode.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


/**
 * @Author: iqqcode
 * @Date: 2021/3/3
 * @Description:launchMode
 */
public class MainActivity extends Activity {

    private static final String TAG = "TAG";
    private TextView mtv_taskId;
    private TextView mtv_previous;
    private TextView mtv_hashcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtv_taskId = findViewById(R.id.tv_taskId);
        mtv_previous = findViewById(R.id.tv_previous);
        mtv_hashcode = findViewById(R.id.tv_hashcode);

        // 设置点击监听
        InnerOnClickListener onClickListener = new InnerOnClickListener();
        findViewById(R.id.btn_standard).setOnClickListener(onClickListener);
        findViewById(R.id.btn_singleTop).setOnClickListener(onClickListener);
        findViewById(R.id.btn_singleTask).setOnClickListener(onClickListener);
        findViewById(R.id.btn_taskAffinity).setOnClickListener(onClickListener);
        findViewById(R.id.btn_singleInstance).setOnClickListener(onClickListener);

        // 从Intent中获取前一个Activity的名字并显示到textView上
        Intent intent = getIntent();
        mtv_previous.setText("Previous: " + intent.getStringExtra("prev_activity"));
        mtv_taskId.setText("TaskID: " + this.getTaskId());
        mtv_hashcode.setText("hashcode: " + this.hashCode());

        Log.e(TAG, "TaskID = " + getTaskId() + " MainActivity@" + this.hashCode() + " -> onCreate()");
    }

    private class InnerOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_standard:
                    startStandard(v);
                    break;
                case R.id.btn_singleTop:
                    startSingleTop(v);
                    break;
                case R.id.btn_singleTask:
                    startSingleTask(v);
                    break;
                case R.id.btn_taskAffinity:
                    startTaskAffinity(v);
                    break;
                case R.id.btn_singleInstance:
                    startSingleInstance(v);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * standard-激活MainActivity
     *
     * @param v
     */
    public void startStandard(View v) {
        Intent intent = new Intent();
        // 传递当前Activity名字到被激活的Activity
        intent.putExtra("prev_activity", "MainActivity");
        // 设置被激活的Activity
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    /**
     * SingleTop-激活SingleTopActivity
     *
     * @param v
     */
    public void startSingleTop(View v) {
        Intent intent = new Intent(SingleTopActivity.ACTION_SINGLE_TOP);
        intent.putExtra("prev_activity", "MainActivity");
        startActivity(intent);
    }

    /**
     * SingleTask-激活SingleTaskActivity
     *
     * @param v
     */
    public void startSingleTask(View v) {
        Intent intent = new Intent(SingleTaskActivity.ACTION_SINGLE_TASK);
        intent.putExtra("prev_activity", "MainActivity");
        startActivity(intent);
    }

    /**
     * SingleTask-激活SingleTaskActivity
     *
     * @param v
     */
    public void startTaskAffinity(View v) {
        Intent intent = new Intent(TaskAffinityActivity.ACTION_TASKAFFINITY);
        intent.putExtra("prev_activity", "MainActivity");
        startActivity(intent);
    }

    /**
     * singleInstance-激活SingleInstanceActivity
     *
     * @param v
     */
    public void startSingleInstance(View v) {
        Intent intent = new Intent(SingleInstanceActivity.ACTION_SINGLE_INSTANCE);
        intent.putExtra("prev_activity", "MainActivity");
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "TaskID=" + mtv_taskId + " MainActivity@" + mtv_hashcode + " => onNewIntent()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "TaskID=" + mtv_taskId + " MainActivity@" + mtv_hashcode + " => onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "TaskID=" + mtv_taskId + " MainActivity@" + mtv_hashcode + " => onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "TaskID=" + mtv_taskId + " MainActivity@" + mtv_hashcode + " => onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "TaskID=" + mtv_taskId + " MainActivity@" + mtv_hashcode + " => onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "TaskID=" + mtv_taskId + " MainActivity@" + mtv_hashcode + " => onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "TaskID=" + mtv_taskId + " MainActivity@" + mtv_hashcode + " => onDestroy()");
    }
}