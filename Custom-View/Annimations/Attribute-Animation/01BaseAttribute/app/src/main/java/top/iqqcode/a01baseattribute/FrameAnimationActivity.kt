package top.iqqcode.a01baseattribute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.a01baseattribute.databinding.ActivityFrameAnimationBinding
import top.iqqcode.a01baseattribute.databinding.ActivityObjectAnimationBinding

class FrameAnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFrameAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}