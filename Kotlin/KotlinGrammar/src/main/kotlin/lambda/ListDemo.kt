package lambda

/**
 * @Author: iqqcode
 * @Date: 2022-04-10 09:51
 * @Description:
 */
fun main(args: Array<String>) {

    val list = arrayListOf<Int>(1, 2, 3, 4, 5, 6)
    // 具名参数使用
    list.forEachIndexed(action = {index: Int, element: Int ->
        println("forEachIndexed: $index -> $$element")
    })

    // 括号外使用(非具名参数)
    list.forEachIndexed { index: Int, element: Int ->
        println("forEachIndexed: $index -> $$element")
    }
}