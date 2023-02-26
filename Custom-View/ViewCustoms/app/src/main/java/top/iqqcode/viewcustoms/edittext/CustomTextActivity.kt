package top.iqqcode.viewcustoms.edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.viewcustoms.databinding.ActivityCustomTextBinding
import top.iqqcode.viewcustoms.databinding.ActivityMainBinding

class CustomTextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}