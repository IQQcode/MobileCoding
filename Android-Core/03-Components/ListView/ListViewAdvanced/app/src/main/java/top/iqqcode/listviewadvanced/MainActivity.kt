package top.iqqcode.listviewadvanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.listviewadvanced.databinding.ActivityMainBinding
import top.iqqcode.listviewadvanced.activity.ListMultipleItemActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.listMultipleItem01.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.listMultipleItem01 -> {
                startActivity(Intent(this, ListMultipleItemActivity::class.java))
            }
        }
    }
}