package top.iqqcode.popupwindowdemo.full

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.*
import com.airbnb.lottie.LottieAnimationView
import top.iqqcode.popupwindowdemo.R
import top.iqqcode.popupwindowdemo.alive.BombView
import java.util.*

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

    private lateinit var mBombView: BombView

    private fun initContentView() {
        mContentView = FrameLayout(mContext)
        mContentView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        mBombView = BombView(mContext)
        val layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.gravity = Gravity.CENTER
        mContentView.addView(mBombView, layoutParams)
    }

    @SuppressLint("ResourceAsColor")
    private fun initPopUpWindow() {
        popupWindow = PopupWindow()
        popupWindow.contentView = mContentView
        popupWindow.height = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.isOutsideTouchable = false
        popupWindow.isFocusable = false
        popupWindow.isTouchable = false
        // popupWindow.setBackgroundDrawable(ColorDrawable(R.color.pop_bg))
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

    /**
     * 显示弹窗
     * @param parent 弹窗显示位置基于的父view
     */
    fun show(parent: View?, rect: Rect) {
        playAnimation(parent, rect)
    }

    private fun playAnimation(parent: View?, rect: Rect) {
        if (ShowPopupWindowUtils.showPopupWindowAtLocation(popupWindow, parent, mGravity, 0, 0)) {
            mBombView.startAnim()
        }
    }

    fun stop() {
        mBombView.setOnBombAnimatorListener {
            if (popupWindow.isShowing) {
                popupWindow.dismiss()
            }
        }
    }

    init {
        initContentView()
        initPopUpWindow()
    }
}