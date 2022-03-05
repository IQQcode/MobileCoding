package top.iqqcode.recyclerviewguigu04

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: iqqcode
 * @Date: 2021-06-27 16:18
 * @Description:RecyclerView item点击效果
 */
class BaseAdapter(context: Context, list: MutableList<String>) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    private var mContext: Context = context
    private var datas: MutableList<String> = list

    private lateinit var onItemClickListener: OnItemClickListener

    // item点击事件的回调接口
    interface OnItemClickListener {
        /**
         * 当RecyclerView某个item点击时被回调
         * @param view View 点击item的视图
         * @param data String 点击得到的数据
         */
        fun onItemClick(view: View, data: String)
    }

    /**
     * 设置某个item的监听
     * @param onItemClickListener OnItemClickListener
     */
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    /**
     * 相当于getView()方法中创建 View & ViewHolder
     * @param parent ViewGroup
     * @param viewType Int
     * @return ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = View.inflate(mContext, R.layout.item_view_recycler, null)
        return ViewHolder(itemView)
    }

    /**
     * 绑定数据
     * @param holder ViewHolder
     * @param position Int
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = datas[position]
        holder.mTextView.text = data
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mImageview: ImageView = itemView.findViewById(R.id.icon)
        val mTextView: TextView = itemView.findViewById(R.id.title)

        init {
            // item点击事件
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(mContext, "item点击事件 + ${datas[layoutPosition]}", Toast.LENGTH_SHORT).show()

                if(onItemClickListener != null) {
                    onItemClickListener.onItemClick(itemView, datas[layoutPosition])
                }
            })

            // 图片单独的点击事件
            mImageview.setOnClickListener(View.OnClickListener {
                Toast.makeText(mContext, "item 图片的点击事件", Toast.LENGTH_SHORT).show()
                if(onItemClickListener != null) {
                    val intent = Intent()
                    intent.putExtra("imageClick","🎨图片单独的点击事件")
                    onItemClickListener.onItemClick(itemView, datas[layoutPosition])
                }
            })
        }
    }
}