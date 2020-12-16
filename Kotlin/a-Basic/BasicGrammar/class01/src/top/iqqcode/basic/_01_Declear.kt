package top.iqqcode

/**
 * @Author: iqqcode
 * @Date: 2020-12-15 11:12
 * @Description:变量
 */

fun main(args: Array<String>) {
    var name = "张三"
    name = "李四"
    println(name)

    var num = Int.MAX_VALUE

    // 声明变量类型
    var temp : String

    val read = "只读类型，不能修改"

    var str1 = "iqqcode"
    var str2 = "iqqcode"
    var str3 = "IQQCODE"
    println(str1 == str2)
    println(str1.equals(str2))
    println(str1.equals(str3,true)) //不区分大小写

    /**
     *  Byte
     *  Short
     *  Int
     *  Long
     *  Float
     *  Double
     *  String
     */
}
