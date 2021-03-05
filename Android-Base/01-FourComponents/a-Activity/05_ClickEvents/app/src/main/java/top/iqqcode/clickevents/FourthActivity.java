package top.iqqcode.clickevents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {

    private TextView mtv_user_fourth;
    private TextView mtv_pwd_fourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Toast.makeText(getApplicationContext(), "ForthActivity", Toast.LENGTH_SHORT).show();

        mtv_user_fourth = findViewById(R.id.tv_user_fourth);
        mtv_pwd_fourth = findViewById(R.id.tv_pwd_fourth);

        Intent intent = getIntent();
        mtv_user_fourth.setText("username: " + intent.getStringExtra("username"));
        mtv_pwd_fourth.setText("password: " + intent.getStringExtra("password"));
    }
}