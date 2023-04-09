package top.iqqcode.viewcustoms

import android.app.Application
import com.didichuxing.doraemonkit.DoKit
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * @Author: jiazihui
 * @Date: 2023-02-12 17:14
 * @Description:
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initFresco()
        initDoKit()
    }

    private fun initFresco() {
        Fresco.initialize(this)
    }

    private fun initDoKit() {
        DoKit.Builder(this)
            .productId("需要使用平台功能的话，需要到dokit.cn平台申请id")
            .build()
    }
}