package top.iqqcode.demo02

/**
 * @Author: iqqcode
 * @Date: 2021-04-08 07:22
 * @Description:
 */
fun main(args: Array<String>) {
    val list = listOf("aaa", "bbb", "ccc")
    println(list)

    val list2 = listOf("aaa", "bbb", "ccc")
    for ((i, e) in list2.withIndex()) {
        println("$i    $e")
    }
}