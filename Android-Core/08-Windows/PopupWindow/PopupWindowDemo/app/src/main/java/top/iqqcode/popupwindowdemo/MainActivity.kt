package top.iqqcode.popupwindowdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.popupwindowdemo.databinding.ActivityMainBinding
import top.iqqcode.popupwindowdemo.full.FullContainerActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonFullContainer.setOnClickListener(this)
        binding.buttonMenu.setOnClickListener(this)
        binding.buttonDialog.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonFullContainer -> {
                startActivity(Intent(this@MainActivity, FullContainerActivity::class.java))
            }
            R.id.buttonMenu -> {
                // startActivity(Intent(this@MainActivity, MainActivity::class.java))
            }
        }
    }
}