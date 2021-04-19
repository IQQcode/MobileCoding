package top.iqqcode.activitydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.activitydemo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // extras?  为null则不执行getString("name")
        val name = intent.getStringExtra("name")
        binding.textView.text = name

        val user = intent.getParcelableExtra<User>("user")
        binding.textViewContext.text = user?.username + " " + user?.password.toString()

        binding.button.setOnClickListener {
            Intent(this, ThirdActivity::class.java).apply {
                putExtras(intent.extras!!)
                putExtra("date", "2021-04-19")
                startActivity(this)
            }
        }
    }
}