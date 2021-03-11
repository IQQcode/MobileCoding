package top.iqqcode.sprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * @Author: iqqcode
 * @Date: 2021/3/8
 * @Description:ProgressBar
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private ProgressBar mProgressBar01;
    private ProgressBar mProgressBar02;
    private Button mButton01;
    private Button mButton02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mButton01 = findViewById(R.id.btn01);
        mButton02 = findViewById(R.id.btn02);
        mProgressBar01 = findViewById(R.id.progress_bar01);
        mProgressBar02 = findViewById(R.id.progress_bar02);
        mButton01.setOnClickListener(this);
        mButton02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                if (mProgressBar01.getVisibility() == View.GONE) {
                    mProgressBar01.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar01.setVisibility(View.GONE);
                }
                break;

            case R.id.btn02:
                int progress = mProgressBar02.getProgress();
                progress = progress + 10;
                mProgressBar02.setProgress(progress);
                break;
            default:
                break;
        }
    }
}