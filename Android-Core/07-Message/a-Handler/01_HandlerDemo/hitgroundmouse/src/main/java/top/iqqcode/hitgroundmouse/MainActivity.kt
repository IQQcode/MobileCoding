package top.iqqcode.hitgroundmouse

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.hitgroundmouse.databinding.ActivityMainBinding
import java.lang.ref.WeakReference
import java.util.*

/**
 * @Author: iqqcode
 * @Date: 2021-06-15 15:41
 * @Description:Handler打地鼠
 */
class MainActivity : AppCompatActivity(), View.OnClickListener,View.OnTouchListener {

    private lateinit var binding: ActivityMainBinding
    private var totalCount = 0
    private var successCount = 0
    private val MAX_COUNT = 10
    private val POSITION_CODE = 1000

    // 随机坐标
    var mPosition = arrayOf(
        intArrayOf(145, 459), intArrayOf(262, 111), intArrayOf(785, 242), intArrayOf(136, 290),
        intArrayOf(521, 445), intArrayOf(778, 980),intArrayOf(758, 816), intArrayOf(154, 545),
        intArrayOf(753, 856), intArrayOf(245, 789), intArrayOf(365, 456), intArrayOf(234, 520),
        intArrayOf(998, 785), intArrayOf(446, 523), intArrayOf(321, 123), intArrayOf(452, 237)
    )

    private val mHandler = CustomHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)

        title = "Hit Groundhogs"
        binding.button.setOnClickListener(this)
        binding.imageView.setOnTouchListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> play()
        }
    }

    // 开始游戏
    private fun play() {
        // 发送消息
        binding.textView.text = "Game Start👏"
        binding.button.text = "Playing..."
        Thread.sleep(1000)
        binding.button.isEnabled = false
        nextPosition(0)
    }

    /**
     * 下次出现的随机坐标
     * @param delayTime Int
     */
    private fun nextPosition(delayTime: Int) {
        val position = Random().nextInt(mPosition.size)
        val message = Message.obtain()
        message.what = POSITION_CODE
        message.arg1 = position
        mHandler.sendMessageDelayed(message, delayTime.toLong())
        totalCount++
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.visibility = View.GONE
        successCount++
        binding.textView.text = "All Hit $successCount Groundhogs"
        return false
    }

    /**
     * 清除屏幕
     */
    private fun clearView() {
        totalCount = 0
        successCount = 0
        binding.imageView.visibility = View.GONE
        binding.button.text = "Click Start🤡"
        binding.button.isEnabled = true
    }

    private inner class CustomHandler(val activity: MainActivity) : Handler(Looper.myLooper()!!) {

        val weakReference: WeakReference<MainActivity> = WeakReference<MainActivity>(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity = weakReference.get()
            when(msg.what) {
                POSITION_CODE -> {
                    if (activity!!.totalCount > MAX_COUNT) {
                        activity.clearView()
                        Toast.makeText(this@MainActivity, "Game Over!", Toast.LENGTH_LONG).show()
                        return
                    }
                    val position = msg.arg1
                    // 设置坐标
                    activity!!.binding.imageView.x = activity!!.mPosition[position][0].toFloat()
                    activity!!.binding.imageView.y = activity!!.mPosition[position][1].toFloat()
                    activity.binding.imageView.visibility = View.VISIBLE

                    val randomTime = Random().nextInt(500) + 500
                    activity.nextPosition(randomTime)
                }
            }
        }
    }
}