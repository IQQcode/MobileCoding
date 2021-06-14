package top.iqqcode.asynctasks

import android.Manifest
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.asynctasks.databinding.ActivityMainBinding
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


/**
 * @Author: iqqcode
 * @Date: 2021-06-14 16:25
 * @Description:AsyncTask基本使用(下载实例)
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val MAX_PROGRESS = 0 // 需要更新的最大进度值
    private val CURRENT_PROGRESS = 1 // 当前下载的进度值
    private val DOWNLOAD_OK = "下载完成" // 下载完成

    private val urlString =
        "https://iqqcode-blog.oss-cn-beijing.aliyuncs.com/img-2021-befo/%E3%80%90%E6%B4%BE%E5%A4%A7%E6%98%9F%E3%80%91%E7%83%AD%E7%88%B1105%C2%B0%E7%9A%84%E4%BD%A0.352073335.flv"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)

        requestPermissions(101)

        binding.mButton.setOnClickListener {
            // 启用AsyncTask，传入需要执行的内容
            MyAsyncTask(this).execute(urlString)
        }
    }

    // MyAsyncTask(this).execute()
    private inner class MyAsyncTask(val activity: MainActivity) :
        AsyncTask<String?, Int?, String?>() {

        // 执行任务之前触发的事件方法，可以在该方法中作一些初始化工作
        override fun onPreExecute() {
            super.onPreExecute()
            // 准备下载前的初始进度
            activity.binding.mProgressBar.progress = 0
        }

        /**
         * 类似于在线程中执行任务，不能访问UI组件
         * @param params Array<out String?>
         * @return String?
         */
        override fun doInBackground(vararg params: String?): String? {
            val str = params[0]
            try {
                val url = URL(str)
                // 获取连接
                val conn = url.openConnection() as HttpURLConnection
                val size = conn.contentLength // 获取文件大小
                Log.i("TAG", "doInBackground: ==> $size")
                // 根据下载文件大小设置进度条最大值（使用标记区别实时进度更新)
                publishProgress(MAX_PROGRESS, size) // 进度变化，onProgressUpdate会更新
                val bytes = ByteArray(1024)
                val inputStream = conn.inputStream
                // 循环下载（边读取边存入）
                val out = BufferedOutputStream(FileOutputStream(File(Environment.getExternalStorageDirectory().toString() + "/Download/demo.flv")))
                while (inputStream.read(bytes) != -1) {
                    val len = inputStream.read(bytes)
                    out.write(bytes, MAX_PROGRESS, len)
                    // 实时更新下载进度（使用标记区别最大值）
                    publishProgress(CURRENT_PROGRESS, len)
                    out.flush()
                }
                inputStream.close()
                out.close()
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return DOWNLOAD_OK
        }

        /**
         * 更新进度值，在publishProgress被调用后，UI thread会调用这个方法
         * @param values Array<out Int?>
         */
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            when (values[0]) {
                MAX_PROGRESS -> activity.binding.mProgressBar.max = values[1]!!
                CURRENT_PROGRESS -> activity.binding.mProgressBar.incrementProgressBy(values[1]!!)
            }
        }

        /**
         * doInBackground方法执行完后被UI thread执行
         * @param result String?
         */
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result == DOWNLOAD_OK) {
                binding.mTextView.visibility = View.VISIBLE
                binding.mProgressBar.visibility = View.GONE
            }
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