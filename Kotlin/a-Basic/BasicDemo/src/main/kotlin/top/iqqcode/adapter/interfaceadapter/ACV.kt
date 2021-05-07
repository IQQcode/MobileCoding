package top.iqqcode.adapter.interfaceadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 17:43
 * @Description:抽象的电源
 */
abstract class ACV {
    open fun output(): Int {
        return 220
    }
}