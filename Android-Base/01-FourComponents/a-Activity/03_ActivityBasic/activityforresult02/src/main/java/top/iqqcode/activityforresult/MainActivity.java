package top.iqqcode.activityforresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @Author: iqqcode
 * @Date: 2021/3/1
 * @Description:启动带返回值的Activity
 */
public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_SUCCESS = 996;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginButton = findViewById(R.id.btn_login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SUCCESS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SUCCESS && resultCode == RESULT_OK) {
            String user = data.getStringExtra("username");
            TextView user_teTextView = findViewById(R.id.tv_login_user);
            user_teTextView.setText(user);
            
            user_teTextView.setVisibility(View.VISIBLE);
            mLoginButton.setVisibility(View.GONE);
        }
    }
}