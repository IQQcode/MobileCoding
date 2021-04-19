package top.iqqcode.activitydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.activitydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = "Hello Kotlin!"
        binding.button.setOnClickListener {
            Intent(this, SecondActivity::class.java).apply {
                putExtra("name", "iqqcode")
                putExtra("user", User())
                startActivity(this)
            }
        }
    }
}