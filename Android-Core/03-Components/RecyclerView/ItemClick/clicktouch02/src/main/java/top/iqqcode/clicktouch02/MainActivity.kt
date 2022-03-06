package top.iqqcode.clicktouch02

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.clicktouch02.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: CommonAdapter? = null
    private val mDatas: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()

        val layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = layoutManager
        mAdapter = CommonAdapter(this)
        mAdapter!!.setData(mDatas)
        mRecyclerView!!.adapter = mAdapter

        mRecyclerView!!.addOnItemTouchListener(object : ItemClickListener(mRecyclerView!!) {
            override fun onItemClick(viewHolder: RecyclerView.ViewHolder?) {
                val position = viewHolder?.adapterPosition
                val intent = Intent(this@MainActivity, PageActivity::class.java)
                intent.putExtra("itemName", mDatas[position!!])
                startActivity(intent)
            }
        })
    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.recycler_view)
    }

    private fun initData() {
        for (i in 0..100) {
            mDatas.add("Android🏖️ - 00$i")
        }
    }
}