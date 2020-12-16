package top.iqqcode.oop.cls

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 08:56
 * @Description:
 */
class WashMachine(var module: String, var size: Int) {

    var isOpen = true

    fun open() {
        println("洗衣机运行...")
    }

    fun close() {
        println("洗衣机停止！")
    }
}