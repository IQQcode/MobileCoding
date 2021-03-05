package top.iqqcode.activitylife;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author: iqqcode
 * @Date: 2021/2/23
 * @Description:Activity生命周期测试
 */
public class MainActivity extends AppCompatActivity {

    private Button button;

    public MainActivity() {
        Log.e("TAG", "ActivityLife");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("TAG", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        button = findViewById(R.id.lift_btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this,SecondActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "onResume");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("TAG", "onRestart");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("TAG", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy");
    }


    /**
     * 跳转到Activity Second
     * @param view
     */
    public void startSecond(View view) {
        startActivity(new Intent(this,SecondActivity.class));
    }
}