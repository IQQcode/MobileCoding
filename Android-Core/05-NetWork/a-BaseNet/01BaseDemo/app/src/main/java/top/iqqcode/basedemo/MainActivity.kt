package top.iqqcode.basedemo

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import org.json.JSONObject
import top.iqqcode.basedemo.databinding.ActivityMainBinding
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

/**
 * @Author: iqqcode
 * @Date: 2021-06-09 13:02
 * @Description:原生网络请求
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var mResult: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)

        binding.mButton01.setOnClickListener(this)
        binding.mButton02.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mButton01 -> {
                Thread {
                    val urlString = "http://www.imooc.com/api/teacher?type=2&page=1"
                    mResult = requestDataByGet(urlString)
                    runOnUiThread {
                        mResult = decode(mResult)
                        binding.mTextView.text = mResult
                    }
                }.start()
            }

            R.id.mButton02 -> {
                if (!TextUtils.isEmpty(mResult)) {
                    mResult?.let { handleJSONData(it) };
                }
            }
        }
    }

    /**
     * GET请求
     * @param urlString String
     * @return String?
     */
    private fun requestDataByGet(urlString: String): String? {
        var result: String? = null
        try {
            val url = URL(urlString)
            // 打开URL链接
            val connection = url.openConnection() as HttpURLConnection
            // 超时时间
            connection.connectTimeout = 30 * 1000
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Charset", "UTF-8")
            connection.setRequestProperty("Accept-Charset", "UTF-8")

            connection.doInput = true
            connection.doOutput = true
            connection.useCaches = false

            connection.connect() // 发起连接

            val responseCode = connection.responseCode
            val responseMessage = connection.responseMessage
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                result = streamToString(inputStream)
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace();
        } catch (e: IOException) {
            e.printStackTrace();
        }
        return result
    }

    private fun requestDataByPost(urlString: String): String? {
        var result: String? = null
        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 30000;
            connection.requestMethod = "POST";
            // 设置运行输入,输出:
            connection.doOutput = true
            connection.doInput = true
            // Post方式不能缓存,需手动设置为false
            connection.useCaches = false
            connection.connect()

            // 请求的数据:
            val data = "username=" + URLEncoder.encode("imooc", "UTF-8")
                .toString() + "&number=" + URLEncoder.encode("15088886666", "UTF-8")
            // 获取输出流
            val out = connection.outputStream
            out.write(data.toByteArray())
            out.flush()
            out.close()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val reader: Reader = InputStreamReader(inputStream, "UTF-8")
                val buffer = CharArray(1024)
                reader.read(buffer)
                result = String(buffer)
                reader.close()
            } else {
                val responseMessage = connection.responseMessage
                Log.e("TAG", "requestDataByPost: $responseMessage")
            }

            val finalResult = result!!
            runOnUiThread { binding.mTextView.text = finalResult }

            connection.disconnect()
        } catch (e: MalformedURLException) {
            e.printStackTrace();
        } catch (e: IOException) {
            e.printStackTrace();
        }
        return result
    }

    /**
     * 输入流转为字符串
     * @param inputStream InputStream
     * @return String
     */
    private fun streamToString(inputStream: InputStream): String? {
        val buffer = StringBuffer()
        val inputStreamReader: InputStreamReader
        try {
            inputStreamReader = InputStreamReader(inputStream, "utf-8")
            val bufferedReader = BufferedReader(inputStreamReader)
            var str: String? = null
            while (bufferedReader.readLine().also { str = it } != null) {
                buffer.append(str)
            }
            // 释放资源
            bufferedReader.close()
            inputStreamReader.close()
            inputStream.close()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return buffer.toString()
    }

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

    /**
     * 将Unicode字符转换为UTF-8类型字符串
     * @param unicodeStr String?
     * @return String?
     */
    private fun decode(unicodeStr: String?): String? {
        if (unicodeStr == null) {
            return null
        }
        val retBuf = StringBuilder()
        val maxLoop = unicodeStr.length
        var i = 0
        while (i < maxLoop) {
            if (unicodeStr[i] == '\\') {
                if (i < maxLoop - 5
                    && (unicodeStr[i + 1] == 'u' || unicodeStr[i + 1] == 'U')
                ) try {
                    retBuf.append(unicodeStr.substring(i + 2, i + 6).toInt(16).toChar())
                    i += 5
                } catch (localNumberFormatException: NumberFormatException) {
                    retBuf.append(unicodeStr[i])
                } else {
                    retBuf.append(unicodeStr[i])
                }
            } else {
                retBuf.append(unicodeStr[i])
            }
            i++
        }
        return retBuf.toString()
    }
}