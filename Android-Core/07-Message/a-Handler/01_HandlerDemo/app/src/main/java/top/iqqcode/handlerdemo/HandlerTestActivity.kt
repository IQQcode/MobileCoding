package top.iqqcode.handlerdemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import top.iqqcode.handlerdemo.databinding.ActivityHandlerTestBinding
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * @Author: iqqcode
 * @Date: 2021-06-13 18:01
 * @Description:使用Handler实现异步任务
 * 1. 创建Handler成员变量对象, 并重写其handleMessage()
 * 2. 在分/主线程创建Message对象
 * 3. 使用handler对象发送Message
 * 4. 在handleMessage()中处理消息
 */
class HandlerTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHandlerTestBinding
    private val urlString = "http://japi.juhe.cn/qqevaluate/qq?qq=2726109782&key=e6ce0de3a1db3739d4343b9d19a9d61f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandlerTestBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)
        binding.mButton.setOnClickListener {
            asyncRequestByGet(urlString)
        }
    }

    // 1. 创建Handler成员变量对象, 并重写其handleMessage()
    private val handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what === 2000) {
                // 4. 在handleMessage()中处理消息
                val result = msg.obj as String
                binding.mEditText.setText(result)
                binding.mProgressBar.visibility = View.INVISIBLE
            }
        }
    }

    /**
     * 请求服务器端,得到返回的结果字符串(在子线程中请求)
     * @return
     * @throws Exception
     */
    @Throws(java.lang.Exception::class)
    fun asyncRequestByGet(path: String?) {

        // 主线程, 显示提示视图(ProgressDialog/ProgressBar)
        binding.mProgressBar.visibility = View.VISIBLE;

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .build()

        val request: Request = Request.Builder()
            .url(path.toString())
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("TAG", e.message!!)
            }

            override fun onResponse(call: Call, response: Response) {
                val resultData = response.body!!.string()
                // 分线程, 联网请求, 并得到响应数据
                thread(start = true) {
                    Thread.sleep(3000)
                    try {
                        Log.i("TAG", "asyncRequestByGet: ==> $resultData")
                        // 2. 在分/主线程创建Message对象
                        val message = Message.obtain()
                        message.what = 2000 //标识
                        message.obj = resultData
                        // 3. 使用handler对象发送Message
                        handler.sendMessage(message)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}