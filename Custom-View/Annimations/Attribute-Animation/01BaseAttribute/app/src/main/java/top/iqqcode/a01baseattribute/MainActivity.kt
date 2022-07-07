package top.iqqcode.a01baseattribute

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.a01baseattribute.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickEvent()
    }

    private fun onClickEvent() {
        binding.buttonBaseAnimation.setOnClickListener {
            startActivity(Intent(this@MainActivity, ViewAnimationActivity::class.java))
        }
        binding.buttonFrame.setOnClickListener {
            startActivity(Intent(this@MainActivity, FrameAnimationActivity::class.java))
        }
        binding.buttonObject.setOnClickListener {
            startActivity(Intent(this@MainActivity, ObjectAnimationActivity::class.java))
        }
    }


}