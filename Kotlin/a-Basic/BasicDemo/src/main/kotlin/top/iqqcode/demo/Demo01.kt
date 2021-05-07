package top.iqqcode.demo

fun main() {
    val listWithNulls: List<String?> = listOf("Kotlin", null, "Java")
    for (item in listWithNulls) {
        item?.let { // 为null不执行;不为null才执行
            println(it)
        } // 输出 Kotlin 并忽略 null
    }
}