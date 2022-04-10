package top.iqqcode.custombase.edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.custombase.databinding.ActivityCustomTextBinding
import top.iqqcode.custombase.databinding.ActivityMainBinding

class CustomTextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}