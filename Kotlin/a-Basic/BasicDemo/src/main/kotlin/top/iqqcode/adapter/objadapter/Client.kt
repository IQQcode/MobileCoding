package top.iqqcode.adapter.objadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 16:37
 * @Description:
 */
fun main(args: Array<String>) {
    println("===== > 对象适配器模式 < =====")
    val phone = Phone()
    phone.charging(VoltageAdapter(Voltage220V()))
}