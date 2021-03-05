package top.iqqcode.basicjump;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @Author: iqqcode
 * @Date: 2021/2/22
 * @Description: Click在Activity中的写法
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private EditText editText;
    private Button btn1;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        // 设置点击监听
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { // v就是发生事件的视图对象
        if (v == btn1) {
            Intent intent = new Intent(this, SecondActivity.class);
            String message = editText.getText().toString();
            intent.putExtra("msg", message);
            // 一般启动
            startActivity(intent);
        } else if (v == btn2) {
            Intent intent = new Intent(this, SecondActivity.class);
            String message = editText.getText().toString();
            intent.putExtra("msg", message);
            int requestCode = 2;
            // 带回调启动Activity
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 2 && resultCode == 3) {
            // 从data中获取数据
            String res = data.getStringExtra("RES");
            editText.setText(res);
        }
    }
}