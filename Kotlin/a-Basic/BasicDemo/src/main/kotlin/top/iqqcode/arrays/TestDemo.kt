package top.iqqcode.arrays

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 10:11
 * @Description:
 */
fun main(args: Array<String>) {
    val user = User("Tom")
    user.name = "Kotlin"
    println(user.name)
}

class User {
    var name = "iqqcode"
        get() {
            return "$field NB!"
        }
        set(value) {
            field = "$field $value"
        }

    constructor(name: String) {
        this.name = name
    }

    init {
        println("Initializing......")
    }
}