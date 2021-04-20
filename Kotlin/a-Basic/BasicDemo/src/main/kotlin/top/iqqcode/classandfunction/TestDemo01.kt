package top.iqqcode.classandfunction

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 10:11
 * @Description:object class;
 * object关键字修饰类, 这个类的所有方法相当于静态方法(本质上是单例的类)
 * object可以用于写单例，也可以用于静态的变量和函数
 */
fun main(args: Array<String>) {
    val sexCategory = Person.sex
    Person.printSex()

    val str = "iqqcode"
    // val numOne = str.toInt()
    val numTwo = str.toIntOrNull()
    println(numTwo)
    val numThree: Int? = str.toIntOrNull()
    println(numThree)
    //val numFour: Int = str.toIntOrNull()


}

object Person {
    const val sex = "male"
    fun printSex() = println(sex)
}