package top.iqqcode.okhttpdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.*
import top.iqqcode.okhttpdemo.databinding.ActivityExecutetDataBinding
import java.io.IOException


class ExecuteDataActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityExecutetDataBinding

    private lateinit var mCityName: TextView
    private lateinit var mTemperature: TextView
    private lateinit var mCondition: TextView
    private lateinit var mCityWind: TextView
    private lateinit var mTempChange: TextView
    private lateinit var mBtnSync: Button
    private lateinit var mBtnAsync: Button
    private var cityName: String = ""

    private val APIKEY: String = "1bd08e5abefe33a02a4433eba4f45a8c"
    private var url: String = "https://restapi.amap.com/v3/weather/weatherInfo"

    // 静态常量
    companion object {
        const val GET = 0x001
        const val POST = 0x002
    }

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                GET -> {
                    val lives = msg.obj as List<Live>
                    setData(lives[0])
                }
                POST -> {

                }

                // 这里的else相当于Java中switch的default;
                else -> {
                    val mBundle = msg.data

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExecutetDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddAsync.setOnClickListener(this)
        binding.btnAddSync.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setData(data: Live?) {
        binding.itemCityTvCity.text = data?.province
        binding.itemCityTvTemp.text = data?.temperature + "℃"
        binding.itemCityTvCondition.text = data?.weather
        binding.itemCityWind.text = data?.windpower
        val minTemp = data?.temperature?.toInt()?.minus(5)
        binding.itemCityTemprange.text = minTemp.toString() + "~" + data?.temperature + "℃" // 16~9℃
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_sync -> {

            }
            R.id.btn_add_async -> {
                cityName = binding.editCityName.text.toString()
                initAsyncData()
            }
        }
    }

    /**
     * 异步请求回调数据
     */
    private fun initAsyncData() {
        if (TextUtils.isEmpty(cityName)) {
            return
        }
        // val params = URLEncoder.encode(cityName, "UTF-8")
        val finalUrl = "$url?city=610100&key=$APIKEY"

        val client = OkHttpClient()
        val builder = Request.Builder()
        val request = builder
            .get() // .method()方法与.get()方法选取1种即可  .method("GET", null)
            .url(finalUrl)
            .build()

        // 创建call并调用enqueue()方法实现网络请求
        client.newCall(builder.build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("————失败了$e")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body!!.string()
                // GSON直接解析成对象
                var resultBean: TempData = Gson().fromJson(responseData, TempData::class.java)
                // 对象中拿到集合
                var livesList: List<Live> = resultBean.lives
                if ("0" == resultBean.status) {
                    return
                }
//                runOnUiThread() {
//                    // 回到主线程刷新UI
//                }
                val message = Message.obtain()
                message.what = GET
                message.obj = livesList
                mHandler.sendMessage(message)
                // 使用sendEmptyMessageDelayed延时1s后发送一条消息
                // mHandler.sendEmptyMessageDelayed(MainActivity.MESSAGE_WHAT, 1000)
            }
        })
    }
}
