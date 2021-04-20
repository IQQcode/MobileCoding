package top.iqqcode.demo04

import java.math.BigInteger

/**
 * @Author: iqqcode
 * @Date: 2021-04-08 07:58
 * @Description:递归
 */
fun main(args: Array<String>) {
    val num = BigInteger("50")
    println(fibonacci(num))
}

fun fibonacci(num: BigInteger): BigInteger {
    if (num == BigInteger.ONE) return BigInteger.ONE
    else return num * fibonacci(num - BigInteger.ONE)
}
