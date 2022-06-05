package top.iqqcode.qqdragbubble

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

/**
 * @Author: jiazihui
 * @Date: 2022-06-12 18:20
 * @Description:自定义仿QQ消息拖拽气泡
 */
class BubbleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var mBubbleView: TextView
    // 初始坐标对象
    private lateinit var mInitialPoint: PointF
    // 保存手指移动坐标
    private lateinit var mMovePoint: PointF
    // 手指是否触摸到气泡
    private var isTouching: Boolean = false

    init {
        initAttrs(attrs)
        initBubbleView()
    }

    private fun initAttrs(attrs: AttributeSet?) {

    }

    private fun initBubbleView() {
        // 初始化文本控件
        mInitialPoint = PointF(300F, 300F)
        mMovePoint = PointF()
        mBubbleView = TextView(context)
        mBubbleView.setBackgroundResource(R.drawable.bubble_view_bg)
        mBubbleView.setTextColor(Color.WHITE)
        mBubbleView.setPadding(10, 10, 10, 10)
        mBubbleView.text = "99+"
        val params = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        mBubbleView.layoutParams = params
        this.addView(mBubbleView)
    }

    /**
     * 绘制当前控件
     * @param canvas
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    /**
     * 绘制当前控件的子控件，
     * 拖拽控件时需要绘制多次，所以此处绘制
     * @param canvas
     */
    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.save()
        // 添加绘制逻辑(此处如果固定坐标，则在使用该控件的宽高必须为 match_parent才可见)
        if (isTouching) {
            mBubbleView.x = mMovePoint.x
            mBubbleView.y = mMovePoint.y
        } else {
            mBubbleView.x = mInitialPoint.x
            mBubbleView.y = mInitialPoint.y
        }
        canvas?.restore()
        super.dispatchDraw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility", "Range")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val rect = Rect()
                val textLocation = IntArray(2)
                // 获取到mBubbleView左上角坐标
                mBubbleView.getLocationOnScreen(textLocation)
                rect.left = textLocation[0]
                rect.top = textLocation[1]
                rect.right = rect.left + mBubbleView.width
                rect.bottom = rect.top + mBubbleView.height
                // 判断当前手指是否触碰到了气泡的范围(触摸点是否在矩形内)
                if (rect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    isTouching = true
                }
            }
            MotionEvent.ACTION_UP -> {

            }
            MotionEvent.ACTION_MOVE -> {
                // 手指移动时，改变坐标
                mMovePoint.set(event.x, event.y)
            }
        }
        // 手指移动后不断调用dispatchDraw来重新绘制
        postInvalidate()
        return true
    }

    companion object {
        private const val TAG = "JIAZIHUI"

    }
}