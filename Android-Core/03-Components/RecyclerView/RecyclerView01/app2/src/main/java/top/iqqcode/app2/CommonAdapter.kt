package top.iqqcode.app2

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: iqqcode
 * @Date: 2021-04-23 13:20
 * @Description:
 */
open class CommonAdapter<T> : RecyclerView.Adapter<CommonViewHolder> {

    private var mList: List<T>

    // 声明接口
    private var onBindDataListener: OnBindDataListener<T>? = null
    private var onMoreBindDataListener: OnMoreBindDataListener<T>? = null

    constructor(mList: List<T>, onBindDataListener: OnBindDataListener<T>) {
        this.mList = mList
        this.onBindDataListener = onBindDataListener
    }

    constructor(mList: List<T>, onMoreBindDataListener: OnMoreBindDataListener<T>) {
        this.mList = mList
        this.onBindDataListener = onBindDataListener
        this.onMoreBindDataListener = onMoreBindDataListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val layoutId = onBindDataListener?.getLayoutId(viewType)
        return CommonViewHolder.getViewHolder(parent, layoutId!!)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        onBindDataListener?.onBindViewHolder(mList[position], holder, getItemViewType(position), position)
    }

    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (onMoreBindDataListener != null) {
            return onMoreBindDataListener!!.getItemType(position)
        }
        return 0
    }

    interface OnBindDataListener<T> {
        fun onBindViewHolder(model: T, holder: CommonViewHolder, type: Int, position: Int)
        fun getLayoutId(type: Int): Int
    }

    interface OnMoreBindDataListener<T> {
        fun getItemType(type: Int): Int
    }
}