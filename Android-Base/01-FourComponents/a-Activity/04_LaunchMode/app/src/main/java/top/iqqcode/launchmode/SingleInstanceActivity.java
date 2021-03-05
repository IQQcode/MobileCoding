package top.iqqcode.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SingleInstanceActivity extends Activity {

    private static final String TAG = "TAG";
    public static final String ACTION_SINGLE_INSTANCE = "com.iqqcode.launchmode.intent.action.SingleInstanceActivity";

    private TextView mtv_taskId;
    private TextView mtv_previous;
    private TextView mtv_hashcode;
    private Button mbtn_singleInstance_main;
    private Button mbtn_sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleinstance);
        mtv_taskId = findViewById(R.id.tv_taskId);
        mtv_previous = findViewById(R.id.tv_previous);
        mtv_hashcode = findViewById(R.id.tv_hashcode);

        // 设置点击监听
        findViewById(R.id.btn_singleInstance_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleInstanceActivity.this, MainActivity.class);
                intent.putExtra("prev_activity", "SingleInstanceActivity");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_sample).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleInstanceActivity.this, SampleActivity.class);
                intent.putExtra("prev_activity", "SingleInstanceActivity");
                startActivity(intent);
            }
        });

        // 从Intent中获取前一个Activity的名字并显示到textView上
        Intent intent = getIntent();
        mtv_previous.setText("Previous: " + intent.getStringExtra("prev_activity"));
        mtv_taskId.setText("TaskID: " + this.getTaskId());
        mtv_hashcode.setText("hashcode: " + this.hashCode());

        Log.e(TAG, "TaskID = " + getTaskId() + " SingleInstanceActivity@" + this.hashCode() + " -> onCreate()");

    }
}