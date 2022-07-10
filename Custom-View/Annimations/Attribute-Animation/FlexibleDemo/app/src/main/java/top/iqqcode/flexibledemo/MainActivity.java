package top.iqqcode.flexibledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.IntEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author jiazihui
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivSearch, ivClose;
    private TextView edSearch;
    private RelativeLayout laySearch;
    private AutoTransition autoTransition;
    private int width;

    private Button mNewPageBtn;
    private Button mBtnTextAnim;
    private AnimTextView mAnimTextView;

    private String mContent = "哈哈爱好哈哈哈哈哈哈哈哈";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mNewPageBtn = findViewById(R.id.newPageBtn);
        mNewPageBtn.setOnClickListener(this);

        mBtnTextAnim = findViewById(R.id.bt_startanim);
        mAnimTextView = findViewById(R.id.anim_text);
        mBtnTextAnim.setOnClickListener(this);

        laySearch = (RelativeLayout) findViewById(R.id.lay_search);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivClose = (ImageView) findViewById(R.id.iv_close);
        edSearch = findViewById(R.id.ed_search);


        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        // 获取屏幕的宽度像素
        width = metrics.widthPixels;

        ivSearch.setOnClickListener(this);
        ivClose.setOnClickListener(this);

        initTextContent();
    }

    private void initTextContent() {
        edSearch.setText(mContent);
        edSearch.setTextSize(Utils.dip2px(this, 6));
    }


    @Override
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            // 点击搜索 伸展
            case R.id.iv_search:
                initExpand();
                break;
            // 点击close 关闭
            case R.id.iv_close:
                initClose();
                break;
            case R.id.bt_startanim:
                mAnimTextView.setAnimText("我是动画文字啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦哈哈哈哈");
                mAnimTextView.startAnim();
                break;
            case R.id.newPageBtn:
                ValueAnimator valueAnimator = ValueAnimator.ofInt(200, 100, 50, 20 ,10).setDuration(5000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mNewPageBtn.getLayoutParams().width = (int) animation.getAnimatedValue();
                        mNewPageBtn.getLayoutParams().height = (int) animation.getAnimatedValue();
                        mNewPageBtn.requestLayout();
                    }
                });
                valueAnimator.start();
                // startActivity(new Intent(this, TranslationActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 设置收缩状态时的布局
     */
    private void initClose() {
        edSearch.setVisibility(View.GONE);
        ivClose.setVisibility(View.GONE);

//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) laySearch.getLayoutParams();
//        params.width = dip2px(48);
//        params.height = dip2px(48);
//        params.setMargins(dip2px(0), dip2px(0), dip2px(0), dip2px(0));
//        laySearch.setLayoutParams(params);
        // beginDelayedTransition(laySearch);
        // delayedTransition(laySearch, laySearch.getWidth(), dip2px(48));
        keyFrameTransition(mNewPageBtn, laySearch.getWidth(), dip2px(48));
    }


    /**
     * 设置伸展状态时的布局
     */
    @SuppressLint("ClickableViewAccessibility")
    public void initExpand() {
        edSearch.setVisibility(View.VISIBLE);
        ivClose.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) laySearch.getLayoutParams();
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        laySearch.setLayoutParams(params);

        Paint paint = new Paint();
        paint.setTextSize(Utils.dip2px(this, 6));
        float strWidth = paint.measureText(mContent);

        laySearch.measure(0, 0);

        Log.i("JIAZIHUI", "initExpand: " + strWidth);
        Log.d("JIAZIHUI", "laySearch measure: " + laySearch.getMeasuredWidth());

        delayedTransition(laySearch, dip2px(48), laySearch.getMeasuredWidth());
    }

    private void delayedTransition(View view, int start, int end) {
        @SuppressLint("Recycle")
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 100F);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                IntEvaluator mEvaluator = new IntEvaluator();
                float fraction = animator.getAnimatedFraction();
                view.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                view.requestLayout();
            }
        });
        valueAnimator.setDuration(1000).setInterpolator(new DecelerateInterpolator());
        // DecelerateInterpolator减速  AccelerateInterpolator加速
        valueAnimator.start();
    }

    /**
     * 匀速动画
     *
     * @param view
     */
    private void beginDelayedTransition(ViewGroup view) {
        autoTransition = new AutoTransition();
        autoTransition.setDuration(500);
        TransitionManager.beginDelayedTransition(view, autoTransition);
    }

    @SuppressLint("Recycle")
    private void keyFrameTransition(View view, int start, int end) {
        //float length = dip2px(laySearch.getWidth() - dip2px(48));
        float length = dip2px(200);
        Keyframe frame01 = Keyframe.ofFloat(0, 0);
        // (占比总时间)时间进行了20%， 流程进行了40%
        Keyframe frame02 = Keyframe.ofFloat(0.2F, 0.4F * length);
        // 时间进行了60%， 流程进行到了60%
        Keyframe frame03 = Keyframe.ofFloat(0.8F, 0.6F * length);
        Keyframe frame04 = Keyframe.ofFloat(1.0F, 1.0F * length);
        PropertyValuesHolder propertyHolder = PropertyValuesHolder.ofKeyframe("translationX",
                frame01, frame02, frame03, frame04);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, propertyHolder);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
//                view.getLayoutParams().width = (int) value;
//                view.requestLayout();

                laySearch.getLayoutParams().width = (int) value;
                laySearch.requestLayout();
                Log.e("JIAZIHUI", "onAnimationUpdate width: " + value);
                Log.v("JIAZIHUI", ">>>: " + laySearch.getLayoutParams().width);

            }
        });
        animator.setStartDelay(1000);
        animator.setDuration(1000);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();

    }

    /**
     * dp 转成 px
     *
     * @param dpVale
     * @return
     */
    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }

    /**
     * px 转成 dp
     *
     * @param pxValue
     * @return
     */
    private int px2dip(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}

