package top.iqqcode.demo04

/**
 * @Author: iqqcode
 * @Date: 2021-04-08 07:58
 * @Description:递归
 */
fun main(args: Array<String>) {
    val numStr = readLine()
    val num: Int = numStr!!.toInt()
    val result = 0
    totalCount(num, result)
}

/**
 * 尾递归优化: 参数的返回值是函数本身
 */
tailrec fun totalCount(num: Int, result: Int): Int {
    println("第${num}次运算, result = $result")
    if (num == 0) return 1
    else return totalCount(num - 1, result + num)
}

