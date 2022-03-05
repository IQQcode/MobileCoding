package top.iqqcode.simageview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * @Author: iqqcode
 * @Date: 2021/3/8
 * @Description:ImageView基本使用
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView mImageView;
    private TextView mTextView;
    private Button mButton;

    private int[] images = {R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06};

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mImageView = findViewById(R.id.iv_banner);
        mTextView = findViewById(R.id.tv_title);
        mButton = findViewById(R.id.btn_first);

        mImageView.setOnClickListener(this);
        mTextView.setOnClickListener(this);
        mButton.setOnClickListener(this);

        // Glide加载网络图片
        Glide.with(this).load("https://iqqcode-blog.oss-cn-beijing.aliyuncs.com/img-2021-befo/DNS%E5%9F%9F%E5%90%8D%E8%A7%A3%E6%9E%90%20www.abc.com.png").into(mImageView);
    }

    @Override
    public void onClick(View v) {
        if (count >= 5) {
            count = -1;
        }
        mImageView.setImageResource(images[++count]);
        mTextView.setText("ImageView" + count);
    }
}