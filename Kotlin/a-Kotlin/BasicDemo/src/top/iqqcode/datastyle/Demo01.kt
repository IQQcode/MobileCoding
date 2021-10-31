fun main(args: Array<String>) {
    // 1.创建并初始化一个IntArray  [1, 2, 3, 4, 5]
    val intArray1 = intArrayOf(1, 2, 3, 4, 5)

    // 2.创建一个长度为5的空的IntArray
    val intArray2 = IntArray(5)

    // 3.创建一个长度为5的值全为100的IntArray [100, 100, 100, 100, 100]
    val intArray3 = IntArray(5) { 100 }

    // 4.注意这里it是它索引下标值，所以这是创建一个长度为5的IntArray [0, 2, 4, 6, 8]
    val intArray4 = IntArray(5) { it * 2 }
}