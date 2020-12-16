package top.iqqcode

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 07:58
 * @Description:输入
 */

fun main(args: Array<String>) {
    //输入字符串
    var str = readLine()
    println(str)

    //输入数字
    var num = readLine()?.toInt() //不为空转为数字
    println("$num")

    // 保证输入的数一定不为空
    var a: Int = readLine()!!.toInt()
    var b: Int = readLine()!!.toInt()
    println("a + b = ${a + b}")
}
