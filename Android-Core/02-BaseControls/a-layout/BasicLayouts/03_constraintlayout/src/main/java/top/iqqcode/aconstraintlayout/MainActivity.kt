package top.iqqcode.aconstraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.aconstraintlayout.databinding.ActivityMainBinding

/**
 * 约束布局
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var rootView: View = binding.root
        setContentView(rootView)


    }
}