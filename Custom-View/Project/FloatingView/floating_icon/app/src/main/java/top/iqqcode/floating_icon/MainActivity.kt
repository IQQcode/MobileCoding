package top.iqqcode.floating_icon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import com.google.android.material.bottomnavigation.BottomNavigationView
import top.iqqcode.floating_icon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mNavListener)
    }

    private val mNavListener: BottomNavigationView.OnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.navigation_home -> {
                    binding.mTextMessage!!.setText(R.string.title_home)
                    return true
                }

                R.id.navigation_dashboard -> {
                    binding.mTextMessage!!.setText(R.string.title_dashboard)
                    return true
                }

                R.id.navigation_notifications -> {
                    binding.mTextMessage!!.setText(R.string.title_notifications)
                    return true
                }
            }
            return false
        }
    }
}