package top.iqqcode.demo02

/**
 * @Author: iqqcode
 * @Date: 2021-04-08 07:10
 * @Description:
 */
fun main(args: Array<String>) {
    val nums = 1 until 10 // [1,100)
    var res = 0
    println("[ 开区间 ]")
    for (num in nums) {
        print("${num} ")
        res += num
    }
    println("\n结果为：${res}")

    val nums2 = 1..10
    for (num in nums2 step 2) {
        print("${num} ")
    }

    println()

    val nums3 = nums2.reversed()
    for (num in nums3 step 2) {
        print("${num} ")
    }
    println("数组中共有" + nums3.count() + "个元素")
}