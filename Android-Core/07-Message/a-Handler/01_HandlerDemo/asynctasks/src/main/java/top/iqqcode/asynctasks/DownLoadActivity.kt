package top.iqqcode.asynctasks

import android.Manifest
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import top.iqqcode.asynctasks.databinding.ActivityDownloadBinding
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


/**
 * @Author: iqqcode
 * @Date: 2021-06-15 10:48
 * @Description:弹框下载Demo
 */
class DownLoadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDownloadBinding
    private lateinit var mDialog: ProgressDialog
    private lateinit var apkFile: File
    private val urlString = "https://www.wandoujia.com/apps/33889/download/dot?ch=detail_normal_dl"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloadBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)

        requestPermissions(101)

        binding.mButton.setOnClickListener {
            downloadApk()
        }
    }

    private fun downloadApk() {
        // 启动异步任务处理
        MyAsyncTask().execute()
    }

    private inner class MyAsyncTask() : AsyncTask<String?, Int?, String?>() {

        // 主线程显示视图
        override fun onPreExecute() {
            super.onPreExecute()
            mDialog = ProgressDialog(this@DownLoadActivity)
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            mDialog.show()

            //准备用于保存APK文件的File对象 : /storage/sdcard/Android/package_name/files/xxx.apk
            apkFile = File(getExternalFilesDir(null), "update.apk")
        }

        // 分线程，请求网络
        override fun doInBackground(vararg params: String?): String? {
            try {
                // 1. 得到连接对象
                val path = urlString
                val url = URL(path)
                val conn = url.openConnection() as HttpURLConnection
                // 2. 设置超时时间
                conn.connectTimeout = 5000
                conn.readTimeout = 10000
                // 3. 连接
                conn.connect()
                // 4. 请求并获得响应
                val responseCode = conn.responseCode
                if (responseCode == 200) {
                    // 设置dialog的最大进度
                    mDialog.max = conn.contentLength
                    // 5. 得到包含APK文件数据的InputStream
                    val inputStream: InputStream = conn.inputStream
                    // 6. 创建指向apkFile的FileOutputStream
                    val outputStream = BufferedOutputStream(FileOutputStream(File(Environment.getExternalStorageDirectory().toString() + "/Download/")))
                    // val outputStream = FileOutputStream(apkFile)
                    // 7. 边读边写
                    val buffer = ByteArray(1024)
                    while (inputStream.read(buffer) != -1) {
                        val len = inputStream.read(buffer)
                        outputStream.write(buffer, 0, len)
                        // 8. 显示下载进度
                        mDialog.incrementProgressBy(len)
                        //在分线程中, 发布当前进度
                        publishProgress(len)
                        // 模拟网速慢
                        SystemClock.sleep(50)
                        outputStream.flush()
                    }
                    // 9. 下载完成, 关闭, 进入==> 3
                    conn.disconnect()
                    outputStream.close()
                    inputStream.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return "DownLoad is OK！"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            mDialog.dismiss();
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            values[0]?.let { mDialog.incrementProgressBy(it) };
        }
    }

    private fun requestPermissions(requestCode: Int) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                val requestPerssionArr = ArrayList<Any>()
                val hasSdcardWrite = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                if (hasSdcardWrite != PackageManager.PERMISSION_GRANTED) {
                    requestPerssionArr.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
                // 是否应该显示权限请求 WRITE_SETTINGS
                if (requestPerssionArr.size >= 1) {
                    val requestArray = arrayOfNulls<String>(requestPerssionArr.size)
                    for (i in requestArray.indices) {
                        requestArray[i] = requestPerssionArr[i].toString()
                    }
                    requestPermissions(requestArray, requestCode)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}