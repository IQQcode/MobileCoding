package top.iqqcode.forceout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.forceout.databinding.ActivityLoginBinding


class LoginActivity : BaseActivity(),View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val rootView: View = binding.root
        setContentView(rootView)

        binding.loginButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.loginButton -> {
                val account: String = binding.accountEdit.text.toString()
                val password: String = binding.passwordEdit.text.toString()
                //在这里进行默认账户为 admin 密码为123456
                if (account == "admin" && password == "123456") { // Kotlin写法
                    //登录成功后 进入MainActivity  并且在这里面提供强制下线的功能
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "账户和密码不匹配", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}