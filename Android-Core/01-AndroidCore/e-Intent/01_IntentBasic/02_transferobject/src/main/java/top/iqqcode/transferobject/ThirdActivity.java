package top.iqqcode.transferobject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    private TextView mtv_content_pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mtv_content_pa = findViewById(R.id.tv_content_pa);


        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");
        mtv_content_pa.setText(String.format("Parcelable序列化传递值对象\nPerson info(name=%s, age=%d)", user.getName(), user.getAge()));
    }
}