package top.iqqcode.spdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import top.iqqcode.spdemo.baseuse.BaseUseActivity;
import top.iqqcode.spdemo.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton01;
    private Button mButton02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mButton01 = findViewById(R.id.brn_base_use);
        mButton02 = findViewById(R.id.brn_login);
        mButton01.setOnClickListener(this);
        mButton02.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.brn_base_use:
                startActivity(new Intent(MainActivity.this, BaseUseActivity.class));
            case R.id.brn_login:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}
