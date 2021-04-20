package top.iqqcode.arrays

fun main(args: Array<String>) {
    val arrayInt = Array<Int>(5, arrInit())
    val nums1 = 0..4
    for (num in nums1) {
        arrayInt[num]
    }
    println(arrayInt.contentToString())


    val arrayInitTwo = Array<Int>(5) { it }
    val nums2 = 0..4
    for (num in nums2) {
        arrayInitTwo[num]
    }
    println(arrayInitTwo.contentToString())


    val b = Array(3) { i -> (i * 2) }
    val nums3 = 0..2
    for (num in nums3) {
        b[num]
    }
    println(b.contentToString())

}

fun arrInit(): (Int) -> Int = { it * 3 }