package top.iqqcode.moredialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView mTextView1;
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextView1 = findViewById(R.id.tv01);
        mTextView2 = findViewById(R.id.tv02);

        Intent intent = getIntent();
        mTextView1.setText("账号: " + intent.getStringExtra("username"));
        mTextView2.setText("密码: " + intent.getStringExtra("password"));
    }
}