package top.iqqcode.demo02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton;

    private MyFragment fragment1, fragment2, fragment3, fragment4;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getFragmentManager();
        mRadioGroup = findViewById(R.id.ll_bottom_bar);
        mRadioGroup.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        mRadioButton = (RadioButton) findViewById(R.id.tab_index);
        mRadioButton.setChecked(true);
    }

    /**
     * 隐藏所有Fragment
     *
     * @param transaction
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (fragment1 != null) transaction.hide(fragment1);
        if (fragment2 != null) transaction.hide(fragment2);
        if (fragment3 != null) transaction.hide(fragment3);
        if (fragment4 != null) transaction.hide(fragment4);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId) {
            case R.id.tab_index:
                if (fragment1 == null) {
                    fragment1 = new MyFragment();
                    fragment1.setContext("第一个Fragment");
                    fragment1.setPos(0);
                    transaction.add(R.id.ly_content, fragment1);
                } else {
                    transaction.show(fragment1);
                }
                break;
            case R.id.tab_fun:
                if (fragment2 == null) {
                    fragment2 = new MyFragment();
                    fragment2.setContext("第一个Fragment");
                    fragment2.setPos(1);
                    transaction.add(R.id.ly_content, fragment2);
                } else {
                    transaction.show(fragment2);
                }
                break;
            case R.id.tab_message:
                if (fragment3 == null) {
                    fragment3 = new MyFragment();
                    fragment3.setContext("第一个Fragment");
                    fragment3.setPos(2);
                    transaction.add(R.id.ly_content, fragment3);
                } else {
                    transaction.show(fragment3);
                }
                break;
            case R.id.tab_setting:
                if (fragment4 == null) {
                    fragment4 = new MyFragment();
                    fragment4.setContext("第一个Fragment");
                    fragment4.setPos(3);
                    transaction.add(R.id.ly_content, fragment4);
                } else {
                    transaction.show(fragment4);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }
}