package top.iqqcode.demo01

/**
 * @Author: iqqcode
 * @Date: 2021-04-07 21:45
 * @Description:
 */
fun main(args: Array<String>) {
    val text = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """.trimMargin()
    println(text)    // 前置空格删除了
}