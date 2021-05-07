package top.iqqcode.adapter.interfaceadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 17:47
 * @Description:
 */
class AC110V : ACV() {
    override fun output(): Int {
        return 110
    }
}