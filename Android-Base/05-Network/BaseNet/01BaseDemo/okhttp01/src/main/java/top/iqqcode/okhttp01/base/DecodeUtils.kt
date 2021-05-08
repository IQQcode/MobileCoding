package top.iqqcode.okhttp01.base

import java.io.*

/**
 * @Author: iqqcode
 * @Date: 2021-06-11 14:33
 * @Description:
 */

object DecodeUtils {
    /**
     * 输入流转为字符串
     * @param inputStream InputStream
     * @return String
     */
    private fun streamToString(inputStream: InputStream): String? {
        val buffer = StringBuffer()
        val inputStreamReader: InputStreamReader
        try {
            inputStreamReader = InputStreamReader(inputStream, "utf-8")
            val bufferedReader = BufferedReader(inputStreamReader)
            var str: String? = null
            while (bufferedReader.readLine().also { str = it } != null) {
                buffer.append(str)
            }
            // 释放资源
            bufferedReader.close()
            inputStreamReader.close()
            inputStream.close()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return buffer.toString()
    }


    /**
     * 将Unicode字符转换为UTF-8类型字符串
     * @param unicodeStr String?
     * @return String?
     */
    fun decode(unicodeStr: String?): String? {
        if (unicodeStr == null) {
            return null
        }
        val retBuf = StringBuilder()
        val maxLoop = unicodeStr.length
        var i = 0
        while (i < maxLoop) {
            if (unicodeStr[i] == '\\') {
                if (i < maxLoop - 5
                    && (unicodeStr[i + 1] == 'u' || unicodeStr[i + 1] == 'U')
                ) try {
                    retBuf.append(unicodeStr.substring(i + 2, i + 6).toInt(16).toChar())
                    i += 5
                } catch (localNumberFormatException: NumberFormatException) {
                    retBuf.append(unicodeStr[i])
                } else {
                    retBuf.append(unicodeStr[i])
                }
            } else {
                retBuf.append(unicodeStr[i])
            }
            i++
        }
        return retBuf.toString()
    }
}