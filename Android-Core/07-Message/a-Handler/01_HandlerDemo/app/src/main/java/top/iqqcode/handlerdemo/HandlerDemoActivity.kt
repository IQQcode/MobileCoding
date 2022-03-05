package top.iqqcode.handlerdemo

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.handlerdemo.databinding.ActivityHandlerDemoBinding
import java.lang.ref.WeakReference
import java.util.*


/**
 * @Author: iqqcode
 * @Date: 2021-06-13 21:45
 * @Description:数字加减变化Demo
 */
class HandlerDemoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHandlerDemoBinding

    private val WHAT_INCREASE = 100
    private val WHAT_DECREASE = 200
    private val RAND_COLOR = 300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandlerDemoBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)

        // 弱引用Handler
        val weakHandler = CountDownTimeHandler(this)

        val message = Message.obtain()
        message.what = WHAT_INCREASE
        handler.sendMessageDelayed(message, 1000)

        // 使用定时器
        val timer = Timer();
        timer.schedule(object: TimerTask() {
            override fun run() {
                handler.sendEmptyMessage(RAND_COLOR)
            }
        }, 1000,1000)

        binding.mDecrease.setOnClickListener(this)
        binding.mIncrease.setOnClickListener(this)
        binding.mPause.setOnClickListener(this)
    }

    private val handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // 得到当前现实的数值
            var number = Integer.parseInt(binding.mNumber.text.toString())
            when (msg!!.what) {
                // 限制number <= 20
                WHAT_INCREASE -> {
                    if (number == 20) {
                        // 设置暂停不能操作
                        binding.mPause.isEnabled = false;
                        Toast.makeText(this@HandlerDemoActivity, "已经达到最大值", Toast.LENGTH_SHORT).show();
                        return
                    }
                    number++
                    binding.mNumber.text = number.toString()
                    // 发送增加的延迟消息
                    sendEmptyMessageDelayed(WHAT_INCREASE, 1000) // 延迟多少时间后处理
                }

                WHAT_DECREASE -> {
                    // 限制number >= 1
                    if (number == 1) {
                        //设置暂停不能操作
                        binding.mPause.isEnabled = false;
                        Toast.makeText(this@HandlerDemoActivity, "已经达到最小值", Toast.LENGTH_SHORT)
                            .show();
                        return
                    }
                    number--;
                    binding.mNumber.text = number.toString();
                    // 发送减少的延迟消息
                    sendEmptyMessageDelayed(WHAT_DECREASE, 1000);
                }
                RAND_COLOR -> {
                    // 使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
                    binding.mNumber.setTextColor(randomTextColor())
                }
            }
        }
    }

    /**
     * 静态Handler--弱引用防止内存泄漏
     * @property mWeakReference WeakReference<HandlerDemoActivity>
     * @constructor
     */
    private inner class CountDownTimeHandler(activity: HandlerDemoActivity) : Handler(Looper.myLooper()!!) {

        val mWeakReference: WeakReference<HandlerDemoActivity> = WeakReference<HandlerDemoActivity>(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // TODO --> 更换
        }

    }

    /**
     * 获取随机rgb颜色值
     * @return Int
     */
    private fun randomTextColor(): Int {
        val random = Random()
        // 0-190 ,如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        val red = random.nextInt(150)
        val green = random.nextInt(150)
        val blue = random.nextInt(150)
        // 使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mIncrease -> {
                // 限制Button可操作性
                binding.mIncrease.isEnabled = false
                binding.mDecrease.isEnabled = true
                binding.mPause.isEnabled = true

                // 停止减少(移除未处理的减少的消息)
                handler.removeMessages(WHAT_DECREASE);
                // 发消息(增加)
                handler.sendEmptyMessage(WHAT_INCREASE);
            }
            R.id.mDecrease -> {
                // 限制Button可操作性
                binding.mIncrease.isEnabled = false
                binding.mDecrease.isEnabled = true
                binding.mPause.isEnabled = true

                // 停止增加(移除未处理的增加的消息)
                handler.removeMessages(WHAT_INCREASE);

                // 发消息(减少)
                handler.sendEmptyMessage(WHAT_DECREASE);
            }
            R.id.mPause -> {
                // 限制Button可操作性
                binding.mIncrease.isEnabled = false
                binding.mDecrease.isEnabled = true
                binding.mPause.isEnabled = true

                // 停止增加/减少(移除未处理的减少/增加的消息)
                handler.removeMessages(WHAT_INCREASE);
                handler.removeMessages(WHAT_DECREASE);
            }
        }
    }
}