package top.iqqcode.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SingleTopActivity extends Activity {

    private static final String TAG = "TAG";
    public static final String ACTION_SINGLE_TOP = "com.iqqcode.launchmode.intent.action.SingleTopActivity";

    private TextView mtv_taskId;
    private TextView mtv_previous;
    private TextView mtv_hashcode;
    private Button mbtn_singleTop_main;
    private Button mbtn_singleTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletop);

        mtv_taskId = findViewById(R.id.tv_taskId);
        mtv_previous = findViewById(R.id.tv_previous);
        mtv_hashcode = findViewById(R.id.tv_hashcode);

        // 设置点击监听
        findViewById(R.id.btn_singleTop_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTopActivity.this, MainActivity.class);
                intent.putExtra("prev_activity", "SingleTopActivity");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_singleTop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTopActivity.this, SingleTopActivity.class);
                intent.putExtra("prev_activity", "SingleTopActivity");
                startActivity(intent);
            }
        });

        // 从Intent中获取前一个Activity的名字并显示到textView上
        Intent intent = getIntent();
        mtv_previous.setText("Previous: " + intent.getStringExtra("prev_activity"));
        mtv_taskId.setText("TaskID: " + this.getTaskId());
        mtv_hashcode.setText("hashcode: " + this.hashCode());

        Log.d(TAG, "TaskID = " + getTaskId() + " SingleTopActivity@" + this.hashCode() + " -> onCreate()");
    }
}