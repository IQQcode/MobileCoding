package top.iqqcode.viewcustoms.expend.spread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import top.iqqcode.viewcustoms.R
import top.iqqcode.viewcustoms.databinding.ActivitySpreadBinding

class SpreadActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpreadBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp() =
        findNavController(this, R.id.spreadFragmentContainer).navigateUp()
}