package top.iqqcode.adapter.objadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 16:22
 * @Description: Source-被适配的220V电源
 */
open class Voltage220V {
    fun output220V(): Int {
        val src: Int =  220
        println("电压 ==> $src 伏")
        return src
    }
}