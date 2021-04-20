package top.iqqcode.arrays

/**
 * @Author: iqqcode
 * @Date: 2021-04-19 20:10
 * @Description:
 */

fun main(args: Array<String>) {
    // 数据类型: Boolean、Shot、Int、Long、Float、Double、Char

    // 创建包含指定元素的数组(Java数组静态初始化)
    val arrString = arrayOf("java", "kotlin")
    val arrInt = arrayOf(1, 2, 3)
    println(arrString.contentToString())
    println(arrInt.contentToString())


    // 创建指定长度，元素为null的数组(Java动态初始化)
    val arr2 = arrayOfNulls<String>(6)
    val arr2Double = arrayOfNulls<Double>(8)
    arr2[0] = "Java"
    arr2.set(1, "Kotlin")
    arr2Double[0] = 10.0
    arr2Double.set(1, 22.0)
    println(arr2.contentToString())
    println(arr2Double.contentToString())


    booleanArrayOf(false, true)
    charArrayOf('a', 'b')
    byteArrayOf(1, 2)
    shortArrayOf(1, 2)
    intArrayOf(1, 2, 3)
    longArrayOf(1L, 2L)
    floatArrayOf(1F, 2F)
    doubleArrayOf(4.0, 5.0, 6.0)
}