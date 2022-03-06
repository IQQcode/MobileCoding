package top.iqqcode.clicktouch02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.clicktouch02.databinding.ActivityPageBinding

class PageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val itemName = intent.getStringExtra("itemName")
        binding.activityText.text = itemName
    }
}