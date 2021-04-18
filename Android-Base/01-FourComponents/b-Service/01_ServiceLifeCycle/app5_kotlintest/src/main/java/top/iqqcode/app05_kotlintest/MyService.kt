package top.iqqcode.app05_kotlintest

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

/**
 * @Author: iqqcode
 * @Date: 2021-04-11 15:13
 * @Description:
 */

class MyService : Service() {

    private val mBinder = DownloadBinder()

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_service", "前台Service通知", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pi = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "my_service")
                .setContentTitle("This is content title")
                .setContentText("This is Content text")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background))
                .setContentIntent(pi)
                .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    class DownloadBinder : Binder() {
        fun startDownload() {
            Log.d("TAG", "startDownload: execute")
        }

        fun getProcess(): Int {
            Log.d("TAG", "getProcess")
            return 0;
        }
    }
}
