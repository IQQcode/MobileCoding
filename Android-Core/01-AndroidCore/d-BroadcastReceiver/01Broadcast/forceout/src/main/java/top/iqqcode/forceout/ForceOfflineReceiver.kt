package top.iqqcode.forceout

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.WindowManager
import top.iqqcode.forceout.ActivityCollector.findAll


/**
 * @Author: iqqcode
 * @Date: 2022-02-13 12:37
 * @Description:
 */
class ForceOfflineReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        //构建一个对话框
        AlertDialog.Builder(context).apply {
            setTitle("警告")
            setMessage("你的账号被强制下线，请重新登录")
            setCancelable(false) // 并且对话框不可被取消
            setPositiveButton("重新登录") { _, _ ->
                    findAll() //销毁所有活动
                    val i = Intent(context, LoginActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //在广播接收器中启动活动 因此需要加上这个标志
                    context.startActivity(i) //重新启动登录界面
                }
            show()
        }
    }
}