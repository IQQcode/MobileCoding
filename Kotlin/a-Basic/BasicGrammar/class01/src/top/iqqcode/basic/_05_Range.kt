package top.iqqcode

/**
 * @Author: iqqcode
 * @Date: 2020-12-15 23:22
 * @Description:区间
 */

fun main(args: Array<String>) {
    // 定义 [1, 100] 的区间
    var nums = 1..10
    println("定义开区间: ")
    for (num in nums) {
        print("${num} ")
    }

    println()

    // 定义 [1, 100) 的区间
    var nums_open = 1 until 10
    println("定义闭区间: ")
    for (num in nums_open) {
        print("${num} ")
    }

    // 步长为2输出
    var nums_interval = 1..20
    println("\n步长为2输出: ")
    for (i in nums_interval step 2) {
        print("${i} ")
    }

    // 反转
    var nums_reverse = nums_open.reversed()
    println("\n反转: ")
    for (i in nums_reverse) {
        print("${i} ")
    }
    println("\n总数为: " + nums_interval.count())
}