package top.iqqcode.gifload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.json.JSONObject;

import top.iqqcode.gifload.databinding.ActivityGlideLoadBinding;
import top.iqqcode.gifload.databinding.ActivityMainBinding;

public class GlideLoadActivity extends AppCompatActivity {

    private ActivityGlideLoadBinding binding;
    public ImageView mGifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGlideLoadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mGifImageView = findViewById(R.id.imageView);
        // loadImage();

        String schema = "com.baidu.tieba://unidispatch/BDPLiveChannel?source=index_gz_back";
        String result = getSourceParams(schema, "source");
        Log.i("JIAZIHUI", "result > " + result);
    }

    /**
     * 用grild加载图片
     */
    public void loadImage() {
        // String url = "file:///android_asset/live_icon_image03.gif";
        Glide.with(this)
                .asGif()
                .load(R.drawable.live_icon_003)
                .error(R.mipmap.ic_launcher) //异常时候显示的图片
                .placeholder(R.mipmap.cover_page) //加载成功前显示的图片
                .fallback(R.mipmap.cover_page) //url为空的时候,显示的图片
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
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

    public String getSourceParams(String schema, String paramsName) {
        Uri schemeUri = Uri.parse(schema);
        if (schemeUri == null) {
            return schema;
        }
        String params = schemeUri.getQueryParameter(paramsName);
        if (TextUtils.isEmpty(params)) {
            return "null";
        }
        return params;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.with(this).clear(binding.imageView);
    }
}