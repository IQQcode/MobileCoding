package top.iqqcode

/**
 * @Author: iqqcode
 * @Date: 2020-12-15 23:10
 * @Description:when(switch)表达式
 */

fun main(args: Array<String>) {
    val str = readLine()
    println("字符串长度为: ${str?.let { showSize(it) }}")
}

fun showSize(str: String): Int {
    var length = str.length
    when(length) {
        1 -> println("字符串长度为1")
        2 -> println("字符串长度为2")
        3 -> println("字符串长度为3")
        4 -> println("字符串长度为4")
        5 -> println("字符串长度为5")
        else -> println("字符串长度大于5")
    }
    return length;
}
