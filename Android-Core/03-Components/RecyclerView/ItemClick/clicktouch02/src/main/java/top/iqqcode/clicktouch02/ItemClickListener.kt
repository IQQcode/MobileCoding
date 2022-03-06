package top.iqqcode.clicktouch02

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener
import androidx.core.view.GestureDetectorCompat
import android.view.MotionEvent
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.View

/**
 * @Author: iqqcode
 * @Date: 2022-03-05 21:41
 * @Description:
 */
abstract class ItemClickListener(
    private val mRecyclerView: RecyclerView?
) : SimpleOnItemTouchListener() {

    private val mGestureDetectorCompat: GestureDetectorCompat

    /**
     * 将事件交给GestureDetectorCompat处理
     * 并将MotionEvent 传入GestureDetectorCompat使得可以获取触摸的坐标
     */
    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        mGestureDetectorCompat.onTouchEvent(e)
    }

    /**
     * 不拦截触摸事件,将事件交给GestureDetectorCompat处理
     * @param rv RecyclerView
     * @param e MotionEvent
     * @return Boolean
     */
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        mGestureDetectorCompat.onTouchEvent(e)
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    /**
     * 定义一个抽象回调方法
     * @param vh ViewHolder?
     */
    abstract fun onItemClick(vh: RecyclerView.ViewHolder?)

    abstract fun onItemLongClick(vh: RecyclerView.ViewHolder?)

    abstract fun onItemDoubleClick(vh: View?)

    private inner class MyGestureListener : SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            val childView = e?.let { mRecyclerView?.findChildViewUnder(it.x, e.y) }
            if (childView != null) {
                val viewHolder = mRecyclerView?.getChildViewHolder(childView)
                onItemClick(viewHolder) //触发回调
            }
            return false
        }

        /**
         * 长按事件
         * @param e MotionEvent
         */
        override fun onLongPress(e: MotionEvent?) {
            super.onLongPress(e)
            val childView = e?.let { mRecyclerView?.findChildViewUnder(it.x, e.y) }
            if (childView != null) {
                val viewHolder = mRecyclerView?.getChildViewHolder(childView)
                onItemLongClick(viewHolder) //触发回调
            }
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            val action = e?.action;
            if (action == MotionEvent.ACTION_UP) {
                val childView = e.let { mRecyclerView?.findChildViewUnder(it.x, e.y) }
                if (childView != null) {
                    onItemDoubleClick(childView);
                    return true;
                }
            }
            return false
        }
    }

    // 通过构造传入我们的RecyclerView,并初始化GestureDetectorCompat
    init {
        mGestureDetectorCompat = GestureDetectorCompat(
            mRecyclerView?.context, MyGestureListener())
    }
}