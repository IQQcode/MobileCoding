package top.iqqcode.itemclick

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.itemclick.databinding.ActivityMainBinding
import top.iqqcode.itemclick.databinding.ActivityPageBinding

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