package top.iqqcode.arrays

/**
 * @Author: iqqcode
 * @Date: 2021-04-19 23:18
 * @Description:
 */

fun main(args: Array<String>) {
    val empty = emptyArray<Int>()

    val otherArray1 = IntArray(6) {it}

    val otherArray2 = IntArray(6) {it * 2}

    println(empty.contentToString())
    println(otherArray1.contentToString())
    println(otherArray2.contentToString())

}
