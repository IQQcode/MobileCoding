package top.iqqcode.headerandfooter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.iqqcode.headerandfooter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val LINEAR = 1
        private const val GRID = 2
    }

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: HeaderFooterAdapter
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mRecyclerView = binding.rvList
        mAdapter = HeaderFooterAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = setLayoutManagerStyle(2);
    }

    private fun setLayoutManagerStyle(layoutType: Int): LinearLayoutManager {
        when (layoutType) {
            GRID -> {
                mGridLayoutManager = GridLayoutManager(this, 2);
                mGridLayoutManager.orientation = LinearLayoutManager.VERTICAL;
                mGridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (mAdapter.isHeaderView(position) || mAdapter.isBottomView(position)) mGridLayoutManager.spanCount else 1
                    }

                };
                return mGridLayoutManager
            }
            else -> {
                mLinearLayoutManager = LinearLayoutManager(this);
                mLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL;
                return mLinearLayoutManager
            }
        }
    }
}