package top.iqqcode.demo03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ly_tab_menu_index;
    private TextView tab_menu_index;
    private TextView tab_menu_index_num;
    private LinearLayout ly_tab_menu_message;
    private TextView tab_menu_message;
    private TextView tab_menu_message_num;
    private LinearLayout ly_tab_menu_fun;
    private TextView tab_menu_fun;
    private TextView tab_menu_fun_num;
    private LinearLayout ly_tab_menu_setting;
    private TextView tab_menu_setting;
    private ImageView tab_menu_setting_partner;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private MyFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ly_tab_menu_index.performClick();
        fragment = new MyFragment();
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.ly_content, fragment).commitAllowingStateLoss();
    }

    private void initView() {
        ly_tab_menu_index = (LinearLayout) findViewById(R.id.ly_tab_menu_index);
        tab_menu_index = (TextView) findViewById(R.id.tab_menu_index);
        tab_menu_index_num = (TextView) findViewById(R.id.tab_menu_index_num);
        ly_tab_menu_message = (LinearLayout) findViewById(R.id.ly_tab_menu_message);
        tab_menu_message = (TextView) findViewById(R.id.tab_menu_message);
        tab_menu_message_num = (TextView) findViewById(R.id.tab_menu_message_num);
        ly_tab_menu_fun = (LinearLayout) findViewById(R.id.ly_tab_menu_fun);
        tab_menu_fun = (TextView) findViewById(R.id.tab_menu_fun);
        tab_menu_fun_num = (TextView) findViewById(R.id.tab_menu_fun_num);
        ly_tab_menu_setting = (LinearLayout) findViewById(R.id.ly_tab_menu_setting);
        tab_menu_setting = (TextView) findViewById(R.id.tab_menu_setting);
        tab_menu_setting_partner = (ImageView) findViewById(R.id.tab_menu_setting_partner);

        ly_tab_menu_index.setOnClickListener(this);
        ly_tab_menu_message.setOnClickListener(this);
        ly_tab_menu_fun.setOnClickListener(this);
        ly_tab_menu_setting.setOnClickListener(this);
    }


    //重置所有文本的选中状态
    private void setSelected() {
        tab_menu_index.setSelected(false);
        tab_menu_message.setSelected(false);
        tab_menu_fun.setSelected(false);
        tab_menu_setting.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ly_tab_menu_index:
                setSelected();
                tab_menu_index.setSelected(true);
                break;
            case R.id.ly_tab_menu_message:
                setSelected();
                tab_menu_message.setSelected(true);
                break;
            case R.id.ly_tab_menu_fun:
                setSelected();
                tab_menu_fun.setSelected(true);
                break;
            case R.id.ly_tab_menu_setting:
                setSelected();
                tab_menu_setting.setSelected(true);
                break;
            default:
                break;
        }
    }
}