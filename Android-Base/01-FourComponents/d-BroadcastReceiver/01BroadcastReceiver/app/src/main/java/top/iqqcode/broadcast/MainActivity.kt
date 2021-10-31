package top.iqqcode.broadcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.broadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)

    }
}