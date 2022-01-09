package top.iqqcode.floating_icon

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

/**
 *
 * @property startX Float
 * @property startY Float
 * @property screenHeight Int
 * @property top Int
 * @property bottom Int
 * @property start Int
 * @property isOnclick Boolean
 * @constructor
 */
class FloatingIconView constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var startX = 0f
    private var startY = 0f
    private val screenHeight: Int = getScreenHeight(context)
    internal var top: Int
    internal val bottom: Int
    private val start: Int
    private var isOnclick = false

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == VISIBLE) {
            val layoutParams = layoutParams as RelativeLayout.LayoutParams
            layoutParams.topMargin = screenHeight - start
            setLayoutParams(layoutParams)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        val touchSlop = 2
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                isOnclick = true
                startX = event.rawX
                startY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                val x = event.rawX
                val Y = event.rawY
                // 偏移量
                val offsetX = (x - startX).toInt()
                val offsetY = (Y - startY).toInt()
                if (Math.abs(offsetY) > touchSlop) {
                    isOnclick = false
                }
                // 增量更新
                val margin = getTop() + offsetY
                if (margin > top && margin < screenHeight - bottom) {
                    // layout(getLeft(), getTop()+offsetY, getRight(),getBottom() + offsetY);
                    val layoutParams = layoutParams as RelativeLayout.LayoutParams
                    // layoutParams.leftMargin = getLeft() + offsetX;
                    layoutParams.topMargin = margin
                    setLayoutParams(layoutParams)
                }
                // 更新起始点
                startX = x
                startY = Y
            }
            MotionEvent.ACTION_UP -> if (isOnclick) {
                performClick()
            }
        }
        return true
    }

    companion object {
        fun getScreenHeight(context: Context): Int {
            val dm = context.resources.displayMetrics
            return dm.heightPixels
        }

        fun dp2px(ctx: Context?, dpValue: Float): Int {
            val scale = ctx!!.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        fun dp2px_f(ctx: Context, dpValue: Float): Float {
            val scale = ctx.resources.displayMetrics.density
            return dpValue * scale + 0.5f
        }
    }

    init {
        top = dp2px(context, 30f)
        bottom = dp2px(context, 260f)
        start = dp2px(context, 280f)
    }
}