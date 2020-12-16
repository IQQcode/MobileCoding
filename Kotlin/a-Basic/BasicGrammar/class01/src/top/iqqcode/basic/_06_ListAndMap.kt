package top.iqqcode

import java.util.*
import kotlin.collections.HashMap

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 07:22
 * @Description:List和Map
 */

fun main(args: Array<String>) {
    // List
    var list = listOf<String>("Java", "Pythin", "kotlin", "C++", "GoLang")
    for ((i, e) in list.withIndex()) {
        println("$i  $e")
    }

    println("===========================================================")
    // Map
    var map = HashMap<Int, String>()
    map[1] = "Java"
    map[2] = "Pythin"
    map[3] = "kotlin"
    map[4] = "C++"
    map[5] = "GoLang"

    // 遍历Map的key-value对，entris元素返回key-value对组成的Set
    for (en in map.entries) {
        println("${en.key} - ${en.value}")
    }

    // 先遍历Map的key，再通过key获取value
    for (key in map.keys) {
        println("${key} - ${map[key]}")
    }

    // 直接用for-in循环遍历Map
    for ((key, value) in map) {
        println("${key} - ${value}")
    }

    // 用Lambda表达式遍历Map
    map.forEach({ println("${it.key} - ${it.value}") })
}