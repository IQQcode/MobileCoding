package top.iqqcode

/**
 * @Author: iqqcode
 * @Date: 2020-12-15 13:29
 * @Description: 函数声明
 */

fun main(args: Array<String>) {
    println("What's your name?")
    val name = readLine()
    println("Hello $name!")

    var a = 6
    var b = 7
    println("${6}和${7}的和为 ${plus(a, b)}")
}


fun plus(a: Int, b: Int): Int {
    if (a + b > 10) return a + b else return b
}
