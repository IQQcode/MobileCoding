package top.iqqcode.collection

/**
 * @Author: iqqcode
 * @Date: 2023-09-28 00:10
 * @Description:
 */
fun main(args: Array<String>) {

    val f4: (Int) -> Int = { a -> a + 5 }
    val f5: (Int) -> Int = { b -> b * 3 }

    val composedFunction: (Int) -> Int = { x -> f4(f5(x)) }
    val result = composedFunction(7)
    println(result)
}