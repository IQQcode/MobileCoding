package top.iqqcode.movefragemnt03;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @Author: iqqcode
 * @Date: 2021/3/23
 * @Description:Fragemnt动态替换及删除
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button mButton_replace, mButton_remove;
    FragmentView01 fl1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton_replace = findViewById(R.id.btn_replace);
        mButton_remove = findViewById(R.id.btn_remove);
        mButton_replace.setOnClickListener(this);
        mButton_remove.setOnClickListener(this);

        fl1 = new FragmentView01();
        getFragmentManager().beginTransaction().add(R.id.fl_container_view, fl1).commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mButton_replace == v) {
            transaction.replace(R.id.fl_container_view, new FragmentView02())
                    // Fragment被替换后，可以点击返回键，返回替换前的状态
                    .addToBackStack(null)
                    .commit();
        } else if (mButton_remove == v) {
            transaction.remove(fl1).commit();
        }
    }
}