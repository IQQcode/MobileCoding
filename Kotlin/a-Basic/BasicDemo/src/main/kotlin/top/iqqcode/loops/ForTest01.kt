package top.iqqcode.loops

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 12:52
 * @Description:for-in
 */
fun main(args: Array<String>) {
    val poemArray: Array<String> = arrayOf("朝辞白帝彩云间", "千里江陵一日还", "两岸猿声啼不住", "轻舟已过万重山")

    for (item in poemArray) {
        print("$item,\n")
    }

    println("========================\n")

    for (i in poemArray.indices) {
        if (i % 2 == 0) println("${poemArray[i]}，")
        else println("${poemArray[i]}。")
    }


    println("========================\n")


    // while循环被保留
    val poem: String = ""
    var i: Int = 0
    while (i < poemArray.size) {
        if (i % 2 == 0) {
            println("${poemArray[i]}，")
        } else {
            println("${poemArray[i]}。")
        }
        i++
    }
    println("${poem}该诗歌一共有${i}句。")

}