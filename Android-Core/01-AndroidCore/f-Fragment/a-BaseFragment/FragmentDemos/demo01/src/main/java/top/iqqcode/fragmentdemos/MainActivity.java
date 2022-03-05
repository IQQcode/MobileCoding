package top.iqqcode.fragmentdemos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView_index;
    private TextView mTextView_fun;
    private TextView mTextView_message;
    private TextView mTextView_setting;
    private TextView txt_topbar;
    private ImageView mImageView;
    private FrameLayout ly_content;

    private MyFragment fragment1, fragment2, fragment3, fragment4;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getFragmentManager();
        initView();
        //模拟一次点击，既进去后选择第一项
        mTextView_index.performClick();
    }

    private void initView() {
        txt_topbar = (TextView) findViewById(R.id.tv_top_bar_text);
        mTextView_index = (TextView) findViewById(R.id.tab_index);
        mTextView_fun = (TextView) findViewById(R.id.tab_fun);
        mTextView_message = (TextView) findViewById(R.id.tab_message);
        mTextView_setting = (TextView) findViewById(R.id.tab_setting);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        mTextView_index.setOnClickListener(this);
        mTextView_fun.setOnClickListener(this);
        mTextView_message.setOnClickListener(this);
        mTextView_setting.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        mTextView_index.setSelected(false);
        mTextView_fun.setSelected(false);
        mTextView_message.setSelected(false);
        mTextView_setting.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fragment1 != null) fragmentTransaction.hide(fragment1);
        if (fragment2 != null) fragmentTransaction.hide(fragment2);
        if (fragment3 != null) fragmentTransaction.hide(fragment3);
        if (fragment4 != null) fragmentTransaction.hide(fragment4);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = manager.beginTransaction();
        hideAllFragment(transaction);
        switch (v.getId()) {
            case R.id.tab_index:
                setSelected();
                mTextView_index.setSelected(true);
                if (fragment1 == null) {
                    fragment1 = new MyFragment();
                    fragment1.setContent("第一个Fragment");
                    fragment1.setPos(0);
                    transaction.add(R.id.ly_content, fragment1);
                } else {
                    transaction.show(fragment1);
                }
                break;
            case R.id.tab_fun:
                setSelected();
                mTextView_fun.setSelected(true);
                if (fragment2 == null) {
                    fragment2 = new MyFragment();
                    fragment2.setContent("第二个Fragment");
                    fragment2.setPos(1);
                    transaction.add(R.id.ly_content, fragment2);
                } else {
                    transaction.show(fragment2);
                }
                break;
            case R.id.tab_message:
                setSelected();
                mTextView_message.setSelected(true);
                if (fragment3 == null) {
                    fragment3 = new MyFragment();
                    fragment3.setContent("第三个Fragment");
                    fragment3.setPos(2);
                    transaction.add(R.id.ly_content, fragment3);
                } else {
                    transaction.show(fragment3);
                }
                break;
            case R.id.tab_setting:
                setSelected();
                mTextView_setting.setSelected(true);
                if (fragment4 == null) {
                    fragment4 = new MyFragment();
                    fragment4.setContent("第四个Fragment");
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