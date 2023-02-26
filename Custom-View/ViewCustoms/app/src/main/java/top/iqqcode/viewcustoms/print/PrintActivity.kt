package top.iqqcode.viewcustoms.print

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.viewcustoms.databinding.ActivityPrintBinding

class PrintActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycle.addObserver(binding.printView)
    }
}