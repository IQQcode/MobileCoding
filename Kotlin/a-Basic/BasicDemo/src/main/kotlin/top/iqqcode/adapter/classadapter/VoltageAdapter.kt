package top.iqqcode.adapter.classadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 16:29
 * @Description:Adapter类
 */
class VoltageAdapter : Voltage220V(), IVoltage5V {
    override fun output5V(): Int {
        // 获取220V电压
        val src: Int = output220V()
        // 返回5V电压
        return src / 44
    }
}