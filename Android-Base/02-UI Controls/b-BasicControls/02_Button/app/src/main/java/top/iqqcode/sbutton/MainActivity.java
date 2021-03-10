package top.iqqcode.sbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @Author: iqqcode
 * @Date: 2021/3/8
 * @Description:Button样式
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button mButton01;
    private Button mButton02;
    private Button mButton03;
    private Button mButton04;
    private Button mButton05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiView();
    }

    private void intiView() {
        mButton01 = findViewById(R.id.btn01);
        mButton02 = findViewById(R.id.btn02);
        mButton03 = findViewById(R.id.btn03);
        mButton04 = findViewById(R.id.btn04);
        mButton05 = findViewById(R.id.btn05);

        mButton01.setOnClickListener(this);
        mButton02.setOnClickListener(this);
        mButton03.setOnClickListener(this);
        mButton04.setOnClickListener(this);
        mButton05.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.btn02:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.btn03:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.btn04:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.btn05:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            default:
                break;
        }
    }
}