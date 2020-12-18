package top.iqqcode.hitoday.base;

import android.app.Application;

import org.xutils.x;

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 22:09
 * @Description:初始化数据
 */
public class InitializeApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化http
        x.Ext.init(this);
    }
}
