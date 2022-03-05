package top.iqqcode.okhttp01.base

/**
 * @Author: iqqcode
 * @Date: 2021-06-09 13:02
 * @Description:
 */
data class LessonResult(var mStatus: Int, var mLessons: ArrayList<Lesson>) {

    data class Lesson(
        var ID: Int = 0,
        var name: String? = null,
        var smallPictureUrl: String? = null,
        var bigPictureUrl: String? = null,
        var description: String? = null,
        var learnerNumber: Int = 0
    )
}

