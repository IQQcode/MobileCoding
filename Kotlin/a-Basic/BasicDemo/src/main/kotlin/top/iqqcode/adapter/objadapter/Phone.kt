package top.iqqcode.adapter.objadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 16:35
 * @Description:
 */
class Phone {
    fun charging(iVoltage5V: IVoltage5V) {
        if(iVoltage5V.output5V() == 5) {
            println("电压为5V, 可以充电")
        } else if(iVoltage5V.output5V() > 5) {
            println("电压不为5V, 不可以充电")
        }
    }
}