package top.iqqcode.recyclerviewguigu04

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.recyclerviewguigu04.databinding.ActivityNewBinding


class NewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        var rootView: View = binding.root
        setContentView(rootView)

        binding.mNewText.text = intent.getStringExtra("position")
        binding.mNewText.text = intent.getStringExtra("imageClick")
    }
}