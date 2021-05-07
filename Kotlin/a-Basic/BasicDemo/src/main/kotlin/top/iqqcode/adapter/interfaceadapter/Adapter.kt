package top.iqqcode.adapter.interfaceadapter

/**
 * @Author: iqqcode
 * @Date: 2021-04-27 18:00
 * @Description:
 */
class Adapter : DC5V {

    private var acv: ACV?

    constructor(ac220V: AC220V?) {
        acv = ac220V
    }

    constructor(ac110V: AC110V?) {
        acv = ac110V
    }

    override fun dc5V(): Int {
        var ac = 0
        if (acv != null) {
            ac = acv!!.output()
        }
        val sta = ac / 5
        return ac / sta
    }
}