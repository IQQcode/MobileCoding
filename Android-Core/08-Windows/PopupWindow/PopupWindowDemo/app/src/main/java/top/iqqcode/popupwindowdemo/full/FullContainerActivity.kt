package top.iqqcode.popupwindowdemo.full

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import top.iqqcode.popupwindowdemo.databinding.ActivityFullContainerBinding

/**
 * Material ViewPager2 + TabLayout测试页面切换
 * @constructor Create empty Full container activity
 */
class FullContainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullContainerBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var mFloatView: FloatingActionButton

    private val activeColor: Int = Color.parseColor("#ff678f")
    private val normalColor: Int = Color.parseColor("#666666")
    private val activeSize = 20
    private val normalSize = 14
    private var mediator: TabLayoutMediator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager
        mFloatView = binding.floatingActionButton
        val tabs = arrayOf("关注", "推荐", "最新")

        initFloatingIcon()

        // 禁用预加载
        viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        // Adapter
        viewPager2.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
            override fun createFragment(position: Int): Fragment {
                // FragmentStateAdapter内部自己会管理已实例化的fragment对象。
                // 所以不需要考虑复用的问题
                return DemoFragment.getNewInstance(tabs[position])
            }

            override fun getItemCount(): Int {
                return tabs.size
            }
        }
        // viewPager 页面切换监听
        viewPager2.registerOnPageChangeCallback(changeCallback)
        mediator = TabLayoutMediator(tabLayout, viewPager2,
            TabConfigurationStrategy { tab, position -> //这里可以自定义TabView
                val tabView = TextView(this@FullContainerActivity)
                val states = arrayOfNulls<IntArray>(2)
                states[0] = intArrayOf(android.R.attr.state_selected)
                states[1] = intArrayOf()
                val colors = intArrayOf(activeColor, normalColor)
                val colorStateList = ColorStateList(states, colors)
                tabView.text = tabs[position]
                tabView.textSize = normalSize.toFloat()
                tabView.setTextColor(colorStateList)
                tab.customView = tabView
            })
        //要执行这一句才是真正将两者绑定起来
        mediator!!.attach()
    }

    private fun initFloatingIcon() {
        mFloatView.setOnClickListener {
            showWindowAnimation()
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun showWindowAnimation() {
        val anniShowController = AnniShowController(this);
        anniShowController.setClippingEnable(false)
        val rect = Rect()
        mFloatView.getGlobalVisibleRect(rect)
        anniShowController.show(this.findViewById(android.R.id.content), rect)
        anniShowController.stop();
    }

    private val changeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            // 可以来设置选中时tab的大小
            val tabCount = tabLayout.tabCount
            for (i in 0 until tabCount) {
                val tab = tabLayout.getTabAt(i)
                val tabView = tab!!.customView as TextView?
                if (tab.position == position) {
                    tabView!!.textSize = activeSize.toFloat()
                    tabView.typeface = Typeface.DEFAULT_BOLD
                } else {
                    tabView!!.textSize = normalSize.toFloat()
                    tabView.typeface = Typeface.DEFAULT
                }
            }
        }
    }

    override fun onDestroy() {
        mediator!!.detach()
        viewPager2.unregisterOnPageChangeCallback(changeCallback)
        super.onDestroy()
    }
}