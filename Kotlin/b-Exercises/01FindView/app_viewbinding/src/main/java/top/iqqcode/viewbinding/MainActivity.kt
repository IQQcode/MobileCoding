package top.iqqcode.viewbinding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import top.iqqcode.viewbinding.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : Activity() {

    // Kotlin声明的变量都必须在声明的同时对其进行初始化。 而这里我们显然无法在声明全局binding变量的同时对它进行初始化，
    // 所以这里又使用了 lateinit 关键字对binding变量进行了延迟初始化
    private lateinit var binding: ActivityMainBinding

    val updateText = 1

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                updateText -> binding.textView.text = "Hello ViewBinding"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        // 使用ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                // 将Message对象发送
                handler.sendMessage(msg)
            }
        }

        binding.buttonFragment.setOnClickListener {
            startActivity(Intent(this@MainActivity, BaseActivity::class.java))
        }
    }
}