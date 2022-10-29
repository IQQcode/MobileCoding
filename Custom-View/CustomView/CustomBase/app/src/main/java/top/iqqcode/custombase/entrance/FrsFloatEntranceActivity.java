package top.iqqcode.custombase.entrance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import top.iqqcode.custombase.R;
import top.iqqcode.custombase.util.UtilHelper;

/**
 * @author jiazihui
 * Frs群聊入口
 */
public class FrsFloatEntranceActivity extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener {

    private CardViewLayout mChatGroupEntranceFloat;
    private ViewFlipper mChatGroupFlipper;
    private List<String> titles;
    private ImageView mImageViewLogo;

    private Button mChatGroupTestButton;

    private int duration = 1000;
    private boolean isExpand = true;
    private boolean isAnimation = false;

    private int savePaddingLeft = 0;
    private int savePaddingRight = 0;
    private int saveMarginLeft = 0;
    private int saveMarginRight = 0;
    private int mLinearLayoutWidth = 0;
    private int allHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frs_float_entrance);

        initView();

        initData();
        setViews();
    }

    private void initView() {
        mChatGroupEntranceFloat = findViewById(R.id.chat_group_entrance_float);
        mChatGroupFlipper = findViewById(R.id.frs_chat_group_flipper);
        mImageViewLogo = findViewById(R.id.imageViewLogo);
        mChatGroupTestButton = findViewById(R.id.chat_group_button);
        mChatGroupTestButton.setOnClickListener(this);

        initLayoutParams();
    }

    private void initLayoutParams() {
        // 在回调中来获取整个布局的高度,和需要缩放的宽度,还有缩放部分的内外间距
        ViewTreeObserver vto = mChatGroupEntranceFloat.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                allHeight = mChatGroupEntranceFloat.getHeight();
                mLinearLayoutWidth = mChatGroupEntranceFloat.getWidth();
                Log.e("JIAZIHUI", "Width=" + mLinearLayoutWidth + ", allHeight=" + allHeight);
                savePaddingLeft = mChatGroupEntranceFloat.getPaddingLeft();
                savePaddingRight = mChatGroupEntranceFloat.getPaddingRight();
                ViewGroup.LayoutParams params = mChatGroupEntranceFloat.getLayoutParams();
                if (params instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginVglp = (ViewGroup.MarginLayoutParams) params;
                    saveMarginLeft = marginVglp.leftMargin;
                    saveMarginRight = marginVglp.rightMargin;
                    Log.e("JIAZIHUI", "vglp saveMarginLeft=" + saveMarginLeft + " saveMarginRight=" + saveMarginRight);
                }
                mChatGroupEntranceFloat.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // initBackGround();
            }
        });
    }

    // =================================== 轮播控件 =================================

    private void setViews() {
        if (titles.size() > 0) {
            // 计算ViewFlipper视图的数目
            int viewNum = titles.size() / 2 + 1;
            for (int i = 0; i < viewNum; i++) {
                // 每一个视图的第一个新闻标题中集合中的下标值
                final int position = i * 2;
                View itemView = View.inflate(getBaseContext(), R.layout.carousel_item_view, null);
                TextView tvTitle1 = (TextView) itemView.findViewById(R.id.tv_title1);
                TextView tvTitle2 = (TextView) itemView.findViewById(R.id.tv_title2);
                LinearLayout ll = (LinearLayout) itemView.findViewById(R.id.ll_second);
                //得到textView实例之后，用集合中的数据进行填充，通过循环完成
                tvTitle1.setText(titles.get(position));

                //判断第二行是否有数据，这里考虑进奇数视图的情况
                if (position + 1 < titles.size()) {
                    tvTitle2.setText(titles.get(position + 1));
                } else {
                    //表示该视图的第二个标题没有数据，隐藏第二行布局
                    ll.setVisibility(View.GONE);
                }

                //标题1的点击事件
                tvTitle1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), titles.get(position), Toast.LENGTH_SHORT).show();
                    }
                });

                //标题2的点击事件
                tvTitle2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), titles.get(position + 1), Toast.LENGTH_SHORT).show();
                    }
                });

                mChatGroupFlipper.addView(itemView);
            }
        }
    }

    private void initData() {
        titles = new ArrayList<>();
        titles.add("你是最适合19.9元T恤的人！");
        titles.add("《高能少年团加长版》一期上线！");
        titles.add("古人是如何买年货的！");
        titles.add("为何让手机24小时不离身！");
        titles.add("我用了20年才看懂还珠格格");
        titles.add("你的英伦腔是装的还是真的？");
        titles.add("梁实秋：人没有不懒的");
    }


    // =================================== 动画  ==============================

    public void close() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(duration);
        animationSet.setAnimationListener(this);
        animationSet.setFillAfter(true);

        RotateAnimation rotateAnimation = new RotateAnimation(360, 270,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(rotateAnimation);
        Animation scaleAnimation = new ScaleAnimation(1f, 1.25f, 1f, 1.25f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        mImageViewLogo.startAnimation(animationSet);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "width", mLinearLayoutWidth, 0);
        ObjectAnimator animator2 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "paddingLeft", savePaddingLeft, 0);
        ObjectAnimator animator3 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "paddingRight", savePaddingRight, 0);
        ObjectAnimator animator4 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "marginLeft", saveMarginLeft, 0);
        ObjectAnimator animator5 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "marginRight", saveMarginRight, 0);
        animatorSet.playTogether(animator1, animator2, animator3, animator4, animator5);
        animatorSet.setDuration(duration).start();

    }


    public void open() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(duration);
        animationSet.setAnimationListener(this);
        animationSet.setFillAfter(true);

        RotateAnimation rotateAnimation = new RotateAnimation(270, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(rotateAnimation);
        Animation scaleAnimation = new ScaleAnimation(1.25f, 1f, 1.25f, 1f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        mImageViewLogo.startAnimation(animationSet);


        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "width", 0, mLinearLayoutWidth);
        ObjectAnimator animator2 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "paddingLeft", 0, savePaddingLeft);
        ObjectAnimator animator3 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "paddingRight", 0, savePaddingRight);
        ObjectAnimator animator4 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "marginLeft", 0, saveMarginLeft);
        ObjectAnimator animator5 = ObjectAnimator.ofInt(mChatGroupEntranceFloat, "marginRight", 0, saveMarginRight);
        animatorSet.playTogether(animator1, animator2, animator3, animator4, animator5);
        animatorSet.setDuration(duration).start();
    }

    public void toggle() {
        if (!isAnimation) {
            if (isExpand) {
                close();
            } else {
                open();
            }
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        isAnimation = true;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        isAnimation = false;
        isExpand = !isExpand;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.chat_group_button) {
            toggle();
        }
    }
}