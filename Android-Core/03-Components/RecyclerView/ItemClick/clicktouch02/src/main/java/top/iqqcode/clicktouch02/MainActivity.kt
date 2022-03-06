package top.iqqcode.clicktouch02

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.clicktouch02.databinding.ActivityMainBinding


class MainActivity(onItemClickListener: Any?) : AppCompatActivity() {

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

        // 抽象类来实现实现
        // setClickListenerWithTouch()

        // 接口回调来实现
        setClickListener()
    }

    private fun setClickListenerWithTouch() {
        mRecyclerView?.addOnItemTouchListener(object : ItemClickListener(mRecyclerView) {
            override fun onItemClick(viewHolder: RecyclerView.ViewHolder?) {
                val position = viewHolder?.adapterPosition
                val intent = Intent(this@MainActivity, PageActivity::class.java)
                intent.putExtra("itemName", mDatas[position!!])
                startActivity(intent) // 跳转新Activity，将item的数据传入新Activity
            }

            override fun onItemLongClick(vh: RecyclerView.ViewHolder?) {
                Toast.makeText(this@MainActivity, "Item Long Click", Toast.LENGTH_SHORT).show()
            }

            override fun onItemDoubleClick(vh: View?) {
                Toast.makeText(this@MainActivity, "Double Click", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setClickListener() {
        mRecyclerView?.addOnItemTouchListener(SimpleItemClickListener(mRecyclerView!!,
            object : SimpleItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    Toast.makeText(this@MainActivity, "touch click:$position", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onItemLongClick(view: View?, position: Int) {
                    Toast.makeText(this@MainActivity,
                        "touch long click:$position",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onItemDoubleClick(view: View?, position: Int) {
                    TODO("Not yet implemented")
                }
            }))
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