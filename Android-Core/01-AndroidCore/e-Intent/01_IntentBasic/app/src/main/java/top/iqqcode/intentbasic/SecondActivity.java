package top.iqqcode.intentbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1); // 指定默认值，防止获取不到的情况
        String name = intent.getStringExtra("name"); // 不是基本类型，不用指定，默认为null
        float salary = intent.getFloatExtra("salary", -1F);

        ((TextView) (findViewById(R.id.tv_id_second))).setText("编号： " + id);
        ((TextView) (findViewById(R.id.tv_name_second))).setText("姓名： " + name);
        ((TextView) (findViewById(R.id.tv_salary_second))).setText("薪资： " + salary + " 元/月");
    }
}