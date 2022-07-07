package top.iqqcode.a01baseattribute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.a01baseattribute.databinding.ActivityObjectAnimationBinding
import top.iqqcode.a01baseattribute.databinding.ActivityViewAnimationBinding

class ObjectAnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityObjectAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjectAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}