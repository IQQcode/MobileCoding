package top.iqqcode.okhttp02

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author: iqqcode
 * @Date: 2021-06-13 08:27
 * @Description:拦截器
 */
class LogIntercept : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val currentTime = System.currentTimeMillis()
        Log.i("ICP", "intercept: ===> REQUEST = $request")
        Log.i("ICP", "intercept: ===> RESPONSE = $response")
        Log.i("ICP", "intercept: ===> 耗时${System.currentTimeMillis() -  currentTime}ms")
        return response
    }
}