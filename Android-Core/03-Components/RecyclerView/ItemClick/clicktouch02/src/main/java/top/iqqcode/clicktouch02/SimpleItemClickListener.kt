package top.iqqcode.clicktouch02

import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener

/**
 * @Author: iqqcode
 * @Date: 2022-03-06 11:02
 * @Description:RecyclerView的Item点击事件监听
 */
class SimpleItemClickListener(
    mRecyclerView: RecyclerView,
    onClickListener: OnItemClickListener?
) : RecyclerView.SimpleOnItemTouchListener() {

    private var mGestureDetector: GestureDetectorCompat? = null

    /**
     * 不拦截触摸事件,将事件交给GestureDetectorCompat处理
     * @param rv RecyclerView
     * @param e MotionEvent
     * @return Boolean
     */
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return mGestureDetector?.onTouchEvent(e) == true
    }


    /**
     * 将事件交给GestureDetectorCompat处理
     * 并将MotionEvent 传入GestureDetectorCompat使得可以获取触摸的坐标
     */
    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        mGestureDetector?.onTouchEvent(e)
    }

    /**
     * 初始化GestureDetector
     */
    init {
        mGestureDetector =
            GestureDetectorCompat(mRecyclerView.context,
                object : SimpleOnGestureListener() {
                // 这里选择SimpleOnGestureListener实现类，可以根据需要选择重写的方法
                // 单击事件
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    val childView = mRecyclerView.findChildViewUnder(e.x, e.y)
                    if (childView != null && onClickListener != null) {
                        onClickListener?.onItemClick(childView, mRecyclerView.getChildLayoutPosition(childView))
                        return true
                    }
                    return false
                }

                /**
                 * 长按事件
                 * @param e MotionEvent
                 */
                override fun onLongPress(e: MotionEvent) {
                    val childView = mRecyclerView.findChildViewUnder(e.x, e.y)
                    if (childView != null && onClickListener != null) {
                        onClickListener?.onItemLongClick(childView,
                            mRecyclerView.getChildLayoutPosition(childView))
                    }
                }

                /**
                 * 双击事件
                 * @param e MotionEvent
                 * @return Boolean
                 */
                override fun onDoubleTapEvent(e: MotionEvent): Boolean {
                    val action = e.action
                    if (action == MotionEvent.ACTION_UP) {
                        val childView = mRecyclerView.findChildViewUnder(e.x, e.y)
                        if (childView != null && onClickListener != null) {
                            onClickListener?.onItemDoubleClick(childView,
                                mRecyclerView.getChildLayoutPosition(childView))
                            return true
                        }
                    }
                    return false
                }
            })
    }

    /**
     * RecyclerView的Item点击事件监听接口
     */
    interface OnItemClickListener {
        /**
         * 当ItemView的单击事件触发时调用
         * @param view View?
         * @param position Int
         */
        fun onItemClick(view: View?, position: Int)

        /**
         * 当ItemView的长按事件触发时调用
         * @param view View?
         * @param position Int
         */
        fun onItemLongClick(view: View?, position: Int)

        /**
         * 当ItemView的双击事件触发时调用
         * @param view View?
         * @param position Int
         */
        fun onItemDoubleClick(view: View?, position: Int)
    }

    /**
     * RecyclerView的Item点击事件监听实现
     */
    inner class RcOnItemClickListener : OnItemClickListener {
        override fun onItemClick(view: View?, position: Int) {}
        override fun onItemLongClick(view: View?, position: Int) {}
        override fun onItemDoubleClick(view: View?, position: Int) {}
    }
}