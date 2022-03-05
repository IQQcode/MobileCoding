package top.iqqcode.fragmentdemo01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 静态加载Fragment
        findViewById(R.id.tv_load_static).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StaticFragmentActivity.class));
            }
        });

        // 动态加载
        FragmentView fragmentView = new FragmentView();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container_view_left, fragmentView).commitAllowingStateLoss();
    }
}