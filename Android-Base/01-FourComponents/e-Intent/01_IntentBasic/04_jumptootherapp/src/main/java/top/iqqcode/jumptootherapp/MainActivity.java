package top.iqqcode.jumptootherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @Author: iqqcode
 * @Date: 2021/3/7
 * @Description:APP之间的跳转
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "TAG";
    // 要跳转应用的包名、类名
    private String appPackageName = "top.iqqcode.app2";

    private Button mbtn_jumpToAPP2_package;
    private Button mbtn_jumpToAPP2_action;
    private Button mbtn_jumpToAPP2_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtn_jumpToAPP2_package = findViewById(R.id.btn_jumpToAPP2_package);
        mbtn_jumpToAPP2_action = findViewById(R.id.btn_jumpToAPP2_action);
        mbtn_jumpToAPP2_uri = findViewById(R.id.btn_jumpToAPP2_uri);
        mbtn_jumpToAPP2_package.setOnClickListener(this);
        mbtn_jumpToAPP2_action.setOnClickListener(this);
        mbtn_jumpToAPP2_uri.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        //A应用直接拉起B应用
        switch (v.getId()) {
            case R.id.btn_jumpToAPP2_package:
                ComponentName componentName = new ComponentName(appPackageName, "top.iqqcode.app2.MainActivity");
                bundle.putString("data", "你好，MainActivity2！来自Package");
                intent.putExtras(bundle);
                // intent.setClassName("B应用包名", "B应用包名.Activity");
                intent.setComponent(componentName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.btn_jumpToAPP2_action:
                //这个值一定要和B应用的action一致，否则会报错
                intent.setAction("top.iqqcode.intent.action.IQQCODE");
                intent.putExtra("data", "你好，MainActivity2！来自Action");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.btn_jumpToAPP2_uri:
                Uri uri = Uri.parse("iqqcode://top.iqqcode.app2/iqqcode"); //  scheme://host/path
                //这里Intent当然也可传递参数,但是一般情况下都会放到上面的URI中进行传递也就是"scheme://host/path?xx=xx"
                intent.putExtra("data", "你好，MainActivity2！来自URI");
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}