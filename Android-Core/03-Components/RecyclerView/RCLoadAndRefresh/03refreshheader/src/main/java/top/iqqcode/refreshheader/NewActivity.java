package top.iqqcode.refreshheader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        TextView textView = findViewById(R.id.activityText);
        textView.setText(itemName);
    }
}