package top.iqqcode.fragmenttoactivity03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox mCheckBox;
    private TextView mTextView;
    private BottomFragment fragment;

    public String getVariable() {
        return variable;
    }

    private String variable = "MainActivity-variable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.btn_judge);
        mCheckBox = findViewById(R.id.cb_is_study);

        fragment = new BottomFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commitAllowingStateLoss();

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String ans = fragment.getLocalVariable();
                if (mCheckBox.isChecked()) {
                    if (fragment != null) {
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