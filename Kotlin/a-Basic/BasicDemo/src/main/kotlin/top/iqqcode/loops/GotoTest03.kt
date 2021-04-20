package top.iqqcode.loops

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 14:12
 * @Description:
 */
fun main(args: Array<String>) {
    val poemArray: Array<String?> = arrayOf("朝辞白帝彩云间", null, "千里江陵一日还", "", "两岸猿声啼不住", "   ", "轻舟已过万重山", "送孟浩然之广陵")
    var i: Int = 0
    var is_found = false
    outside@ while (i < poemArray.size) {
        var j: Int = 0
        val item = poemArray[i];
        if (item != null) {
            while (j < item.length) {
                if (item[j] == '一') {
                    is_found = true
                    break@outside
                }
                j++
            }
        }
        i++
    }
    println(if (is_found) "我找到'一'字啦" else "没有找到'一'字呀")
}