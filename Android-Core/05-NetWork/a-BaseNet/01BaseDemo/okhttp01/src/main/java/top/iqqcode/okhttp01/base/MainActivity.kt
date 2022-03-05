package top.iqqcode.okhttp01.base


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import top.iqqcode.okhttp01.R
import top.iqqcode.okhttp01.databinding.ActivityMainBinding
import top.iqqcode.okhttp01.plus.OkhttpActivity
import java.io.IOException


/**
 * @Author: iqqcode
 * @Date: 2021-06-11 14:06
 * @Description:OKHttp使用
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val GET: Int = 1
    private val POST: Int = 2
    private val TAG: String = "TAG"


    private val urlString = "http://www.imooc.com/api/teacher?type=2&page=1"

    private val client: OkHttpClient = OkHttpClient()

    // OKHTTP MediaType
    val JSON = "application/json; charset=utf-8".toMediaType()
    private var mResult: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)

        binding.mButtonNew.setOnClickListener(this)
        binding.mButton01.setOnClickListener(this)
        binding.mButton02.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.mButtonNew -> startActivity(Intent(this@MainActivity, OkhttpActivity ::class.java))

            R.id.mButton01 -> {
                Log.i(TAG, "onClick: mButton01被点击")
                binding.mTextView.text = ""
                requestDataByPost()
            }
            R.id.mButton02 -> {
                if (!TextUtils.isEmpty(mResult)) {
                    mResult?.let { handleJSONData(it) };
                }
            }
        }
    }

    /**
     * Get请求网络数据
     */
    private fun requestDataByGet() {
        Thread {
            mResult = DecodeUtils.decode(getNet(urlString))
            val message = Message.obtain()
            message.what = GET
            message.obj = mResult
            handler.sendMessage(message) // Handler发送数据
        }.start()
    }

    /**
     * Post请求网络数据
     */
    private fun requestDataByPost() {
        Thread {
            mResult = DecodeUtils.decode(postNet(urlString, ""))
            val message = Message.obtain()
            message.what = POST
            message.obj = mResult
            handler.sendMessage(message) // Handler发送数据
        }.start()
    }

    /**
     * OKHttp获取网址(POST)
     * @param url String?
     * @param json String?
     * @return String?
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun postNet(url: String?, json: String?): String? {
        val body: RequestBody = json.toString().toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(url.toString())
            .post(body)
            .build()
        client.newCall(request).execute().use { response -> return response.body!!.string() }
    }

    /**
     * OKHttp获取网址(GET)
     * 载一个 URL 并将其内容打印为字符串
     * @param url String
     * @return String?
     */
    private fun getNet(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()
    }

    private var handler: Handler = object : Handler(Looper.getMainLooper()) {
        @SuppressLint("HandlerLeak")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // 获取数据
            when (msg.what) {
                GET -> binding.mTextView.text = msg.obj as String

                POST -> binding.mTextView.text = msg.obj as String
            }
        }
    }


    /**
     * 格式化JSON数据
     * @param json String
     */
    private fun handleJSONData(json: String) {
        try {
            val jsonObject = JSONObject(json)
            val lessonResult = LessonResult(0, ArrayList<LessonResult.Lesson>())
            val lessonList: MutableList<LessonResult.Lesson> = ArrayList()
            val status = jsonObject.getInt("status")
            // data数组
            val lessons = jsonObject.getJSONArray("data")
            if (lessons != null && lessons.length() > 0) {
                for (index in 0 until lessons.length()) {
                    val item = lessons[0] as JSONObject
                    val id = item.getInt("id")
                    val name = item.getString("name")
                    val smallPic = item.getString("picSmall")
                    val bigPic = item.getString("picBig")
                    val description = item.getString("description")
                    val learner = item.getInt("learner")
                    val lesson: LessonResult.Lesson = LessonResult.Lesson()
                    lesson.ID = id
                    lesson.name = name
                    lesson.smallPictureUrl = smallPic
                    lesson.bigPictureUrl = bigPic
                    lesson.description = description
                    lesson.learnerNumber = learner
                    lessonList.add(lesson)
                }
                lessonResult.mStatus = status
                lessonResult.mLessons = lessonList as ArrayList<LessonResult.Lesson>
                binding.mTextView.text = "data is : $lessonResult"
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}