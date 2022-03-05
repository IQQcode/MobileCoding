package top.iqqcode.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * @Author: iqqcode
 * @Date: 2021/3/7
 * @Description:测试Module4-APP之间的跳转
 */
public class MainActivity extends Activity {

    public static final String TAG = "TAG";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tv_context);

        Intent intent = getIntent();
        Bundle bundle = this.getIntent().getExtras();
        // 防止直接启动MainActivity时空指针闪退
        if (bundle != null) {
            mTextView.setText("APP1跳转到APP2啦！\n\n\n" + bundle.getString("data"));
        }

        //解析APP1中的Uri
        Uri uri = intent.getData();
        if (uri != null) {
            String scheme = uri.getScheme();
            String host = uri.getHost();
            String path = uri.getPath();
            String type = uri.getQueryParameter("type");
            Log.d(TAG, "解析的Uri为: scheme=" + scheme + " host=" + host + " path=" + path + " type=" + type);
        }

    }
}