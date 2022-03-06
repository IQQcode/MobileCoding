package top.iqqcode.clicktouch02

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: iqqcode
 * @Date: 2022-03-05 21:27
 * @Description:
 */
interface OnItemTouchListener {

    /**
     * 拦截触摸事件
     * @param rv RecyclerView?
     * @param e MotionEvent?
     * @return Boolean
     */
    fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean

    /**
     * 处理触摸事件
     * @param rv RecyclerView?
     * @param e MotionEvent?
     */
    fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?)

    /**
     * 处理触摸冲突
     * @param disallowIntercept Boolean
     */
    fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean)
}