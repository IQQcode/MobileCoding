package top.iqqcode.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TaskAffinityActivity extends Activity {

    private static final String TAG = "TAG";
    public static final String ACTION_TASKAFFINITY= "com.iqqcode.launchmode.intent.action.TaskAffinityActivity";

    private TextView mtv_taskId;
    private TextView mtv_previous;
    private TextView mtv_hashcode;
    private TextView mtv_from_another;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskaffinity);

        mtv_taskId = findViewById(R.id.tv_taskId);
        mtv_previous = findViewById(R.id.tv_previous);
        mtv_hashcode = findViewById(R.id.tv_hashcode);
        mtv_from_another = findViewById(R.id.tv_from_another);

        // 设置点击监听
        findViewById(R.id.btn_taskAffinity_main).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskAffinityActivity.this,MainActivity.class);
                intent.putExtra("prev_activity", "TaskAffinityActivity");
                startActivity(intent);
            }
        });

        // 从Intent中获取前一个Activity的名字并显示到textView上
        Intent intent = getIntent();
        mtv_previous.setText("Previous: " + intent.getStringExtra("prev_activity"));
        mtv_taskId.setText("TaskID: " + this.getTaskId());
        mtv_hashcode.setText("hashcode: " + this.hashCode());

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            String value = bundle.getString("launchModeAPP");
            mtv_from_another.setText(value);
        }


        Log.d(TAG, "TaskID = " + getTaskId() + " TaskAffinityActivity@" + this.hashCode() + " -> onCreate()");
    }
}