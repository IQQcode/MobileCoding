package top.iqqcode.headerandfooter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.headerandfooter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: HeaderBottomAdapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mGridLayoutManager: GridLayoutManager
    private var mIsGrid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mRecyclerView = binding.rvList

        mRecyclerView.layoutManager = setIsGridLayout(true);
        mAdapter = HeaderBottomAdapter(this);
        mRecyclerView.adapter = mAdapter
        if (mIsGrid) {
            mGridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (mAdapter.isHeaderView(position) ||
                        mAdapter.isBottomView(position)
                    ) mGridLayoutManager.spanCount else 1
                }
            }
        }
    }

    private fun setIsGridLayout(isGrid: Boolean): RecyclerView.LayoutManager {
        lateinit var layoutManager: RecyclerView.LayoutManager
        if (isGrid) {
            mIsGrid = true
            mGridLayoutManager = GridLayoutManager(this, 2)
            mGridLayoutManager.orientation = GridLayoutManager.VERTICAL;
            layoutManager = mGridLayoutManager
        } else {
            mIsGrid = false
            mLinearLayoutManager = LinearLayoutManager(this);
            mLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL;
            layoutManager = mLinearLayoutManager
        }
        return layoutManager
    }
}