package top.iqqcode.collection

/**
 * @Author: iqqcode
 * @Date: 2023-09-19 02:01
 * @Description:
 */
open class Person(val name: String, var age: Int) {
    val isAdult: Boolean
        get() { // do something else
            return age >= 18
        }

    open val canWalk: Boolean = false
    open fun walk() {}
}

class Boy : Person("", 1) {

    override val canWalk: Boolean = true
    override fun walk() {}

}