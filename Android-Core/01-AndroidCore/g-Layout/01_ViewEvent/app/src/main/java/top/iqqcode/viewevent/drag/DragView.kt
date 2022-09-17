package top.iqqcode.viewevent.drag

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import kotlin.math.abs

/**
 * 随意拖动的view
 */
@SuppressLint("AppCompatCustomView")
class DragView(context: Context, attributeSet: AttributeSet) : ImageView(context, attributeSet) {

    private var widthSize = 0
    private var heightSize = 0
    private var screenWidth = 0
    private var screenHeight = 0

    // 是否拖动
    var isDrag = false

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthSize = measuredWidth
        heightSize = measuredHeight
        screenWidth = ScreenUtil.getScreenWidth(context)
        screenHeight = ScreenUtil.getScreenHeight(context)
    }

    /** 手指点击移动在屏幕上的坐标  */
    private var downX = 0f
    private var downY = 0f
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        if (this.isEnabled) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isDrag = false
                    downX = event.x
                    downY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    val xDistance = event.x - downX
                    val yDistance = event.y - downY
                    var l: Int
                    var r: Int
                    var t: Int
                    var b: Int
                    // 当水平或者垂直滑动距离大于10,才算拖动事件
                    if (abs(xDistance) > 10 || abs(yDistance) > 10) {
                        isDrag = true
                        l = (left + xDistance).toInt()
                        r = l + width
                        t = (top + yDistance).toInt()
                        b = t + height
                        // 不划出边界判断,此处应按照项目实际情况,因为本项目需求移动的位置是手机全屏,
                        // 所以才能这么写,如果是固定区域,要得到父控件的宽高位置后再做处理
                        if (l < 0) {
                            l = 0
                            r = l + width
                        } else if (r > screenWidth) {
                            r = screenWidth
                            l = r - width
                        }
                        if (t < 0) {
                            t = 0
                            b = t + height
                        } else if (b > screenHeight) {
                            b = screenHeight
                            t = b - height
                        }

                        // 重新布局
                        layout(l, t, r, b)
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> isPressed = false
                else -> {}
            }
            return true
        }
        // 这里我们要消费这个事件，所以返回了true
        return false
    }
}