package top.iqqcode.textcontent.util

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * @Author: jiazihui
 * @Date: 2022-12-11 16:22
 * @Description:
 */
object UtilHelper {

    fun dealLink(link: String, context: Context) {
        val uri: Uri = Uri.parse(link)
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        intent.data = uri
        context.startActivity(intent)
    }

    fun <T> getCount(list: List<T>?): Int {
        return if (list.isNullOrEmpty()) {
            0
        } else list.size
    }

    fun <T> isEmpty(list: List<T>?): Boolean {
        return getCount(list) <= 0
    }
}