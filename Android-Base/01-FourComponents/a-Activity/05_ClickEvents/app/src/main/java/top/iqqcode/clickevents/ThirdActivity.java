package top.iqqcode.clickevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends Activity {

    private TextView mtv_user_third;
    private TextView mtv_pwd_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Toast.makeText(getApplicationContext(), "ThirdActivity", Toast.LENGTH_SHORT).show();

        mtv_user_third = findViewById(R.id.tv_user_third);
        mtv_pwd_third = findViewById(R.id.tv_pwd_third);

        Intent intent = getIntent();
        //用getXXXExtra()方法来获取传递过来的数据,将获取到的数据在TextView中显示
        mtv_user_third.setText("username: " + intent.getStringExtra("username"));
        mtv_pwd_third.setText("password: " + intent.getStringExtra("password"));

    }
}