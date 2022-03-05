package top.iqqcode.servicelifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @Author: iqqcode
 * @Date: 2021/4/8
 * @Description: Service Life Cycle
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doStartService(View view) {
        Intent intent = new Intent(this, DemoService.class);
        intent.putExtra("study", "Android");
        startService(intent);
    }

    public void doStopService(View view) {
        stopService(new Intent(this, DemoService.class));
    }
}