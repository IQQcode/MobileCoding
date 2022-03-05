package top.iqqcode.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SampleActivity extends Activity {

    private TextView mtv_taskId;
    private TextView mtv_previous;
    private TextView mtv_hashcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mtv_taskId = findViewById(R.id.tv_taskId);
        mtv_previous = findViewById(R.id.tv_previous);
        mtv_hashcode = findViewById(R.id.tv_hashcode);


        // 从Intent中获取前一个Activity的名字并显示到textView上
        Intent intent = getIntent();
        mtv_previous.setText("Previous: " + intent.getStringExtra("prev_activity"));
        mtv_taskId.setText("TaskID: " + this.getTaskId());
        mtv_hashcode.setText("hashcode: " + this.hashCode());
    }
}