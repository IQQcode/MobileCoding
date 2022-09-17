package top.iqqcode.viewevent.drag

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View.OnTouchListener
import android.view.MotionEvent
import top.iqqcode.viewevent.drag.ViewTestActivity
import android.view.ViewGroup.MarginLayoutParams
import android.animation.ObjectAnimator
import android.util.Log

/**
 * @Author: jiazihui
 * @Date: 2022-09-17 17:30
 * @Description:
 */
class ViewTestActivity : AppCompatActivity() {
    private var mTv1: TextView? = null
    private val mTv2: TextView? = null
    private var lastx = 0.0
    private var lastY = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_view_test);
        mTv1 = TextView(this)
        mTv1!!.setOnTouchListener { v, event ->
            val x = event.rawX.toDouble()
            val y = event.rawY.toDouble()
            Log.d(TAG, "onTouch: " + event.action)
            if (event.action == MotionEvent.ACTION_DOWN) {
                lastx = x
                lastY = y
            } else if (event.action == MotionEvent.ACTION_MOVE) {
                val dx = x - lastx
                val dy = y - lastY
                Log.d(TAG, "onTouch: dx==$dx,dy==$dy")
                //            startAnimation(dx,dy);

                //  moveMethod1(dx, dy);
                moveMethod2(dx, dy)
                lastx = x
                lastY = y
            }
            true
        }
    }

    //    @Override
    //    public boolean onTouchEvent(MotionEvent event) {
    //        double x=event.getRawX();
    //        double y=event.getRawY();
    //        Log.d(TAG, "onTouch: "+event.getAction());
    //        if (event.getAction()==MotionEvent.ACTION_DOWN){
    //            lastx=x;
    //            lastY=y;
    //        }else if (event.getAction()==MotionEvent.ACTION_MOVE){
    //            double dx=x-lastx;
    //            double dy=y-lastY;
    //            Log.d(TAG, "onTouch: dx=="+dx+",dy=="+dy);
    // startAnimation(dx,dy);
    //
    //          //  moveMethod1(dx, dy);
    //            moveMethod2(dx, dy);
    //
    //            lastx=x;
    //            lastY=y;
    //        }
    //        return true;
    //    }
    //根据属性动画的原理
    private fun moveMethod2(dx: Double, dy: Double) {
        mTv1!!.translationX = (mTv1!!.translationX + dx).toFloat()
        mTv1!!.translationY = (mTv1!!.translationY + dy).toFloat()
    }

    //根据margin 原理
    private fun moveMethod1(dx: Double, dy: Double) {
        val marginLayoutParams = mTv1!!.layoutParams as MarginLayoutParams
        marginLayoutParams.leftMargin += dx.toInt()
        marginLayoutParams.topMargin += dy.toInt()
        mTv1!!.layoutParams = marginLayoutParams
    }

    private fun startAnimation(dx: Double, dy: Double) {
        val objectAnimator =
            ObjectAnimator.ofFloat(mTv1, "translationX", (mTv1!!.translationX + dx).toFloat())
                .setDuration(3000)
        objectAnimator.start()
        val objectAnimator2 =
            ObjectAnimator.ofFloat(mTv1, "translationY", (mTv1!!.translationY + dy).toFloat())
                .setDuration(3000)
        objectAnimator2.start()
    }

    companion object {
        private const val TAG = "ViewTestActivity"
    }
}