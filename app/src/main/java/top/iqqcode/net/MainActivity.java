package top.iqqcode.net;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView mMenuText, mShow;
    private ImageView mImageView;
    private WebView mWebView;
    private ScrollView mScroll;
    private Bitmap mBitmap;
    private String detail = "";
    private boolean flag = false;
    private final static String PIC_URL = "https://ww4.sinaimg.cn/bmiddle/005Iu2BQgy1gr4y093xosj30yi22on61.jpg";
    private final static String HTML_URL = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mMenuText = (TextView) findViewById(R.id.txtMenu);
        mShow = (TextView) findViewById(R.id.txtshow);
        mImageView = (ImageView) findViewById(R.id.imgPic);
        mWebView = (WebView) findViewById(R.id.webView);
        mScroll = (ScrollView) findViewById(R.id.scroll);
        registerForContextMenu(mMenuText); // 长按加载菜单
    }

    // 用于刷新界面
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x001:
                    hideAllWidget();
                    mImageView.setVisibility(View.VISIBLE);
                    mImageView.setImageBitmap(mBitmap);
                    Toast.makeText(MainActivity.this, "图片加载完成", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    hideAllWidget();
                    mScroll.setVisibility(View.VISIBLE);
                    mShow.setText(detail);
                    Toast.makeText(MainActivity.this, "HTML代码加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    hideAllWidget();
                    mWebView.setVisibility(View.VISIBLE);
                    mWebView.loadDataWithBaseURL("", detail, "text/html", "UTF-8", "");
                    Toast.makeText(MainActivity.this, "网页加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    // 隐藏控件
    private void hideAllWidget() {
        mImageView.setVisibility(View.GONE);
        mScroll.setVisibility(View.GONE);
        mWebView.setVisibility(View.GONE);
    }

    @Override
    // 重写上下文菜单的创建方法
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflator = new MenuInflater(this);
        inflator.inflate(R.menu.menus, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // 上下文菜单被点击是触发该方法
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:
                new Thread() {
                    public void run() {
                        try {
                            byte[] data = GetNetData.getNetImage(PIC_URL);
                            mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0x001);
                    }
                }.start();
                break;
            case R.id.two:
                new Thread() {
                    public void run() {
                        try {
                            detail = GetNetData.getNetHtml(HTML_URL);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0x002);
                    }
                }.start();
                break;
            case R.id.three:
                if (detail.equals("")) {
                    Toast.makeText(MainActivity.this, "先请求HTML先嘛~", Toast.LENGTH_SHORT).show();
                } else {
                    handler.sendEmptyMessage(0x003);
                }
                break;
        }
        return true;
    }
}