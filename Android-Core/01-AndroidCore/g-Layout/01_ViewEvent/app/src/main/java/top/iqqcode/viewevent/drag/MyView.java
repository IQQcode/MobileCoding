package top.iqqcode.viewevent.drag;


import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @Author: jiazihui
 * @Date: 2022-09-17 15:14
 * @Description:
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        //动画实现移动代码
        //--------------------------------------------------
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int delaltax = x - mLastX;
//                int delaltaY = y - mLastY;
//                float tranX = getTranslationX() + delaltax;
//                float tranY = getTranslationY() + delaltaY;
//                setTranslationX(tranX);
//                setTranslationY(tranY);
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//            default:
//                break;
//        }
        //-----------------------------------------------

        //layout方法实现
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //计算偏移量
//                int offsetX = x - mLastX;
//                int offsetY = y - mLastY;
//                //重新布局
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);
//                //也可以使用下面这种方法
//        offsetLeftAndRight(offsetX);
//        offsetTopAndBottom(offsetY);
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }
        //---------------------------------------------

        //LayoutParams方法
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;
                //示例代码的父View是LinearLayout
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                //下面这两句都可以使用，setLayoutParams也会调用requestLayout
                //                setLayoutParams(layoutParams);
                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }
}
