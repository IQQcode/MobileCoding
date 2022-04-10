package coll

import java.util.*

/**
 * @Author: iqqcode
 * @Date: 2022-04-09 11:49
 * @Description:Kotlin集合
 */
fun main(args: Array<String>) {
    // set集合使用
    val set = mutableSetOf<Int>(1, 2, 3, 1, 5, 5) // 可以对numbers进行增删改查操作
    set.add(6)
    println(set)
    println("isEmpty: ${set.isEmpty()}")
    println("contains: ${set.contains(0)}")
    println("indexOf: ${set.indexOf(1)}")
    println("lastIndexOf: ${set.lastIndexOf(5)}")

    // 迭代器使用
    val iterator = set.iterator()
    iterator.forEach {
        println("it: $it")
    }
}