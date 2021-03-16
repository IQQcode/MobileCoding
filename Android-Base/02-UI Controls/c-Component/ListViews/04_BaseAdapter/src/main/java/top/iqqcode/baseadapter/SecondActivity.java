package top.iqqcode.baseadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends Activity {

    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String index = getIntent().getStringExtra("index");
        String title = getIntent().getStringExtra("title");

        TextView mTextView = findViewById(R.id.tv_context);
        mTextView.setText("编号: " + index + "\nAPP: " + title);
    }
}