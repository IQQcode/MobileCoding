package top.iqqcode.smenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * @Author: iqqcode
 * @Date: 2021/3/14
 * @Description:OptionMenu
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 向Menu中添加MenuItem
        // 1. 存编码 add(int groupId, int itemId, int order, CharSequence title);
//        menu.add(0,2,0,"添加");
//        menu.add(0,3,0,"删除");

        // 2. menu资源文件(建议使用switch)
        // 得到菜单加载器
        MenuInflater menulnflater = getMenuInflater();
        // 加载菜单文件
        menulnflater.inflate(R.menu.main_option, menu); // 把加载对象填充到菜单文件
        return super.onCreateOptionsMenu(menu);
    }
}