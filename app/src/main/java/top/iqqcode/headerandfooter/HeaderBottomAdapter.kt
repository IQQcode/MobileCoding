package top.iqqcode.headerandfooter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import top.iqqcode.headerandfooter.HeaderBottomAdapter
import android.widget.TextView
import top.iqqcode.headerandfooter.R
import android.view.ViewGroup
import top.iqqcode.headerandfooter.HeaderBottomAdapter.ContentViewHolder
import top.iqqcode.headerandfooter.HeaderBottomAdapter.BottomViewHolder

/**
 * @Author: jiazihui
 * @Date: 2022-06-04 18:01
 * @Description:
 */
class HeaderBottomAdapter(private val mContext: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 模拟数据
    var texts = arrayOf("java", "python", "C++", "Php", ".NET", "js", "Ruby", "Swift", "OC")
    private val mLayoutInflater: LayoutInflater

    /**
     * 头部View个数
     */
    private val mHeaderCount = 1

    /**
     * 底部View个数
     */
    private val mBottomCount = 1

    //内容长度
    fun getContentItemCount(): Int {
        return texts.size
    }

    /**
     * 判断当前item是否是HeadView
     *
     * @param position
     * @return
     */
    fun isHeaderView(position: Int): Boolean {
        return mHeaderCount != 0 && position < mHeaderCount
    }

    /**
     * 判断当前item是否是FooterView
     *
     * @param position
     * @return
     */
    fun isBottomView(position: Int): Boolean {
        return mBottomCount != 0 && position >= mHeaderCount + getContentItemCount()
    }

    /**
     * 判断当前item类型
     *
     * @param position
     * @return
     */
    override fun getItemViewType(position: Int): Int {
        val dataItemCount = getContentItemCount()
        return if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部View
            ITEM_TYPE_HEADER
        } else if (mBottomCount != 0 && position >= mHeaderCount + dataItemCount) {
            //底部View
            ITEM_TYPE_BOTTOM
        } else {
            //内容View
            ITEM_TYPE_CONTENT
        }
    }

    /**
     * 内容 ViewHolder
     */
    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById<View>(R.id.tv_item_text) as TextView
        }
    }

    /**
     * 头部 ViewHolder
     */
    class HeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    )

    /**
     * 底部 ViewHolder
     */
    class BottomViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE_HEADER) {
            return HeaderViewHolder(
                mLayoutInflater.inflate(
                    R.layout.item_header_view,
                    parent,
                    false
                )
            )
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return BottomViewHolder(
                mLayoutInflater.inflate(
                    R.layout.item_footer_view,
                    parent,
                    false
                )
            )
        } else  {
            return ContentViewHolder(
                mLayoutInflater.inflate(
                    R.layout.list_item_view,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
        } else if (holder is ContentViewHolder) {
            holder.textView.text = texts[position - mHeaderCount]
        } else if (holder is BottomViewHolder) {
        }
    }

    override fun getItemCount(): Int {
        return mHeaderCount + getContentItemCount() + mBottomCount
    }

    companion object {
        // item类型
        const val ITEM_TYPE_HEADER = 0
        const val ITEM_TYPE_CONTENT = 1
        const val ITEM_TYPE_BOTTOM = 2
    }

    init {
        mLayoutInflater = LayoutInflater.from(mContext)
    }
}