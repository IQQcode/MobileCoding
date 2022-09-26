package top.iqqcode.gifload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import top.iqqcode.gifload.databinding.ActivityGlideLoadBinding;
import top.iqqcode.gifload.util.SharedPreferencesUtil;

public class GlideLoadActivity extends AppCompatActivity implements View.OnClickListener {

    // public static final String localUrl = "file:///android_asset/live_icon_003.gif";


    public static final String GIFURL = "https://iqqcode-blog.oss-cn-beijing.aliyuncs.com/img-2022/virtual_head_image_test.gif";
    public static final String IMAGEURL = "https://iqqcode-blog.oss-cn-beijing.aliyuncs.com/img-2022/icon_voiceroom_floatingball.jpg";
    public static String URL = GIFURL;
    public static int count = 0;

    private final String PREFS_KEY_NAME = "key_load_gif_file_test";

    private ActivityGlideLoadBinding binding;
    public ImageView mGifImageView;
    public Button mButtonClear;
    public Button mButtonPause;
    private Button mButtonChange;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGlideLoadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;
        mGifImageView = findViewById(R.id.imageView);
        mButtonClear = findViewById(R.id.buttonClear);
        mButtonPause = findViewById(R.id.buttonPause);
        mButtonChange = findViewById(R.id.buttonChange);
        mButtonClear.setOnClickListener(this);
        mButtonPause.setOnClickListener(this);
        mButtonChange.setOnClickListener(this);
        loadServerImage();
    }

    /**
     * 用grild加载图片
     */
    public void loadServerImage() {
        if (isDestroy(this)) {
            return;
        }
        final RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.net_cover_loading);

        Glide.with(this)
                .load(URL)
                .apply(options)
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        // 图片加载失败后返回
                        // e：报错信息
                        // model：load方法里面的值
                        // target：ImageView本身
                        // isFirstResource：是否是第一个资源
                        mGifImageView.post(new Runnable() {
                            @Override
                            public void run() {
                                loadLastLocalImage();
                            }
                        });
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // 图片加载成功后返回

                        // resource：目标图bitmap
                        // model：load方法里面的值
                        // target：ImageView本身
                        // dataSource：图片资源的来源，MEMORY_CACHE、LOCAL、REMOTE
                        // isFirstResource:是否是第一个资源
                        SharedPreferencesUtil.getInstance(mContext).putSP(PREFS_KEY_NAME, URL);
                        return false;
                    }
                })
                .into(mGifImageView);

        // 首帧图
//        Glide.with(this)
//                .asBitmap()
//                .load(R.drawable.live_icon_003)
//                .into(new BitmapImageViewTarget(mGifImageView) {
//                    @Override
//                    protected void setResource(Bitmap resource) {
//                        Drawable drawable = new BitmapDrawable(resource);
//                        mGifImageView.setImageDrawable(drawable);
//                    }
//                });

    }

    public void loadLastLocalImage() {
        if (isDestroy(this)) {
            return;
        }
        final RequestOptions options = new RequestOptions()
                .error(R.drawable.live_icon_default) // 异常时候显示的图片
                .placeholder(R.mipmap.cover_page) // 加载成功前显示的图片
                .fallback(R.mipmap.ic_launcher_round); // url为空的时候,显示的图片
        // .skipMemoryCache(true) // 禁用Glide的内存缓存功能
        // DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。

        Glide.with(mContext)
                .load(SharedPreferencesUtil.getInstance(mContext).getSP(PREFS_KEY_NAME))
                .apply(options)
                .error(Glide.with(mContext).load(R.drawable.live_icon_default).into(mGifImageView))
                .into(mGifImageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Glide.with(this).clear(binding.imageView);
    }

    //判断Activity是否Destroy
    public boolean isDestroy(Activity activity) {
        if (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed())) {
            Glide.with(this).clear(binding.imageView);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.buttonClear) {
            Toast.makeText(mContext, "清除缓存", Toast.LENGTH_SHORT).show();
            new ClearGlideCacheAsyncTask().execute();
        } else if (id == R.id.buttonPause) {
            Drawable drawable = mGifImageView.getDrawable();
            if (drawable instanceof GifDrawable) {
                GifDrawable gifDrawable = ((GifDrawable) drawable);
                if (gifDrawable.isRunning()) {
                    gifDrawable.stop();
                } else {
                    gifDrawable.start();
                }
            }
        } else if (id == R.id.buttonChange) {
            if (count % 2 == 0) {
                URL = GIFURL;
            } else {
                URL = IMAGEURL;
            }
            count++;
            loadServerImage();
        }
    }

    private class ClearGlideCacheAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private boolean result;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Glide.get(mContext).clearMemory();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Glide.get(mContext).clearDiskCache();
                result = true;
            } catch (Exception e) {
                Toast.makeText(mContext, "清除缓存异常", Toast.LENGTH_SHORT).show();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                Toast.makeText(mContext, "cache deleted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}