package top.iqqcode.spdemo.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import top.iqqcode.spdemo.MainActivity;
import top.iqqcode.spdemo.R;

public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextName, mEditTextPwd;
    private Button mButtonLogin;
    private CheckBox mCheckBox;
    private UserData userData;

    public String username = null;
    private String password = null;
    private boolean checkStatus = false; // 复选框状态
    private Map<String, Object> mMap;

    private static final String MD5Usr = MD5Utils.md5("iqqcode@qq.com");
    private static final String MD5Pwd = MD5Utils.md5("android");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        initEvent();
    }

    private void initView() {
        mEditTextName = findViewById(R.id.editTextEmail);
        mEditTextPwd = findViewById(R.id.editTextPassword);
        mCheckBox = findViewById(R.id.checkbox_pwd);
        mButtonLogin = findViewById(R.id.cirLoginButton);
    }

    private void initEvent() {
        userData = new UserData(getApplicationContext());
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkStatus = mCheckBox.isChecked();
            }
        });

        if (userData.getSharedIsRemember()) {
            mMap = userData.loadData();
            if (mMap != null) {
                username = (String) mMap.get("usr");
                password = (String) mMap.get("pwd");
                mEditTextName.setText(username);
                mEditTextPwd.setText(password);
                loginJudge(username, password);
                startActivity(new Intent(LoginActivity.this, LoginSuccessActivity.class));
            }
        }


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 不勾选CheckBox, 登录
                username = mEditTextName.getText().toString().trim();
                password = mEditTextPwd.getText().toString().trim();
                if(loginJudge(username, password)) {
                    if (checkStatus) {
                        userData.saveData(username, password);
                    } else {
                        userData.clear();
                    }

                    // 跳转到成功，登录成功的状态传递到
                    startActivity(new Intent(LoginActivity.this, LoginSuccessActivity.class));
                }
            }
        });
    }

    private boolean loginJudge(String username, String password) {
        // 对当前用户输入的密码进行MD5加密再进行比对判断 MD5Utils.md5();
        String md5Usr = MD5Utils.md5(username);
        String md5Pwd = MD5Utils.md5(password);
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            // md5Psw.equals(); 判断，输入的密码加密后，是否与保存在SharedPreferences中一致
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
            return false;
        } else if ((password != null && !TextUtils.isEmpty(password) && !md5Pwd.equals(MD5Pwd))) {
            Toast.makeText(LoginActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        } else if (md5Usr.equals(MD5Usr) && md5Pwd.equals(MD5Pwd)) {
            // 一致登录成功
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(LoginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}