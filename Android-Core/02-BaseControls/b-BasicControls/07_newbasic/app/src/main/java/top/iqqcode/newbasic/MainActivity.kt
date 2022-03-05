package top.iqqcode.newbasic

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.newbasic.databinding.ActivityMainBinding

/**
 * @Author: iqqcode
 * @Date: 2021-06-08 16:49
 * @Description:ProcessBar使用
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 关键代码
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)

        // 如何使用
        val animation = binding.mImageView.drawable as AnimationDrawable
        binding.mImageView.postDelayed(Runnable() {
            kotlin.run {
                animation.start()
            }
        }, 100)
    }
}