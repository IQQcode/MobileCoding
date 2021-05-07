package top.iqqcode.adapter.objadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 16:29
 * @Description:Adapter类
 */

// 主构造方法就是直接跟在类名后面的
class VoltageAdapter(private var voltage220V: Voltage220V) : IVoltage5V {

    override fun output5V(): Int {
        // 获取220V电压
        val src: Int = voltage220V.output220V()
        val target = src / 44
        println("适配完成，输出的电压为$target")
        // 返回5V电压
        return target
    }
}