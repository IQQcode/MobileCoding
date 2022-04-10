

/**
 * @Author: iqqcode
 * @Date: 2022-04-08 00:10
 * @Description:Kotlin数组
 */
fun main(args: Array<String>) {
    val arrNumber: Array<Int> = arrayOf(1, 2, 3, 4)

    // 创建一个指定大小、所有元素都为空的数组，但是必须指定集合中的元素类型
    // <String?>是指：代表数组中对象为可空的，即arrays数组可存放空对象
    val arrays:Array<String?> = arrayOfNulls<String>(5)
    arrays[0] = null
    arrays[1] = "kotlin"
    println(arrays.contentToString())
}

