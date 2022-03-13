package top.iqqcode.refreshrecyclerview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import top.iqqcode.refreshrecyclerview.R;
import top.iqqcode.refreshrecyclerview.interfaces.IRefreshHeader;
import top.iqqcode.refreshrecyclerview.interfaces.RefreshStateEnum;

/**
 * @Author: iqqcode
 * @Date: 2022-03-13 00:24
 * @Description:
 */
public class RefreshHeaderView extends LinearLayout implements IRefreshHeader {

    private LinearLayout mContentLayout;
    private ImageView mArrowImageView;
    private TextView mStatusTextView;
    private ProgressBar mProgressBar;
    private RotateAnimation mRotateUpAnim;
    private RotateAnimation mRotateDownAnim;
    // 当下拉大于固定高度，就执行刷新逻辑
    private int mMeasuredHeight;
    private int mState;

    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        this.setLayoutParams(layoutParams);
        this.setPadding(0, 0, 0, 0);

        // 将refreshHeader高度设置为0
        mContentLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.refresh_header_item, null);
        // 将头部刷新的View的高度设置成了0
        addView(mContentLayout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));

        // 初始化控件
        mArrowImageView = findViewById(R.id.ivHeaderArrow);
        mStatusTextView = findViewById(R.id.tvRefreshStatus);
        mProgressBar = findViewById(R.id.refreshProgress);

        boolean tag = mContentLayout == null;
        Log.d("iqqcode", "init: " + tag);

        // 初始化下拉刷新动画
        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateUpAnim.setDuration(200);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateDownAnim.setDuration(200);
        mRotateDownAnim.setFillAfter(true);

        // 将mContentLayout的LayoutParams高度和宽度设为自动包裹并重新测量
        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight = getMeasuredHeight(); // 获得测量后的高度
    }

    @Override
    public void onReset() {
        setState(RefreshStateEnum.STATE_NORMAL);
    }

    @Override
    public void onPrepare() {
        setState(RefreshStateEnum.STATE_RELEASE_TO_REFRESH);
    }

    @Override
    public void onRefreshing() {
        setState(RefreshStateEnum.STATE_REFRESHING);
    }

    @Override
    public void onMove(float offSet, float sumOffSet) {
        if (getVisibleHeight() > 0 || offSet > 0) {
            setVisibleHeight((int) offSet + getVisibleHeight());
            // 处于在下拉但是未刷新的临界状态，更新箭头
            if (mState <= RefreshStateEnum.STATE_RELEASE_TO_REFRESH) {
                if (getVisibleHeight() > mMeasuredHeight) {
                    onPrepare();
                } else {
                    // 下拉中ing...
                    onReset();
                }
            }
        }
    }

    /**
     * 下拉完成，回弹并且刷新
     *
     * @return
     */
    @Override
    public boolean onRelease() {
        boolean isOnRefresh = false;
        int height = getVisibleHeight();
        if (height <= 0) { // 下拉刷新View不可见
            isOnRefresh = false;
        }
        if (height > mMeasuredHeight && mState < RefreshStateEnum.STATE_REFRESHING) {
            setState(RefreshStateEnum.STATE_REFRESHING);
            isOnRefresh = true;
        }

        // refreshing and header isn't shown fully. do nothing.
        if (height > mMeasuredHeight && mState == RefreshStateEnum.STATE_REFRESHING) {
            smoothScrollTo(mMeasuredHeight);
        }

        if (mState != RefreshStateEnum.STATE_REFRESHING) {
            smoothScrollTo(0);
        }

        if (mState == RefreshStateEnum.STATE_REFRESHING) {
            int destHeight = mMeasuredHeight;
            smoothScrollTo(destHeight);
        }

        return isOnRefresh;
    }

    @Override
    public void refreshComplete() {
        setState(RefreshStateEnum.STATE_DONE); // 设置刷新的状态为已完成
        reset();

        // 延迟200ms后复位，主要是为了显示“刷新完成”的字样，不延迟的话由于时间太短就看不见“刷新完成”的字样
        new Handler().postDelayed(new Runnable() {
            public void run() {
                reset();
            }
        }, 200);
    }

    /**
     * 将头部刷新View的高度还设置为0，就是将头部刷新View隐藏通知将刷新的状态设置为STATE_NORMAL
     */
    public void reset() {
        smoothScrollTo(0);
        setState(RefreshStateEnum.STATE_NORMAL);
    }

    @Override
    public View getHeaderView() {
        return this;
    }

    /**
     * 刷新时分部处理的逻辑
     * 主要及时根据不同的刷新状态的标志，设置视图的显示隐藏以及文字的改变
     * @param state
     */
    public void setState(int state) {
        // 状态没有改变时什么也不做
        if (state == mState) return;
        switch (state) {
            case RefreshStateEnum.STATE_NORMAL: // 要变为正常状态
                if (mState == RefreshStateEnum.STATE_RELEASE_TO_REFRESH) {
                    mArrowImageView.startAnimation(mRotateDownAnim);
                }
                if (mState == RefreshStateEnum.STATE_REFRESHING) {
                    mArrowImageView.clearAnimation();
                }
                mStatusTextView.setText("下拉刷新");
                break;
            case RefreshStateEnum.STATE_RELEASE_TO_REFRESH: // 要变为下拉的状态
                mArrowImageView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                if (mState != RefreshStateEnum.STATE_RELEASE_TO_REFRESH) {
                    mArrowImageView.clearAnimation();
                    mArrowImageView.startAnimation(mRotateUpAnim);
                    mStatusTextView.setText("释放立即刷新");
                }
                break;
            case RefreshStateEnum.STATE_REFRESHING:
                mArrowImageView.clearAnimation();
                mArrowImageView.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                smoothScrollTo(mMeasuredHeight);
                mStatusTextView.setText("正在刷新...");
                break;
            case RefreshStateEnum.STATE_DONE:
                mArrowImageView.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                mStatusTextView.setText("刷新完成");
                break;
            default:
                break;

        }
        mState = state; // 保存当前刷新的状态
    }

    /**
     * 下拉之后，当正在刷新的时候，将位置从下拉到的位置恢复规定的位置的动画
     *
     * @param destHeight 规定的高度
     */
    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        Log.i("iqqcode", "smoothScrollTo: " + getVisibleHeight());
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }


    @Override
    public int getVisibleHeight() {
        boolean tag = mContentLayout == null;
        Log.d("iqqcode", "getVisibleHeight: " + tag);
        LayoutParams lp = (LayoutParams) mContentLayout.getLayoutParams();
        return lp.height;
    }

    /**
     * 设置刷新头部可见的高度
     * @param height
     */
    public void setVisibleHeight(int height) {
        boolean tag = mContentLayout == null;
        Log.d("iqqcode", "getVisibleHeight: " + tag);
        if (height < 0) height = 0;
        LayoutParams lp = (LayoutParams) mContentLayout.getLayoutParams();
        lp.height = height;
        mContentLayout.setLayoutParams(lp);
    }
}
