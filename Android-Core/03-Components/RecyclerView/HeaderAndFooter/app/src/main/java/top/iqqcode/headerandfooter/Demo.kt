package top.iqqcode.headerandfooter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.headerandfooter.HeaderFooterAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import android.view.View
import top.iqqcode.headerandfooter.R
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup

/**
 * @Author: jiazihui
 * @Date: 2022-06-05 08:33
 * @Description:
 */
class Demo : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var adapter: HeaderFooterAdapter? = null
    var gridLayoutManager: GridLayoutManager? = null
    var layoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById<View>(R.id.rv_list) as RecyclerView
        //List布局
        layoutManager = LinearLayoutManager(this)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView!!.layoutManager = layoutManager
        mRecyclerView!!.adapter = HeaderFooterAdapter(this).also { adapter = it }

        //Grid布局 
        gridLayoutManager = GridLayoutManager(this, 2)
        mRecyclerView!!.layoutManager = gridLayoutManager // 这里用线性宫格显示 类似于grid view
        mRecyclerView!!.adapter = HeaderFooterAdapter(this).also { adapter = it }
        gridLayoutManager!!.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter!!.isHeaderView(position) || adapter!!.isBottomView(position)) gridLayoutManager!!.spanCount else 1
            }
        }
    }
}