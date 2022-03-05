package top.iqqcode.handlerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.handlerdemo.databinding.ActivityMainBinding
import top.iqqcode.handlerdemo.databinding.ActivityUierrorBinding
import kotlin.concurrent.thread

/**
 * @Author: iqqcode
 * @Date: 2021-06-17 12:54
 * @Description:UI阻塞测试
 */
class UIErrorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUierrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUierrorBinding.inflate(layoutInflater)
        var rootView: View = binding.root
        setContentView(rootView)

        thread(start = true) {
            var count = 0
            while (count < 50) {
                count++
                binding.textView.text = "Count = $count"
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}