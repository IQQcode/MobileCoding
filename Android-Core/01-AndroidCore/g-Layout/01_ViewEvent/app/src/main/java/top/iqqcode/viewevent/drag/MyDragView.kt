package top.iqqcode.viewevent.drag

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView

/**
 * @Author: jiazihui
 * @Date: 2022-09-17 17:08
 * @Description:
 */
@SuppressLint("AppCompatCustomView")
class MyDragView(context: Context, attributeSet: AttributeSet) : ImageView(context, attributeSet) {

    private var lastX = 0.0
    private var lastY = 0.0

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val xLocate = event.x.toDouble()
        val yLocate = event.y.toDouble()
        if (event.action == MotionEvent.ACTION_DOWN) {
            lastX = xLocate
            lastY = yLocate
        } else if (event.action == MotionEvent.ACTION_MOVE) {
            val offsetX = xLocate - lastX
            val offsetY = yLocate - lastY
            // moveMethod2(dx, dy)
            offsetLeftAndRight(offsetX.toInt());
            offsetTopAndBottom(offsetY.toInt());
            lastX = xLocate
            lastY = yLocate
        }
        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun setOnTouchListener(l: OnTouchListener?) {
        super.setOnTouchListener(l)
    }

    // 根据margin 原理
    private fun moveMethod1(dx: Double, dy: Double) {
        val marginLayoutParams = layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.leftMargin += dx.toInt()
        marginLayoutParams.topMargin += dy.toInt()
        layoutParams = marginLayoutParams
    }

    // 根据属性动画的原理
    private fun moveMethod2(dx: Double, dy: Double) {
        translationX = (translationX +dx).toFloat();
        translationY = (translationY +dy).toFloat();
    }

    private fun startAnimation(dx: Double, dy: Double) {
        val objectAnimator =
            ObjectAnimator.ofFloat(this, "translationX", (translationX + dx).toFloat())
                .setDuration(3000)
        objectAnimator.start()
        val objectAnimator2 =
            ObjectAnimator.ofFloat(this, "translationY", (translationY + dy).toFloat())
                .setDuration(3000)
        objectAnimator2.start()
    }
}