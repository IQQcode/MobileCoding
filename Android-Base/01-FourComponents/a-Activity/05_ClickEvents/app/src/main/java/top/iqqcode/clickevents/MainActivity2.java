package top.iqqcode.clickevents;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends Activity implements View.OnClickListener {

    private Button mbtn_second;
    private Button mbtn_third;
    private Button mbtn_fourth;
    private Button mbtn_fifth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
    }

    private void initView() {
        // 初始化控件
        mbtn_second = findViewById(R.id.btn_second);
        mbtn_third = findViewById(R.id.btn_third);
        mbtn_fourth = findViewById(R.id.btn_fourth);
        mbtn_fifth = findViewById(R.id.btn_fifth);

        // 设置点击事件监听器
        mbtn_second.setOnClickListener(this); // MainActivity2的this(当前对象)为OnClickListener
        mbtn_third.setOnClickListener(this);
        mbtn_fourth.setOnClickListener(this);
        mbtn_fifth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_second:
                startActivity(new Intent(MainActivity2.this, SecondActivity.class));
                finish();
                break;
            case R.id.btn_third:
                startActivity(new Intent(MainActivity2.this, ThirdActivity.class));
                finish();
                break;
            case R.id.btn_fourth:
                startActivity(new Intent(MainActivity2.this, FourthActivity.class));
                finish();
                break;
            case R.id.btn_fifth:
                startActivity(new Intent(MainActivity2.this, FifthActivity.class));
                finish();
                break;
        }
    }
}