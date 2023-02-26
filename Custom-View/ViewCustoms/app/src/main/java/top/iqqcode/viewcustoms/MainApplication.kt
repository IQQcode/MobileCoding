package top.iqqcode.viewcustoms

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * @Author: jiazihui
 * @Date: 2023-02-12 17:14
 * @Description:
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}