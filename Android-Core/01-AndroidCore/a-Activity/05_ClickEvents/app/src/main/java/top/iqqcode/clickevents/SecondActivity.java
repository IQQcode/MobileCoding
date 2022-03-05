package top.iqqcode.clickevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {

    private TextView mtv_user_second;
    private TextView mtv_pwd_second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toast.makeText(getApplicationContext(), "SecondActivity", Toast.LENGTH_SHORT).show();

        mtv_user_second = findViewById(R.id.tv_user_second);
        mtv_pwd_second = findViewById(R.id.tv_pwd_second);

        Intent intent = getIntent();
        //用getXXXExtra()方法来获取传递过来的数据,将获取到的数据在TextView中显示
        mtv_user_second.setText("username: " + intent.getStringExtra("username"));
        mtv_pwd_second.setText("password: " + intent.getStringExtra("password"));
    }
}