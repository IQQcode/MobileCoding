package top.iqqcode.handlerdemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.handlerdemo.databinding.ActivityMainBinding
import kotlin.concurrent.thread

/**
 * @Author: iqqcode
 * @Date: 2021-06-13 11:34
 * @Description:
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    
    private val TAG: String = "TAG"
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var rootView: View = binding.root
        setContentView(rootView)

        // 设置监听
        binding.mButtonHandler.setOnClickListener(this)
        binding.mButtonDemo.setOnClickListener(this)
        binding.mButtonUIError.setOnClickListener(this)

        // 创建Handler
        val handler: Handler = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d(TAG,"running from UI thread(): ${Thread.currentThread()}")
                Log.d(TAG, "handleMessage: ${msg.what}")
                // 主线程处理消息，更新UI
                if(msg.what == 1001) {
                    binding.mTextView.text = "📺 iqqcode..."
                }
            }
        }

        binding.mButton.setOnClickListener {
            // TODO 处理耗时操作
            thread(start = true) {
                Log.d(TAG,"running from sub-thread(): ${Thread.currentThread()}")
                // 子线程中发送消息,通知UI更新
                handler.sendEmptyMessage(1001) // 发送空消息
                val msg = handler.obtainMessage() // 获取消息对象
                msg.what = 2000
                msg.obj = "要存储的消息" // 任意类型
                handler.sendMessage(msg) // 发送消息
                handler.sendEmptyMessageAtTime(3000, System.currentTimeMillis() + 3000) // 在指定时间后延迟处理
                handler.sendEmptyMessageDelayed(4000,2000) // 延迟多少时间后处理
            }
        }

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mButtonHandler -> startActivity(Intent(this@MainActivity, HandlerTestActivity::class.java))
            R.id.mButtonDemo -> startActivity(Intent(this@MainActivity, HandlerDemoActivity::class.java))
            R.id.mButtonUIError -> startActivity(Intent(this@MainActivity, UIErrorActivity::class.java))
        }
    }
}