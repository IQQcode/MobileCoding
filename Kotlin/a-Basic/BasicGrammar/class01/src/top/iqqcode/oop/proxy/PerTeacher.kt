package top.iqqcode.oop.proxy

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 09:28
 * @Description:
 */

class PerTeacher : IPerson {
    override fun role() {
        println("teacher")
    }

    override fun behavior() {
        println("Teach and educate people...")
    }
}