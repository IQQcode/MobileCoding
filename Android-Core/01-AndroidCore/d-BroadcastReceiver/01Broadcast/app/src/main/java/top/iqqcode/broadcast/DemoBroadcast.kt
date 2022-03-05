package top.iqqcode.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


/**
 * @Author: iqqcode
 * @Date: 2022-02-12 18:34
 * @Description:
 */
class DemoBroadcast : BroadcastReceiver() {

    /**
     * 广播接收器
     * @param context Context
     * @param intent Intent
     */
    override fun onReceive(context: Context?, intent: Intent) {
        Toast.makeText(context, "接收到新广播~", Toast.LENGTH_LONG).show()
        Log.i("TEST", "onReceive: " + intent?.getStringExtra("data"))
    }
}