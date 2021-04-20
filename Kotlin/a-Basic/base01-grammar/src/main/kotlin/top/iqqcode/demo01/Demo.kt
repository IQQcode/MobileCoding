import java.lang.Integer.parseInt

/**
 * @Author: iqqcode
 * @Date: 2021-03-31 22:31
 * @Description:
 */
fun main(args: Array<String>) {
    val a: Int = 10000
    println(a === a) // true，值相等，对象地址相等

    //经过了装箱，创建了两个不同的对象
    val boxedA: Int ? = a
    val anotherBoxedA: Int ? = a

    //虽然经过了装箱，但是值是相等的，都是10000
    println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样
    println(boxedA == anotherBoxedA) // true，值相等
}