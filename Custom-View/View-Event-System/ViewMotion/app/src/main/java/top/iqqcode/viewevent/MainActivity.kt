package top.iqqcode.viewevent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.viewevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.motionButton.setOnClickListener(this)
        binding.keyEventButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.motionButton -> startActivity(Intent(this@MainActivity, MotionTestActivity::class.java))
            R.id.keyEventButton -> startActivity(Intent(this@MainActivity, ParamsActivity::class.java))
        }
    }
}