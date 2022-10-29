package top.iqqcode.custombase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import top.iqqcode.custombase.databinding.ActivityMainBinding
import top.iqqcode.custombase.edittext.CustomTextActivity
import top.iqqcode.custombase.entrance.FrsFloatEntranceActivity
import top.iqqcode.custombase.floats.anni.ExpendBaseDemoActivity
import top.iqqcode.custombase.expend.ExpendClickActivity
import top.iqqcode.custombase.floats.FloatExpandActivity
import top.iqqcode.custombase.floats.IMGroupEntryActivity
import top.iqqcode.custombase.print.PrintActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClick()
    }

    private fun initClick() {
        binding.customEditButton.setOnClickListener(this)
        binding.printButton.setOnClickListener(this)
        binding.expendButton.setOnClickListener(this)
        binding.groupEntryButton.setOnClickListener(this)
        binding.floatExpendButton.setOnClickListener(this)
        binding.floatExpendBaseDemo.setOnClickListener(this)
        binding.frsFloatExpendDemo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.customEditButton -> startActivity(Intent(this@MainActivity, CustomTextActivity::class.java))
            R.id.printButton -> startActivity(Intent(this@MainActivity, PrintActivity::class.java))
            R.id.expendButton -> startActivity(Intent(this@MainActivity, ExpendClickActivity::class.java))
            R.id.groupEntryButton -> startActivity(Intent(this@MainActivity, IMGroupEntryActivity::class.java))
            R.id.floatExpendButton -> startActivity(Intent(this@MainActivity, FloatExpandActivity::class.java))
            R.id.floatExpendBaseDemo -> startActivity(Intent(this@MainActivity, ExpendBaseDemoActivity::class.java))
            R.id.frsFloatExpendDemo -> startActivity(Intent(this@MainActivity, FrsFloatEntranceActivity::class.java))
        }
    }
}