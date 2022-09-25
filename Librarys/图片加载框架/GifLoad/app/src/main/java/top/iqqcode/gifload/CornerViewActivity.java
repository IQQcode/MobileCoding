package top.iqqcode.gifload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.shape.RoundedCornerTreatment;

public class CornerViewActivity extends AppCompatActivity {

    private RelativeLayout mContainer;
    private ImageView mVirtualHeadImage;
    private ImageView mImageView;
    private ImageView mImageViewTest;

    private FrameLayout mVirtualContainer02;
    private ImageView mVirtualHeadImage02;


    private final String man_url = "https://iqqcode-blog.oss-cn-beijing.aliyuncs.com/img-2022/7aec54e736d12f2e531ac8bf0ac2d562843568d6.gif";

    private final String woman_url = "https://iqqcode-blog.oss-cn-beijing.aliyuncs.com/img-2022/83025aafa40f4bfba74bfa8b464f78f0f6361851.gif";

    private final String backGroundValue = "http://tiebapic.baidu.com/figure/pic/item/7aec54e736d12f2e531ac8bf0ac2d562843568d6.jpg?tbpicau=2022-09-26-05_0aaac529437cecdce6352255b994a836";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corner_view);

        mContainer = findViewById(R.id.viewContainer);
        mVirtualHeadImage = findViewById(R.id.virtualHeadImage);
        mImageView = findViewById(R.id.cornerImageView);
        mImageViewTest = findViewById(R.id.cornerImageViewTest);

        mVirtualContainer02 = findViewById(R.id.virtualHeadContainer02);
        mVirtualHeadImage02 = findViewById(R.id.virtualHeadImage02);

        loadVirtualBackGround();

        loadRound();
    }

    private void loadVirtualBackGround() {
        RequestOptions options = new RequestOptions()
                .transform(new RoundedCornersTransform(this, 10.3f, 10.3f, 0, 0))
                .placeholder(R.drawable.virtual_dynamic_background)
                .error(R.drawable.virtual_dynamic_background);

        Glide.with(this)
                .load(man_url)
                .apply(options)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        int height = resource.getIntrinsicHeight();
                        int with = resource.getIntrinsicWidth();
                        // Matrix matrix = new Matrix();
                        // matrix.preTranslate(-1.5f, -height / 2.5f);
                        // matrix.setScale(1.1f,1f);
                        // matrix.preScale(0.9f, 0.9f);
                        // matrix.postTranslate(-1.5f, -height / 2f);

                        // .setImageMatrix(matrix);
                        return false;
                    }
                })

                .into(mVirtualHeadImage);

        Glide.with(this).load(man_url).into(mImageViewTest);
    }

    private void loadRound() {
        RequestOptions options = new RequestOptions()
                .transform(new RoundedCornersTransform(this, 10.3f, 10.3f, 0, 0))
                .placeholder(R.drawable.virtual_dynamic_background)
                .error(R.drawable.virtual_dynamic_background);

        Glide.with(this)
                .load(woman_url)
                .apply(options)
                .into(mVirtualHeadImage02);
    }
}