package top.iqqcode.itemclick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.itemclick.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: CommonAdapter? = null
    private val dataList: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()

        val layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = layoutManager
        mAdapter = CommonAdapter(this)
        mAdapter!!.setData(dataList)
        mRecyclerView!!.adapter = mAdapter
        // 真正处理item点击事件
        mAdapter!!.setOnItemClickListener(object : CommonAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, PageActivity::class.java)
                intent.putExtra("itemName", dataList[position])
                startActivity(intent)
            }
        })
    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.recycler_view)
    }

    private fun initData() {
        for (i in 0..100) {
            dataList.add("Android🏖️ - 00$i")
        }
    }
}