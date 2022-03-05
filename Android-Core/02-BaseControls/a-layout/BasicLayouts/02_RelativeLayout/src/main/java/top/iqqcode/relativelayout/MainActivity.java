package top.iqqcode.relativelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 去掉标题栏(游戏)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 设置全屏必须要在加载布局文件之前
        setContentView(R.layout.demo01);
    }
}