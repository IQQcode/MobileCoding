package top.iqqcode.demo01

/**
 * @Author: iqqcode
 * @Date: 2021-04-07 18:10
 * @Description:
 */
fun main(args: Array<String>) {
    val a = 3
    val b = 5
    println("${a}和${b}较大的值为${returnBig(a, b)}")

    var name = "zhang san"
    println(name)
    name = "li si"
    println(name)
    val finalValue = "我是不可改变的";
    println(finalValue);
}

fun returnBig(a: Int, b: Int): Int {
    if (a > b) return a
    else return b
}
