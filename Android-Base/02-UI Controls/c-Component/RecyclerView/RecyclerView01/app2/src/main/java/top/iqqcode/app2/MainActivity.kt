package top.iqqcode.app2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import top.iqqcode.app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        mList = ArrayList()

        // 模拟数据
        initData()
        val layoutManager = LinearLayoutManager(this)
        binding.mRecyclerView.layoutManager = layoutManager

        binding.mRecyclerView.adapter = CommonAdapter(mList, object : CommonAdapter.OnBindDataListener<String> {
            override fun onBindViewHolder(model: String, viewHolder: CommonViewHolder, type: Int, position: Int) {
                var fruit = mList[position];
                // viewHolder.setImageResource(R.id.mFruitImage, fruit.imageId)
                viewHolder.setText(R.id.mFruitName, model)
            }

            override fun getLayoutId(type: Int): Int {
                return R.layout.item_recycler_test
            }

        })
    }

    private fun initData() {
        for (i in 0..3) {
            val apple = Fruit("Apple", R.mipmap.ic_launcher)
            mList.add(apple.toString())
            val banana = Fruit("Banana", R.mipmap.ic_launcher)
            mList.add(banana.toString())
            val orange = Fruit("Orange", R.mipmap.ic_launcher)
            mList.add(orange.toString())
            val watermelon = Fruit("Watermelon", R.mipmap.ic_launcher)
            mList.add(watermelon.toString())
            val pear = Fruit("Pear", R.mipmap.ic_launcher)
            mList.add(pear.toString())
            val grape = Fruit("Grape", R.mipmap.ic_launcher)
            mList.add(grape.toString())
            val pineapple = Fruit("Pineapple", R.mipmap.ic_launcher)
            mList.add(pineapple.toString())
            val strawberry = Fruit("Strawberry", R.mipmap.ic_launcher)
            mList.add(strawberry.toString())
            val cherry = Fruit("Cherry", R.mipmap.ic_launcher)
            mList.add(cherry.toString())
            val mango = Fruit("Mango", R.mipmap.ic_launcher)
            mList.add(mango.toString())
        }
    }
}