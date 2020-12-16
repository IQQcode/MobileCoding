package top.iqqcode

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 07:38
 * @Description:函数表达式
 */

fun main(args: Array<String>) {
    // 1. 函数表达式<写法一>
    var res = { a: Int, b: Int -> a + b }
    println(res(3, 5))

    // 2. 函数表达式<写法二>
    var ans: (Int, Int) -> Int = { a, b -> a + b }
    println(ans(2, 4))

    println(add(2, 3))
}

// 函数简写
fun add(x: Int, y: Int): Int = x + y
