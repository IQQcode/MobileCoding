package top.iqqcode.intentforresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @Author: iqqcode
 * @Date: 2021/3/7
 * @Description:带回调启动与带结果返回
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mtv_context = null;
    private Button mbtn_next = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtv_context = findViewById(R.id.tv_context);
        mbtn_next = findViewById(R.id.btn_next);
        mbtn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mtv_context.setText("第二个Activity返回结果显示: \n\n\n" + data.getStringExtra("data"));
    }
}