package top.iqqcode.adapter.interfaceadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 16:22
 * @Description: Source-被适配的220V电源
 */
open class AC220V : ACV() {
    override fun output(): Int {
        return 220
    }
}