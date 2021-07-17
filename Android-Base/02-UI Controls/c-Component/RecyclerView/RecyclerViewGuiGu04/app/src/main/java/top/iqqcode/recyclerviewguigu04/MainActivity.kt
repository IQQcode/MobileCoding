package top.iqqcode.recyclerviewguigu04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import top.iqqcode.recyclerviewguigu04.databinding.ActivityMainBinding

/**
 * @Author: iqqcode
 * @Date: 2021-06-27 14:56
 * @Description:RecyclerView使用
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private val datas = mutableListOf<String>()
    private lateinit var adapter: BaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var rootView: View = binding.root
        setContentView(rootView)

        initView()
        initData()

        // 设置适配器
        adapter = BaseAdapter(this, datas)
        binding.mRecyclerView.adapter = adapter

        // item的点击事件
        adapter.setOnItemClickListener(object : BaseAdapter.OnItemClickListener {
            override fun onItemClick(view: View, data: String) {
                val intent = Intent(this@MainActivity, NewActivity::class.java)
                intent.putExtra("position", data)
                startActivity(intent)
            }
        })
    }

    // 初始化视图
    private fun initView() {
        binding.mButton1.setOnClickListener(this)
        binding.mButton2.setOnClickListener(this)
        binding.mButton3.setOnClickListener(this)
        binding.mButton4.setOnClickListener(this)
        binding.mButton5.setOnClickListener(this)
    }

    // 初始化数据
    private fun initData() {
       for(i in 0 until 100) {
           datas.add("Count + $i")
       }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mButton1 -> {

            }

            R.id.mButton2 -> {

            }

            R.id.mButton3 -> {
                Toast.makeText(this, "ListView效果", Toast.LENGTH_SHORT).show()
                binding.mRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
            }

            R.id.mButton4 -> {
                Toast.makeText(this, "GridView效果", Toast.LENGTH_SHORT).show()
                binding.mRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3, GridLayoutManager.VERTICAL, false) //是否倒序
            }

            R.id.mButton5 -> {
                Toast.makeText(this, "Flow效果", Toast.LENGTH_SHORT).show()
                binding.mRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }
}