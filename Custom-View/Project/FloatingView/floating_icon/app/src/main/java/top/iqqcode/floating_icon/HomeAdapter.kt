package top.iqqcode.floating_icon

import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.floating_icon.HomeAdapter.HomeHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.view.View

class HomeAdapter : RecyclerView.Adapter<HomeHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HomeHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_home, viewGroup, false)
        return HomeHolder(view)
    }

    override fun onBindViewHolder(homeHolder: HomeHolder, position: Int) {
        homeHolder.textView.text = "TextView  这是条目的第 $position 条"
    }

    override fun getItemCount(): Int {
        return 50
    }

    inner class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            textView = itemView.findViewById(R.id.textView2)
        }
    }
}