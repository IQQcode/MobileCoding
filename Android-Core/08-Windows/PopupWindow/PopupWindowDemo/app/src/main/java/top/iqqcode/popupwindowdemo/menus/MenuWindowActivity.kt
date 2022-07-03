package top.iqqcode.popupwindowdemo.menus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.popupwindowdemo.databinding.ActivityMenuWindowBinding


class MenuWindowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}