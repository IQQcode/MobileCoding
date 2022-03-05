package top.iqqcode.forceout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import top.iqqcode.forceout.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)
        /**
         * 点击按钮 发送一个"top.iqqcode.broadcast.FORCE_OFFLINE"
         * 强制下线的广播 然后在接受这条广播的类（接收器）中进行强制下线的功能
         * 在这里新建一个广播接收器 ForceOfflineReceiver 类 来继承 BroadcastReceiver
         */
        binding.forceButton.setOnClickListener {
            Toast.makeText(this, "强制用户下线", Toast.LENGTH_SHORT).show()
            val intent = Intent("top.iqqcode.broadcast.FORCE_OFFLINE")
            sendBroadcast(intent)
        }
    }
}