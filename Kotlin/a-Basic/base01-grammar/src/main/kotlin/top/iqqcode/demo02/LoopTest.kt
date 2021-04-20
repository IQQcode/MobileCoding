package top.iqqcode.demo02

/**
 * @Author: iqqcode
 * @Date: 2021-04-08 07:10
 * @Description:
 */
fun main(args: Array<String>) {
    val nums = 1..100
    var res = 0
    for (num in nums) {
        print("${num} ")
        res += num
    }
    println("\n结果为：${res}")
}