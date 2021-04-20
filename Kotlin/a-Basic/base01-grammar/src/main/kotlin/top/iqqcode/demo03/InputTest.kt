package top.iqqcode.demo03

/**
 * @Author: iqqcode
 * @Date: 2021-04-08 07:48
 * @Description:
 */
fun main(args: Array<String>) {
    while (true) {
        println("请输入第一个数字：")
        val num1Str = readLine()
        println("请输入第二个数字：")
        val num2Str = readLine()

        try {
            val num1: Int = num1Str!!.toInt()
            val num2: Int = num2Str!!.toInt()
            println("$num1 + $num2 = ${num1 + num2}")
        } catch (e: Exception) {
            println("请输入数字哦！")
        }
    }

}