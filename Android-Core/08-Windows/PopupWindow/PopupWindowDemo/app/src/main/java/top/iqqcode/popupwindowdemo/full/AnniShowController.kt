package top.iqqcode.popupwindowdemo.full

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.PopupWindow


/**
 * @Author: jiazihui
 * @Date: 2022-06-24 01:56
 * @Description:动画展示控制器
 */
class AnniShowController(context: Context) {

    private var mContext: Context = context
    private lateinit var popupWindow: PopupWindow
    private lateinit var mContentView: FrameLayout
    private var mGravity = Gravity.NO_GRAVITY

    private fun initContentView() {
        mContentView = FrameLayout(mContext)
        mContentView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        // mContentView.addView(mEjectionAnimationView)
    }

    private fun initPopUpWindow() {
        popupWindow = PopupWindow()
        popupWindow.contentView = mContentView
        popupWindow.height = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.isOutsideTouchable = false
        popupWindow.isFocusable = false
        popupWindow.isTouchable = false
    }

    fun setGravity(gravity: Int) {
        mGravity = gravity
    }

    /**
     * 是否受Window窗口限制
     * @parmas clippingEnable true：受 false：不受(覆盖状态栏和导航栏)
     */
    fun setClippingEnable(clippingEnable: Boolean) {
        popupWindow.isClippingEnabled = clippingEnable
    }

    init {
        initContentView()
        initPopUpWindow()

    }
}