package top.iqqcode.loops

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 13:16
 * @Description:
 */
fun main(args: Array<String>) {
    val poemArray: Array<String?> = arrayOf("朝辞白帝彩云间", null, "千里江陵一日还", "", "两岸猿声啼不住", "   ", "轻舟已过万重山", "送孟浩然之广陵")
    var pos: Int = -1
    var count: Int = 0
    while (pos <= poemArray.size) {
        pos++
        if (poemArray[pos].isNullOrBlank())
            continue
        if (count % 2 == 0) {
            println("${poemArray[pos]}，")
        } else {
            println("${poemArray[pos]}。")
        }
        count++
        if (count == 4)
            break
    }
}