package top.iqqcode.broadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.broadcast.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val ACTION_CAST = "top.iqqcode.action.BROADCAST"
    private lateinit var receiver: DemoBroadcast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)
        receiver = DemoBroadcast()
        register()

        binding.sendButton.setOnClickListener {
            doSendBroadcastReceiver()
        }
    }

    // 注册广播代码
    private fun register() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_CAST)
        // 注册receiver时，直接指定发送者应该具有的权限。不然外部应用依旧可以触及到receiver
        registerReceiver(receiver, intentFilter)
    }

    private fun doSendBroadcastReceiver() {
        val intent = Intent()
        intent.putExtra("data", "data = " + Date())
        intent.action = ACTION_CAST
        intent.setPackage(packageName)
        sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}