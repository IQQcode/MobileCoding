package top.iqqcode.viewevent

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.viewevent.databinding.ActivityMotionTestBinding

/**
 * 在Activity中处理Touch事件
 * @property binding ActivityMotionTestBinding
 * @property mRoot View
 * @property mLastX Int
 * @property mLastY Int
 * @property mRootRight Int
 * @property mRootBottom Int
 */
class MotionTestActivity : AppCompatActivity(), View.OnTouchListener {

    private lateinit var binding: ActivityMotionTestBinding
    private lateinit var mRoot: View

    private var mLastX: Int = 0
    private var mLastY: Int = 0

    private var mRootRight: Int = 0
    private var mRootBottom: Int = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotionTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mRoot = binding.exampleImage.rootView

        binding.exampleImage.setOnTouchListener(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 得到父视图的 right / bottom
                if (mRootRight == 0) {
                    mRootRight = mRoot.right
                    mRootBottom = mRoot.bottom
                }
                // 第一次记录lastX / lastY
                mLastX = event.rawX.toInt()
                mLastY = event.rawY.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                // 计算事件的偏移
                val dx = event.rawX - mLastX
                val dy = event.rawY - mLastY
                // 根据事件的偏移来移动View
                var left = binding.exampleImage.left + dx
                var right = binding.exampleImage.right + dx
                var top = binding.exampleImage.top + dy
                var bottom = binding.exampleImage.bottom + dy

                //获取到layoutParams然后改变属性，在设置回去
                val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
                // 限制left
                if (left < 0) {
                    right += -left
                    left = 0F
                }
                if (top < 0) {
                    bottom += -top
                    top = 0F
                }
                if (right > mRootRight) {
                    left -= right - mRootRight
                    right = mRootRight.toFloat()
                }
                if (bottom > mRootBottom) {
                    top -= bottom - mRootBottom
                    bottom = mRootBottom.toFloat()
                }

                binding.exampleImage.layout(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
                // 再次记录 mLastX / mLastY
                mLastX = event.rawX.toInt()
                mLastY = event.rawY.toInt()
            }
            MotionEvent.ACTION_UP -> {

            }
            else -> println()
        }
        // 所有的MotionEvent都交给ExpImageView处理
        return true
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.d("JIAZIHUI", "Activity dispatchTouchEvent: " + event?.action)
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("JIAZIHUI", "Activity onTouchEvent: " + event?.action)
        return super.onTouchEvent(event)
    }
}