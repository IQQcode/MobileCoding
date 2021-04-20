package top.iqqcode.demo05

fun main(args: Array<String>) {
    val names = listOf<String>("Tom", "Jack", "Luck")
    names.forEach {
         // 闭包
        println(it)
    }
}