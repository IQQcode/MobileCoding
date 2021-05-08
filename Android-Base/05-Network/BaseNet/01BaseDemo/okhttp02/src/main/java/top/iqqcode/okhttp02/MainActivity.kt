package top.iqqcode.okhttp02

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.FormBody
import okio.IOException
import top.iqqcode.okhttp02.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

/**
 * @Author: iqqcode
 * @Date: 2021-06-11 18:58
 * @Description:OKHttp基础使用
 * Link: https://segmentfault.com/a/1190000017579471
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private val TAG: String = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var rootView: View = binding.root
        setContentView(rootView)

        binding.mButton01.setOnClickListener(this)
        binding.mButton02.setOnClickListener(this)
        binding.mButton03.setOnClickListener(this)
        binding.mButton04.setOnClickListener(this)
        binding.mButton05.setOnClickListener(this)
    }

    /**
     * 同步请求
     * 使用同步请求的是需要调用execute()方法，Response接收返回的对象。
     * 同步和异步请求只是最后一步的请求的方法不同而已。
     */
    private fun syncRequestByGet() {

        val urlString = "http://apis.juhe.cn/fapig/euro2020/schedule" // 请求地址
        val urlParameter = "type=2&key=828f26861263cc47c50c36a0985aff93"

        // 第一步获取okHttpClient对象
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .build()
        // 第二步构建Request对象
        val request: Request = Request.Builder()
            .url("$urlString?$urlParameter") // 可以后加请求参数 url + str
            .get() // 默认就是GET请求，可以不写
            .build()
        // 第三步构建Call对象
        val call: Call = client.newCall(request)
        // 发起同步请求(同步/异步)
        Thread {
            try {
                val response = call.execute() // 提交并且接收返回数据
                val resultData = response.body!!.string()
                Log.i(TAG, "requestByGet: $resultData")

                // 更新UI
                runOnUiThread {
                    binding.mTextViewContext.text = resultData
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun syncRequestByPost() {

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .build()

        val formBody: RequestBody = FormBody.Builder()
            .add("page", "1")
            .add("count", "2")
            .add("type", "video")
            .build()

        val request: Request = Request.Builder()
            .url("https://api.apiopen.top/getJoke")
            .post(formBody)
            .build()

        val call: Call = client.newCall(request)

        Thread {
            try {
                val response = call.execute() // 提交并且接收返回数据
                val resultData = response.body!!.string()
                // 更新UI
                runOnUiThread {
                    binding.mTextViewContext.text = resultData
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "异常", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }.start()
    }

    /**
     * Get异步请求(不需要手动创建线程)
     */
    private fun asyncRequestByGet() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .build()

        val request: Request = Request.Builder()
            .url("https://api.apiopen.top/getJoke?page=1&count=2&type=video") // 可以后加请求参数 url + str
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: java.io.IOException) {
                Log.i("TAG", e.message!!)
            }

            @Throws(java.io.IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val resultData = response.body!!.string()
                Log.i("TAG", resultData)
                runOnUiThread {
                    binding.mTextViewContext.text = resultData
                }
            }
        })
    }

    /**
     * Post请求
     * @throws java
     */
    private fun asyncRequestByPost() {
        val urlStr = "https://api.apiopen.top/getJoke"

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .build()

        // 表单数据
        val formBody = FormBody.Builder()
            .add("page", "1")
            .add("count", "2")
            .add("type", "video")
            .build()

        val request: Request = Request.Builder()
            .url(urlStr)
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: java.io.IOException) {
                Log.i("TAG", e.message!!)
            }


            @Throws(java.io.IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val resultData = response.body!!.string()
                Log.e("TAG", resultData)
                runOnUiThread {
                    binding.mTextViewContext.text = resultData
                }
            }
        })

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mButton01 -> {
                binding.mTextViewContext.text = ""
                syncRequestByGet()
            }
            R.id.mButton02 -> {
                binding.mTextViewContext.text = ""
                asyncRequestByGet()
            }
            R.id.mButton03 -> {
                binding.mTextViewContext.text = ""
                syncRequestByPost()
            }
            R.id.mButton04 -> {
                binding.mTextViewContext.text = ""
                asyncRequestByPost()
            }
            R.id.mButton05 -> Toast.makeText(this@MainActivity, "JSON Format", Toast.LENGTH_SHORT)
                .show()
        }
    }
}