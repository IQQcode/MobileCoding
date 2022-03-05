package top.iqqcode.transferobject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @Author: iqqcode
 * @Date: 2021/3/7
 * @Description:页面间传递值对象
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_next_se).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("person", new Person("iqqcode", 23));
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_next_pa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("user", new User("iqqcode", 23));
                startActivity(intent);
            }
        });
    }
}