package top.iqqcode.qqdragbubble

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout

/**
 * @Author: jiazihui
 * @Date: 2022-06-12 23:14
 * @Description:
 */
class NovelMemberView : LinearLayout, View.OnClickListener {
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        postInvalidate()
        return super.onTouchEvent(event)
    }
    override fun onClick(v: View) {}
}