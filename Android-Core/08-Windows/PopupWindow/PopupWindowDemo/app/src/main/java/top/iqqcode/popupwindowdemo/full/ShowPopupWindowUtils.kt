package top.iqqcode.popupwindowdemo.full

import android.app.Activity
import android.content.Context
import android.os.Binder
import android.view.View
import android.widget.PopupWindow
import java.lang.Exception

/**
 * @Author: jiazihui
 * @Date: 2022-06-25 18:14
 * @Description:PopupWindow工具类
 */
object ShowPopupWindowUtils {

    /**
     * 在给定偏移量展示PopupWindow
     *
     * @param window
     * @param parent
     * @param gravity
     * @param x
     * @param y
     * @return
     */
    fun showPopupWindowAtLocation(
        window: PopupWindow?,
        parent: View?,
        gravity: Int,
        x: Int,
        y: Int,
    ): Boolean {
        if (window != null && parent != null) {
//            if (isActivityFinished(parent.context)) {
//                return false;
//            }
//            if (isTokenValid(parent)) {
//                try {
//                    window.showAtLocation(parent, gravity, x, y)
//                    return true
//                } catch (e: Exception) {
//
//                }
//            }
            window.showAtLocation(parent, gravity, x, y)
            return true
        }
        return false
    }

    /**
     * 在给定锚点左下方展示PopupWindow
     *
     * @param window
     * @param anchor
     * @return
     */
    fun showPopupWindowAsDropDown(window: PopupWindow?, anchor: View?): Boolean {
        if (window != null && anchor != null) {
            if (isActivityFinished(anchor.context)) {
                return false;
            }
            if (isTokenValid(anchor)) {
                try {
                    window.showAsDropDown(anchor)
                    return true
                } catch (e: Exception) {

                }
            }
        }
        return false
    }

    /**
     * 按照给定的偏移量在给定锚点左下方展示PopupWindow
     *
     * @param window
     * @param anchor
     * @param xOffSet
     * @param yOffSet
     * @return
     */
    fun showPopupWindowAsDropDown(
        window: PopupWindow?,
        anchor: View?,
        xOffSet: Int,
        yOffSet: Int,
    ): Boolean {
        if (window != null && anchor != null) {
            if (isActivityFinished(anchor.context)) {
                return false;
            }
            if (isTokenValid(anchor)) {
                try {
                    window.showAsDropDown(anchor)
                    return true
                } catch (e: Exception) {

                }
            }
        }
        return false
    }

    fun dismissPopupWindow(window: PopupWindow?): Boolean {
        if (window != null) {
            if (isActivityFinished(window.contentView.context)) {
                return false;
            }
            if (isTokenValid(window.contentView)) {
                window.dismiss()
                return true
            }
        }
        return false
    }

    fun dismissPopupWindow(window: PopupWindow?, activity: Activity?): Boolean {
        if (window != null && activity != null) {
            if (isActivityFinished(activity)) {
                return false;
            }
            if (isTokenValid(activity?.window?.decorView)) {
                window.dismiss()
                return true
            }
            return false
        } else {
            return dismissPopupWindow(window)
        }
    }

    private fun isTokenValid(view: View?): Boolean {
        if (view != null) {
            val binder: Binder? = view.windowToken as? Binder
            try {
                if (binder != null) {
                    if (binder.isBinderAlive && binder.pingBinder()) {
                        return true
                    }
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
        return false
    }

    private fun isActivityFinished(context: Context): Boolean {
        if (context is Activity) {
            return context.isFinishing
        }
        return true
    }
}