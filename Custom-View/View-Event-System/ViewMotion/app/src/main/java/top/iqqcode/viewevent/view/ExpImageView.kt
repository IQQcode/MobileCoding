package top.iqqcode.viewevent.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

/**
 * @Author: iqqcode
 * @Date: 2022-04-10 16:37
 * @Description:
 */
class ExpImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.v("JIAZIHUI", "ExpImageView dispatchTouchEvent: " + event?.action)
        return super.dispatchTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.v("JIAZIHUI", "ExpImageView onTouchEvent: " + event?.action)
        return super.onTouchEvent(event)
    }
}