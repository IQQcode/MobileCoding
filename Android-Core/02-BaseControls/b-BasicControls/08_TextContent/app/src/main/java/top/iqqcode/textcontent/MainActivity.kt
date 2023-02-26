package top.iqqcode.textcontent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.textcontent.databinding.ActivityMainBinding
import top.iqqcode.textcontent.span.AtActivity
import top.iqqcode.textcontent.span.SpanActivity


class MainActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButton()
    }

    private fun initButton() {
        binding.buttonA.setOnClickListener(this)
        binding.buttonB.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonA -> {
                startActivity(Intent(this, SpanActivity::class.java))
            }
            R.id.buttonB -> {
                startActivity(Intent(this, AtActivity::class.java))
            }
        }
    }
}