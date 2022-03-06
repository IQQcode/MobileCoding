package top.iqqcode.itemclicksupport

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.View.OnLongClickListener
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener

/**
 * @Author: iqqcode
 * @Date: 2022-03-05 22:18
 * @Description: 自定义的条目点击事件支持类ItemClickSupport
 */
class ItemClickSupport public constructor(private val mRecyclerView: RecyclerView) {

    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null

    private val mOnClickListener = View.OnClickListener { v ->
        if (mOnItemClickListener != null) {
            val holder = mRecyclerView.getChildViewHolder(v)
            mOnItemClickListener!!.onItemClicked(mRecyclerView, holder.adapterPosition, v)
        }
    }

    private val mOnLongClickListener = OnLongClickListener { v ->
        if (mOnItemLongClickListener != null) {
            val holder = mRecyclerView.getChildViewHolder(v)
            return@OnLongClickListener mOnItemLongClickListener!!.onItemLongClicked(mRecyclerView,
                holder.adapterPosition, v)
        }
        false
    }

    private val mAttachListener: OnChildAttachStateChangeListener =
        object : OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                if (mOnItemClickListener != null) {
                    view.setOnClickListener(mOnClickListener)
                }
                if (mOnItemLongClickListener != null) {
                    view.setOnLongClickListener(mOnLongClickListener)
                }
            }

            override fun onChildViewDetachedFromWindow(view: View) {}
        }

    fun setOnItemClickListener(listener: OnItemClickListener?): ItemClickSupport {
        mOnItemClickListener = listener
        return this
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener?): ItemClickSupport {
        mOnItemLongClickListener = listener
        return this
    }

    public fun detach(view: RecyclerView) {
        view.removeOnChildAttachStateChangeListener(mAttachListener)
        view.setTag(R.id.item_click_support, null)
    }

    interface OnItemClickListener {
        fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?)
    }

    interface OnItemLongClickListener {
        fun onItemLongClicked(recyclerView: RecyclerView?, position: Int, v: View?): Boolean
    }

    companion object {

        /**
         * 为RecyclerView设置ItemClickSupport
         * @param view [ERROR : RecyclerView]
         * @return ItemClickSupport
         */
        fun addTo(view: RecyclerView): ItemClickSupport {
            var support = view.getTag(R.id.item_click_support) as? ItemClickSupport
            if (support == null) {
                support = ItemClickSupport(view)
            }
            return support
        }

        /**
         * 为RecyclerView移除ItemClickSupport
         * @param view [ERROR : RecyclerView]
         * @return ItemClickSupport?
         */
        fun removeFrom(view: RecyclerView): ItemClickSupport? {
            val support = view.getTag(R.id.item_click_support) as? ItemClickSupport
            support?.detach(view)
            return support
        }
    }

    init {
        mRecyclerView.setTag(R.id.item_click_support, this)
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener)
    }
}