package top.iqqcode.forceout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * @Author: iqqcode
 * @Date: 2022-02-13 11:15
 * @Description:定义父类BaseActivity
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 创建活动时，将其加入管理器中
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 销毁活动时，将其从管理器中移除
        ActivityCollector.removeActivity(this)
    }
}