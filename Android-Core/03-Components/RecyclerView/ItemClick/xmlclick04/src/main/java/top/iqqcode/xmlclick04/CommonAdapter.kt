package top.iqqcode.xmlclick04

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

/**
 * @Author: iqqcode
 * @Date: 2022-03-05 16:09
 * @Description:实体类
 */
class CommonAdapter(private val mContext: Context) : RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

    private var mList: List<String>? = null
    private val inflater: LayoutInflater = LayoutInflater.from(mContext)

    fun setData(list: List<String>?) {
        mList = list
    }

    /**
     * 创建ViewHolder实例
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.list_item_view, null)
        return ViewHolder(view)
    }

    /**
     * 对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: CommonAdapter.ViewHolder, position: Int) {
        val itemData = mList!![position]
        holder.textView.text = itemData
    }

    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @JvmField
        var textView: TextView = itemView.findViewById(R.id.itemContent)
    }
}