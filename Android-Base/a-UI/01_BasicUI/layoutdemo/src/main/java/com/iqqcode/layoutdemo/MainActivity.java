package com.iqqcode.layoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myClick(View v) {
        switch(v.getId()) {
            case R.id.frame:
                //startActivity(new Intent(this,FrameActivity.class));
                break;
        }
    }
}