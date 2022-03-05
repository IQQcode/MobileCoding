package top.iqqcode.clickevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText met_username;
    private EditText met_password;
    private Button mbtn_second;
    private Button mbtn_third;
    private Button mbtn_fourth;
    private Button mbtn_fifth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化控件
        met_username = findViewById(R.id.et_username);
        met_password = findViewById(R.id.et_password);
        mbtn_second = findViewById(R.id.btn_second);
        mbtn_third = findViewById(R.id.btn_third);
        mbtn_fourth = findViewById(R.id.btn_fourth);
        mbtn_fifth = findViewById(R.id.btn_fifth);

        /**
         * 【Button点击事件二】 匿名内部类
         */
        findViewById(R.id.btn_second).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                String username = met_username.getText().toString();
                String password = met_password.getText().toString();
                if ((TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) || (TextUtils.isEmpty(password) || TextUtils.isEmpty(username))) {
                    Toast.makeText(getApplicationContext(), "输入为空", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        });

        /**
         * 【Button点击事件三】内部类
         */
        //findViewById(R.id.btn_third).setOnClickListener(new InnerOnClickListener());

        InnerOnClickListener onClickListener = new InnerOnClickListener();
        findViewById(R.id.btn_third).setOnClickListener(onClickListener);



        /**
         * 【Button点击事件四】当前类实现接口
         */
        mbtn_fourth.setOnClickListener(onClickListener);
        mbtn_fifth.setOnClickListener(onClickListener);
    }

    /**
     * 【Button点击事件三】内部类
     */
    private class InnerOnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            commonMethods(intent);
        }
    }

    /**
     * 【Button点击事件四】当前类实现接口
     */
    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v == mbtn_fourth) {
                Intent intent = new Intent(MainActivity.this, FourthActivity.class);
                commonMethods(intent);
            } else if (v == mbtn_fifth) {
                Intent intent = new Intent(MainActivity.this, FifthActivity.class);
                commonMethods(intent);
            }
        }
    };

    /**
     * 【Button点击事件一】 xml-onClick
     *
     * @param v
     */
    public void xmlOnClick(View v) {
        Toast.makeText(getApplicationContext(), "MianActivity2", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }

    private void commonMethods(Intent intent) {
        String username = met_username.getText().toString();
        String password = met_password.getText().toString();
        if ((TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) || (TextUtils.isEmpty(password) || TextUtils.isEmpty(username))) {
            Toast.makeText(getApplicationContext(), "输入为空", Toast.LENGTH_SHORT).show();
        } else {
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            startActivity(intent);
        }
    }
}