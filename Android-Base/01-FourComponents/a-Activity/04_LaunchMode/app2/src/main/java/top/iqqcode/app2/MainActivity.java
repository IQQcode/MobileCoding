package top.iqqcode.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mtv_taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtv_taskId = findViewById(R.id.tv_taskId);

        findViewById(R.id.btn_main2_taskAffinity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName("top.iqqcode.launchmode","top.iqqcode.launchmode.TaskAffinityActivity");
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("prev_activity", "APP2-MainActivity");
                bundle.putString("launchModeAPP", "From APP2-MainActivity");
                intent.putExtras(bundle);
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_main2_singleInstance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName("top.iqqcode.launchmode","top.iqqcode.launchmode.SingleInstanceActivity");
                Intent intent = new Intent();
                intent.putExtra("prev_activity", "APP2-MainActivity");
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });

        mtv_taskId.setText("TaskID: " + this.getTaskId());
    }
}