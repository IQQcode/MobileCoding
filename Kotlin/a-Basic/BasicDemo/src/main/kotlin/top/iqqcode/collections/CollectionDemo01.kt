package top.iqqcode.collections

/**
 * @Author: iqqcode
 * @Date: 2021-04-19 19:29
 * @Description:
 */
class Demo01 {
}

fun main(args: Array<String>) {
    var msg = readLine(); // a_b_c_d_e f_g_i_j_k
    args.flatMap {
        it.split("_")
    }.map {
        print("$it")
    }
}