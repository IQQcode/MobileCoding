package top.iqqcode.rcloadandrefresh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @Author: iqqcode
 * @Date: 2022/3/10
 * @Description:https://www.jianshu.com/p/b502c5b59998
 * 《高级Android开发强化实战》
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_base).setOnClickListener(this);
        findViewById(R.id.btn_wrapper).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_base:
                intent = new Intent(this, LoadMoreActivity.class);
                break;

            case R.id.btn_wrapper:
                intent = new Intent(this, LoadMoreWrapperActivity.class);
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}