package method

/**
 * @Author: iqqcode
 * @Date: 2022-04-09 14:34
 * @Description:
 */

fun method(offset: Int = 0, start: Int, action: () -> String) {
    val ret = action
    println("$ret")
}

fun method(offset: Int = 0, start: Int) {
    println("method: $offset, $start")
}


fun main() {
    // ======================   在括号内传入 ======================
    method(1,2, action = {
        return@method "method"
    })

    // [简写]: 方法体最后一行，就是该方法的返回值
    method(1,2, action = {
        val result = 2 * 6
        "[括号内传入参数] > method $result"
    })

    // ======================= 在括号外传入 ========================

    method(start = 6) {
        "[括号外传入参数] > method"
    }

    // 具名参数
    method(start = 2)

    method(1,2)


}