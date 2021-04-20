/**
 * @Author: iqqcode
 * @Date: 2021-04-07 17:06
 * @Description:
 */

fun main(args: Array<String>) {
    val a = 8
    val b = 2
    println("a + b = " + plus(a, b))
    println("a - b = " + sub(a, b))
}

fun plus(a: Int, b: Int): Int {
    return a + b
}

fun sub(a: Int, b: Int) = a - b
