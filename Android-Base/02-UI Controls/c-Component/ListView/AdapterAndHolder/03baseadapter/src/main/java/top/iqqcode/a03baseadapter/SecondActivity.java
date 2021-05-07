package top.iqqcode.a03baseadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @Author: iqqcode
 * @Date: 2021/5/6
 * @Description:
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView mTextView = findViewById(R.id.second_tv_view);
        AppInfo itemInfo = this.getIntent().getParcelableExtra("itemInfo");
        mTextView.setText(itemInfo.toString());
    }
}