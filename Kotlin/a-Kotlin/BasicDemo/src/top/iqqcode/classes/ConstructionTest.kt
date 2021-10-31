package top.iqqcode.classes

class Person {
    private var name: String? = null
    private var age = 0
    private var feature: Any? = null

    constructor() { }

    constructor(name: String?, age: Int, feature: Any?) {
        this.name = name
        this.age = age
        this.feature = feature
    }

    init {
        println("Hello $name")
    }
}


fun main(args: Array<String>) {
    val per = Person()
    val per1 = Person("Tom", 12, "HAHA")
}