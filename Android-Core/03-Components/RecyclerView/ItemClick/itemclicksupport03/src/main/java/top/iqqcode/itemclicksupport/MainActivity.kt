package top.iqqcode.itemclicksupport

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.itemclicksupport.databinding.ActivityMainBinding


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


        ItemClickSupport.addTo(mRecyclerView!!).setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
            override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                val intent = Intent(this@MainActivity, PageActivity::class.java)
                intent.putExtra("itemName", mDatas[position])
                startActivity(intent)
            }
        })

    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.recycler_view)
    }

    private fun initData() {
        for (i in 0..100) {
            mDatas.add("Android🎨 - 00$i")
        }
    }
}