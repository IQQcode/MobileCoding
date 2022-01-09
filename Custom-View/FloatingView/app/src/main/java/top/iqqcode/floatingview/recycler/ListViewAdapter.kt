package top.iqqcode.floatingview.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.floatingview.R

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 21:23
 * @Description:ListView效果的适配器
 */
class ListViewAdapter(context: Context?, private val data: List<ItemBean>?) : RecyclerView.Adapter<ListViewAdapter.ViewHolder?>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * 创建item的View
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.item_list_view, null, false)
        return ViewHolder(view)
    }

    /**
     * 绑定holder, 用来设置数据
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data!![position])
    }

    /**
     * 返回item个数
     * @return
     */
    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.item_icon)
        private val title: TextView = itemView.findViewById<TextView>(R.id.item_text)
        fun getData(): List<ItemBean>? {
            return data
        }

        /**
         * 设置数据
         * @param itemBean
         */
        fun setData(itemBean: ItemBean) {
            icon.setImageResource(itemBean.imageId)
            title.text = itemBean.title
        }
    }
}