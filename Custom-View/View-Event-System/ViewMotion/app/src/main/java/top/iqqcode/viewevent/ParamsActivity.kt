package top.iqqcode.viewevent

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.view.View.OnTouchListener
import android.widget.RelativeLayout
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

/**
 * @Author: iqqcode
 * @Date: 2022-04-11 22:44
 * @Description: https://www.jianshu.com/p/cdbf200122ae
 */
class ParamsActivity : AppCompatActivity(), OnTouchListener {

    private lateinit var imageView: ImageView
    private lateinit var relativeLayout: RelativeLayout
    private var lastX = 0
    private var lastY = 0 // 保存手指点下的点的坐标


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_params)
        imageView = findViewById<View>(R.id.moveImage) as ImageView
        relativeLayout = findViewById<View>(R.id.layout) as RelativeLayout
        // 初始设置一个layoutParams
        val layoutParams = RelativeLayout.LayoutParams(IMAGE_SIZE, IMAGE_SIZE)
        imageView.layoutParams = layoutParams
        // 设置屏幕触摸事件
        imageView.setOnTouchListener(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                // 将点下的点的坐标保存
                lastX = event.rawX.toInt()
                lastY = event.rawY.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                // 计算出需要移动的距离
                val dx = event.rawX.toInt() - lastX
                val dy = event.rawY.toInt() - lastY
                // 将移动距离加上，现在本身距离边框的位置
                val left = view.left + dx
                val top = view.top + dy
                // 获取到layoutParams然后改变属性，在设置回去
                val layoutParams = view
                    .layoutParams as RelativeLayout.LayoutParams
                layoutParams.height = IMAGE_SIZE
                layoutParams.width = IMAGE_SIZE
                layoutParams.leftMargin = left
                layoutParams.topMargin = top
                view.layoutParams = layoutParams
                // 记录最后一次移动的位置
                lastX = event.rawX.toInt()
                lastY = event.rawY.toInt()
            }
        }
        // 刷新界面
        relativeLayout.invalidate()
        return true
    }

    companion object {
        const val IMAGE_SIZE = 200
    }
}