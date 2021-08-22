package top.iqqcode.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private Button mButton;
    private String url = "http://japi.juhe.cn/qqevaluate/qq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTextView = findViewById(R.id.text_content);
        mButton = findViewById(R.id.net_button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // url参数拼接
        StringBuilder sb = new StringBuilder("http://japi.juhe.cn/qqevaluate/qq");
        sb.append("?qq=");
        sb.append(URLEncoder.encode("1527147030"));
        sb.append("&key=");
        sb.append(URLEncoder.encode("e6ce0de3a1db3739d4343b9d19a9d61f"));
        Log.i("TAG", "拼接URL >>> " + sb.toString());

        // 提交异步任务
        MyAsyncTask asyncTask = new MyAsyncTask(sb.toString());
        asyncTask.execute();
        asyncTask.setOnDataListener(new OnDataListener() {
            @Override
            public void onDataSuccess(String data) {
                JSONFormat format = new JSONFormat();
                mTextView.setText(format.printJson(data));
            }

            @Override
            public void onDataFailed(String error) {
                mTextView.setText("Error Code is" + error);
            }
        });
    }
}