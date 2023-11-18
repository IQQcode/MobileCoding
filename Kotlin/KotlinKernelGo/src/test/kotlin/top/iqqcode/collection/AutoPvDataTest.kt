package top.iqqcode.collection

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * @Author: iqqcode
 * @Date: 2023-10-29 20:47
 * @Description: TODO
 */
class AutoPvDataTest {

    @Test
    fun testAutoPvData() {
        val map = mutableMapOf<String, AutoPvData?>()
        val pvData = AutoPvData("chat", "chatroom", 1, 2)
        map[pvData.scene] = pvData

        val pvTmp = map[pvData.scene]
        pvTmp?.let {
            it.pv += 10
            it.pvLost -= 10
        }
        map[pvData.scene] = pvTmp

        println("pvData = ${pvData.hashCode()} | pvTmp = ${pvTmp.hashCode()}")
    }
}