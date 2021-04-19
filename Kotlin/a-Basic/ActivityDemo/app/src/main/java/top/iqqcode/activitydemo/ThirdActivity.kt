package top.iqqcode.activitydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.activitydemo.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val date = intent.getStringExtra("date")
        binding.textView.text = date

        val user = intent.getParcelableExtra<User>("user")
        binding.textViewContext.text = user?.username + " " + user?.password.toString()
    }
}