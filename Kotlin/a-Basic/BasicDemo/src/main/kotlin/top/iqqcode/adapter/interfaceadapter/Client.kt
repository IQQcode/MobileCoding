package top.iqqcode.adapter.interfaceadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 16:37
 * @Description:
 */
fun main(args: Array<String>) {
    println("===== > 接口适配器模式 < =====")
    val dc5V: DC5V = Adapter(AC220V())
    val dc: Int = dc5V.dc5V()
    println("输入的电压为：" + AC220V().output() + " 伏...")
    println("转换后的电压为：$dc 伏...")
}