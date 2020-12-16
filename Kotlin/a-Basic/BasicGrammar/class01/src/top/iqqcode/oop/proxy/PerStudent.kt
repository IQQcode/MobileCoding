package top.iqqcode.oop.proxy

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 09:28
 * @Description:
 */

object PerStudent : IPerson {
    override fun role() {
        println("student")
    }

    override fun behavior() {
        println("Study hard... ")
    }

}