package top.iqqcode.addviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author jiazihui
 */
public class MainActivity extends AppCompatActivity {

    private RelativeLayout mRootView;
    private View mTabLayoutTest;
    private TextView mHotTipBubble;

    private LinearLayout mHotBar;
    private View mRedHot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootView = findViewById(R.id.root_view);
        mHotBar = findViewById(R.id.hot_bar);
        mRedHot = findViewById(R.id.hot_red_tip);
        mHotBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRedHot.setVisibility(View.GONE);
            }
        });

//        mTabLayoutTest = new View(this);
//        mTabLayoutTest.setBackgroundColor(R.drawable.ic_launcher_background);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                DensityHelper.dip2px(this, 200),
//                DensityHelper.dip2px(this, 50));
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        mTabLayoutTest.setLayoutParams(params);
//        mRootView.addView(mTabLayoutTest);
//
//        // initView();
//        updateContainerView();
    }

    private void updateContainerView() {
        RelativeLayout tabContainer = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                DensityHelper.dip2px(this, 100));
        tabContainer.setPadding(20,20,20,20);
        tabContainer.setLayoutParams(params);
        mRootView.removeAllViews();
        mRootView.addView(tabContainer);
        tabContainer.addView(mTabLayoutTest);

        View motBubble = LayoutInflater.from(this).inflate(R.layout.hot_bubble_layout, tabContainer, true);
        // tabContainer.addView(motBubble);
//        RelativeLayout.LayoutParams paramsLocate = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        paramsLocate.addRule(RelativeLayout.RIGHT_OF, mTabLayoutTest.getId());
//        paramsLocate.addRule(RelativeLayout.ABOVE, mTabLayoutTest.getId());
//        motBubble.setLayoutParams(paramsLocate);
    }
//
//    private void initView() {
//        RelativeLayout tabContainer = new RelativeLayout(this);
//        tabContainer.setBackground(getDrawable(R.drawable.hot_tip));
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                DensityHelper.dip2px(this, 70),
//                DensityHelper.dip2px(this, 35));
//        tabContainer.setGravity(Gravity.CENTER);
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        tabContainer.setLayoutParams(params);
//        // tabContainer.setPadding(0, DensityHelper.dip2px(this, 10), 0, 0);
//        mRootView.removeAllViews();
//        tabContainer.addView(mTabLayoutTest);
//
//        initHotTipBubbleView();
//        tabContainer.addView(mHotTipBubble, -1);
//        mRootView.addView(tabContainer);
//    }
//

    private void initHotTipBubbleView() {
        mHotTipBubble = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = DensityHelper.dip2px(this, 8);
        mHotTipBubble.setGravity(Gravity.CENTER);
        mHotTipBubble.setTextSize(DensityHelper.dip2px(this, 13));
        mHotTipBubble.setLayoutParams(params);
        mHotTipBubble.setSingleLine();
        mHotTipBubble.setGravity(Gravity.CENTER);
        mHotTipBubble.setTextSize(DensityHelper.dip2px(this, 5));
        mHotTipBubble.setText("贾自慧");
        setBubbleBackground();
    }

    private void setBubbleBackground() {
        Drawable backDrawable = ContextCompat.getDrawable(this, R.drawable.hot_tip);
        mHotTipBubble.setBackground(backDrawable);
    }
}