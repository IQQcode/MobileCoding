package top.iqqcode.floatingview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.floatingview.databinding.ActivityMainBinding
import top.iqqcode.floatingview.recycler.Datas
import top.iqqcode.floatingview.recycler.ItemBean
import top.iqqcode.floatingview.recycler.ListViewAdapter
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var listData: ArrayList<ItemBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        // ListView<DataBean> ---> Adapter ---> setAdapter ---> 显示数据
        listData = ArrayList()
        // 模拟数据
        for (i in Datas.icons.indices) {
            // 创建数据对象
            val data = ItemBean()
            data.imageId = Datas.icons[i]
            data.title = "我是第" + i + "个item"
            listData!!.add(data)
        }

        // RecyclerView需要设置样式，设置布局管理器
        val layoutManager = LinearLayoutManager(this)
        binding.mRecyclerView!!.layoutManager = layoutManager

        // 创建适配器(处理数据，将数据恰当的显示在View上)
        val adapter = ListViewAdapter(this, listData)
        binding.mRecyclerView!!.adapter = adapter
    }

    /**
     * Menu菜单条目
     * @param menu
     * @return
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Menu菜单被选中
     * @param item
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (itemId) {
            R.id.list_view_vertical_standard -> Log.i(TAG, "点击了ListView的垂直标准")
            R.id.list_view_vertical_reverse -> {}
            R.id.list_view_horizontal_standard -> {}
            R.id.list_view_horizontal_reverse -> {}
            R.id.grid_view_vertical_standard -> {}
            R.id.grid_view_vertical_reverse -> {}
            R.id.grid_view_horizontal_standard -> {}
            R.id.grid_view_horizontal_reverse -> {}
            R.id.stagger_view_vertical_standard -> {}
            R.id.stagger_view_vertical_reverse -> {}
            R.id.stagger_view_horizontal_standard -> {}
            R.id.stagger_view_horizontal_reverse -> {}
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG = "TAG"
    }
}