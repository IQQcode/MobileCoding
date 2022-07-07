package top.iqqcode.a01baseattribute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.a01baseattribute.databinding.ActivityMainBinding
import top.iqqcode.a01baseattribute.databinding.ActivityViewAnimationBinding

class ViewAnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}