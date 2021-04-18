package top.iqqcode.findview

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : Activity() {
    val updateText = 1

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // update UI
            when (msg.what) {
                updateText -> textView.text = "Hello Kotlin"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                // 将Message对象发送
                handler.sendMessage(msg)
            }
        }
    }
}