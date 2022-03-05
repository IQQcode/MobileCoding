package top.iqqcode.a03baseadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TextView mTextView = findViewById(R.id.third_tv_view);
        AppInfo itemInfo = this.getIntent().getParcelableExtra("itemInfo");
        mTextView.setText("APP: " + itemInfo.getAppName());
    }
}