package top.iqqcode.demo05

/**
 * @Author: iqqcode
 * @Date: 2021-04-08 11:42
 * @Description:
 */
fun main(args: Array<String>) {
    // maxBy 取集合中最大值
    println(data.maxBy { it.from })
    // minBy 取集合中最小值
    println(data.minBy { it.from })
    // filter 特定条件过滤
    println(data.filter { (it.from > 20) and (it.use > 100) })
    // map 某个属性映射成新的集合
    println(data.map { "${it.name}  ${it.type}" })
    // any 是否有符合条件的的数据
    println(data.any { it.name == "Java" })
    // count 统计符合条件的的元素
    println(data.count { it.use > 200 })
    // find 查找第一个符合条件的的元素
    println(data.find { it.from <= 20 })
    // groupBy 按照某个类型分组
    println(data.groupBy { it.type })
    // 根据分组打印(?如果有内容就迭代)
    data.groupBy {
        it.type
    }["全栈"]?.forEach { println(it) }

    println("\n********************************************")

    // DSL测试
    data 查找编程语言 "后端"
}

/**
 * DSL-中缀表达式
 */
infix fun List<Coding>.查找编程语言(type: String) {
    filter { it.type == "后端" }.forEach(::println) // ::action
}



data class Coding(var name: String, var from: Int, var info: String, var use: Int, var type: String)

val data = listOf<Coding>(
    Coding("Java", 20, "Java天下第一", 5000, "后端"),
    Coding("Python", 18, "Python人工智能", 200, "后端"),
    Coding("PHP", 22, "PHP牛逼", 1000, "后端"),
    Coding("Kotlin", 10, "Kotlin来啦", 58, "全栈"),
    Coding("HTML", 16, "超文本标记语言", 90, "前端"),
    Coding("C++", 20, "学的头秃", 261, "后端"),
    Coding("C", 50, "嵌入式开发", 330, "后端"),
    Coding("JavaScript", 26, "JavaScript全栈开发", 180, "全栈"),
    Coding("GoLang", 20, "新起之秀", 980, "后端")
)