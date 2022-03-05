package top.iqqcode.basicjump;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * @Author: iqqcode
 * @Date: 2021/2/22
 * @Description:
 */
public class SecondActivity extends Activity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText = findViewById(R.id.editText_return);
        // 得到Intent对象
        Intent intent = getIntent();
        // 通过intent读取额外数据
        String message = intent.getStringExtra("msg");
        // 显示到EditText上
        editText.setText(message);
    }

    public void back1(View v) {
        // 关闭当前界面
        finish();
    }

    public void back2(View v) {
        // 保存结果
        int resultCode = 3;
        // 准备带数据的Intent
        Intent data = new Intent();
        String result = editText.getText().toString();
        data.putExtra("RES",result);
        setResult(resultCode, data);
        finish();
    }
}