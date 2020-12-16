package top.iqqcode

/**
 * @Author: iqqcode
 * @Date: 2020-12-15 14:11
 * @Description:null处理
 */

fun main(args: Array<String>) {
    var res1 = heat("dog")
    println(res1)

    // Null can not be a value of a non-null type String
    var res2 = heat(null)
    println(res2)
}

// 收一个参数参数是非空的string类型, 加上问号代表的是参数可以为空
fun heat(str: String?): String {
    return "hot " + str
}
