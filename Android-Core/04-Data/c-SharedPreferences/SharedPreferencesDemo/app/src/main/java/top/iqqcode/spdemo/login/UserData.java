package top.iqqcode.spdemo.login;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: iqqcode
 * @Date: 2021-08-29 14:35
 * @Description:为了模拟项目中的分层操作，将SharedPreferences获取Data放到单独的类
 */
public class UserData {

    private Context mContext;
    SharedPreferences sp = null;

    public UserData(Context context) {
        this.mContext = context;
        sp = mContext.getSharedPreferences("LoginData", Context.MODE_PRIVATE);
    }

    public void saveData(String username, String password) {
        // 获取编辑器
        SharedPreferences.Editor editor = sp.edit();
        // 存入登录状态时的用户名
        editor.putString("user_name", username);
        editor.putString("pass_word", password);
        // 存入boolean类型的登录状态
        editor.putBoolean("isLogin", true);
        // 提交修改
        editor.apply();
    }

    public Map loadData() {
        Map<String, Object> map = new HashMap();
        String resUsr = sp.getString("user_name", "no user");
        String resPwd = sp.getString("pass_word", "no password");
        map.put("usr", resUsr);
        map.put("pwd", resPwd);
        return map;
    }

    public void clear() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public boolean getSharedIsRemember() {
        return sp.getBoolean("isLogin", false);
    }
}
