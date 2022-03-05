package top.iqqcode.transferobject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView mtv_content_se;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mtv_content_se = findViewById(R.id.tv_content_se);

        Intent intent = getIntent();
        Person per = (Person) intent.getSerializableExtra("person");
        mtv_content_se.setText(String.format("Serializable序列化传递值对象\nPerson info(name=%s, age=%d)\n", per.getName(), per.getAge()));
    }
}