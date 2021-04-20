package top.iqqcode.demo01

/**
 * @Author: iqqcode
 * @Date: 2021-04-08 07:00
 * @Description:
 */
fun main(args: Array<String>) {
    gradeJudge(3)
}

fun gradeJudge(score: Int) {
    when (score) {
        10 -> println("aaaaa")
        9 -> println("bbbbbb")
        8 -> println("cccccc")
        7 -> println("ddddd")
        6 -> println("eeeeee")
        else -> println("ffffff")
    }
}

