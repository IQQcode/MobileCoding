package top.iqqcode.activitybasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @Author: iqqcode
 * @Date: 2021/2/25
 * @Description:Activity跳转[无返回值]
 */
public class MainActivity extends AppCompatActivity {

    private Button btn1_left;
    private Button btn1_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // [显示启动方式]: Context跳转
        findViewById(R.id.main_btn1_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        // [隐藏示启动一]: Action跳转
        findViewById(R.id.main_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent("com.iqqcode.activitybasic.intent.action.SecondActivity"));
                startActivity(new Intent(SecondActivity.ACTION));
            }
        });
    }
}