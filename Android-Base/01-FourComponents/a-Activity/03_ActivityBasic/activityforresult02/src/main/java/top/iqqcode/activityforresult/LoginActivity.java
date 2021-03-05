package top.iqqcode.activityforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * @Author: iqqcode
 * @Date: 2021/3/2
 * @Description:被启动Activity添加返回的数据
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user_editText = findViewById(R.id.et_username);
                String user = user_editText.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("username", user); // key-value

                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}