package com.example.sedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @Author: iqqcode
 * @Date: 2021/3/13
 * @Description:EditText使用
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText_usr;
    private EditText mEditText_pwd;
    private Button mButton_register;
    private Button mButton_login;
    private final static String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mEditText_usr = findViewById(R.id.ed_text_usr);
        mEditText_usr = findViewById(R.id.ed_text_usr);
        mButton_login = findViewById(R.id.btn_login);
        mButton_register = findViewById(R.id.btn_register);

        mButton_login.setOnClickListener(this);
        mButton_register.setOnClickListener(this);
        mEditText_usr.addTextChangedListener(textWatcher);
    }

    @Override
    public void onClick(View v) {
        if (v == mButton_login || v == mButton_register) {
            startActivity(new Intent(MainActivity.this, SuccessActivity.class));
        } else {
            Toast.makeText(this, "点击有误", Toast.LENGTH_SHORT);
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            
        }

        /**
         * 当前输入框中的内容
         * @param s
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d(TAG, "editText_user: " + s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}