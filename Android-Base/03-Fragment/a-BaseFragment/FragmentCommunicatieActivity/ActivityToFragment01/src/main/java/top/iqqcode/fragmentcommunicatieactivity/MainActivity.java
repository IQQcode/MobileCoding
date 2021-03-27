package top.iqqcode.fragmentcommunicatieactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox mCheckBox;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.btn_judge);
        mCheckBox = findViewById(R.id.cb_is_study);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 通过id获取
                // getFragmentManager().findFragmentById(R.id.fragment_bottom);
                // 通过Tag获取Fragment
                BottomFragment fragment = (BottomFragment) getFragmentManager().findFragmentByTag("fragment_bottom_tag");
                // 获取Fragment的成员变量
                String ans = fragment.getLocalVariable();
                if (mCheckBox.isChecked()) {
                    if (fragment != null) {
                        // Fragment获取其UI控件
                        mTextView = fragment.getView().findViewById(R.id.tv_result);
                        mTextView.setText("在学习\n" + ans);
                    }
                } else {
                    if (fragment != null) {
                        // Fragment获取其UI控件
                        mTextView = fragment.getView().findViewById(R.id.tv_result);
                        mTextView.setText("不在学习");
                    }
                }
            }
        });
    }
}