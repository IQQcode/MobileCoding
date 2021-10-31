package top.iqqcode.loop

class Person (var name: String?, var age: Int, var feature: Any?) {
    init {
        println(this.name + this.age + this.feature)
    }
}