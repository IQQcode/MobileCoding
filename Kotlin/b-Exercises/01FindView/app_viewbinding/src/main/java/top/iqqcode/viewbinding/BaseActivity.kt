package top.iqqcode.viewbinding

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.viewbinding.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding
    private val fragment: FragmentDemo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, FragmentDemo()).commit()
        val transaction = fragmentManager.beginTransaction()

        binding.btnReplace.setOnClickListener {
            Log.d("TAG", "onClick: btnReplace")
            transaction.replace(R.id.fragmentContainer, FragmentDemo2()).addToBackStack(null)
                .commit()
        }

        binding.btnRemove.setOnClickListener {
            if (fragment != null) transaction.remove(fragment).commit()
            Log.d("TAG", "onClick: btnRemove")
        }
    }


}