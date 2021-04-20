import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.GetMethod
import java.io.File

/**
 * @Author: iqqcode
 * @Date: 2021-04-08 15:00
 * @Description:
 */
fun main(args: Array<String>) {
    val client = HttpClient()
    val range = 1..15
    for (i in range) {
        val method = GetMethod("http://www.netbian.com/desk/23394-1920x1080.htm")
        client.executeMethod(method)
        val responseBody = method.responseBody
        method.releaseConnection()
        val file = File("${i}.jpg")
        file.writeBytes(responseBody)
    }
}