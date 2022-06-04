package top.iqqcode.custombase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.custombase.databinding.ActivityMainBinding
import top.iqqcode.custombase.edittext.CustomTextActivity
import top.iqqcode.custombase.print.PrintActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customEditButton.setOnClickListener(this)
        binding.printButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.customEditButton -> startActivity(Intent(this@MainActivity, CustomTextActivity::class.java))
            R.id.printButton -> startActivity(Intent(this@MainActivity, PrintActivity::class.java))
        }
    }
}