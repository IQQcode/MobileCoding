package top.iqqcode.viewevent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.viewevent.databinding.ActivityMainBinding
import top.iqqcode.viewevent.drag.TouchMoveActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.mBtnDemo01.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mBtnDemo01 -> {
                startActivity(Intent(this, TouchMoveActivity::class.java))
            }
        }
    }
}