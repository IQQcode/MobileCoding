package lambda

/**
 * @Author: iqqcode
 * @Date: 2022-04-10 08:51
 * @Description:
 */


/**
 * lambda表达式作为方法中的参数的时候。定义transform方法，可以对数组中的元素进行变换。
 * @param array 要求传入一个数组，元素类型为Int整数类型
 * @param action 要求传入的是一个方法，接受两个参数下标index,元素element。要求返回变换后的元素，会替换掉老的元素
 */
fun transform(array: Array<Int>, action: (index: Int, element: Int) -> Int) {
    for (index: Int in array.indices) {
        val newValue: Int = action(index, array[index])
        array[index] = newValue
    }
}

fun main(args: Array<String>) {
    val nums = arrayOf(1,2,3,4)
    // 方法外传入调用
    transform(nums, action = { index, element ->
        index * element // 返回值
    })
    // 方法外入调用
    transform(nums) {
        index, element ->  index * element * 2
    }
    println(nums.contentToString())
}