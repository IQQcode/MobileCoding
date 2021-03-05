package top.iqqcode.clickevents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FifthActivity extends AppCompatActivity {

    private TextView mtv_user_fifth;
    private TextView mtv_pwd_fifth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        Toast.makeText(getApplicationContext(), "FifthActivity", Toast.LENGTH_SHORT).show();

        mtv_user_fifth = findViewById(R.id.tv_user_fifth);
        mtv_pwd_fifth = findViewById(R.id.tv_pwd_fifth);

        Intent intent = getIntent();
        mtv_user_fifth.setText("username: " + intent.getStringExtra("username"));
        mtv_pwd_fifth.setText("password: " + intent.getStringExtra("password"));
    }
}